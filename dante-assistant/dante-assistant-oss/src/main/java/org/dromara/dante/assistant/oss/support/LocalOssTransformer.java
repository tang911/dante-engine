/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Stirrup.
 *
 * Herodotus Stirrup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Stirrup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.stirrup.assistant.oss.support;

import cn.herodotus.stirrup.assistant.oss.service.base.S3AsyncClientObjectService;
import cn.herodotus.stirrup.assistant.oss.service.base.S3TransferManagerService;
import cn.herodotus.stirrup.core.support.file.OssTransformer;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.transfer.s3.model.CompletedFileDownload;
import software.amazon.awssdk.transfer.s3.model.CompletedFileUpload;
import software.amazon.awssdk.transfer.s3.model.DownloadFileRequest;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

/**
 * <p>Description: 本地文件传输器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/11/28 22:13
 */
@Component
public class LocalOssTransformer implements OssTransformer {

    private final S3AsyncClientObjectService s3AsyncClientObjectService;
    private final S3TransferManagerService s3TransferManagerService;

    public LocalOssTransformer(S3AsyncClientObjectService s3AsyncClientObjectService, S3TransferManagerService s3TransferManagerService) {
        this.s3AsyncClientObjectService = s3AsyncClientObjectService;
        this.s3TransferManagerService = s3TransferManagerService;
    }

    @Override
    public boolean upload(String bucketName, Path path) {
        if (Files.isRegularFile(path)) {
            String fileName = path.getFileName().toString();

            UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                    .putObjectRequest(b -> b.bucket(bucketName).key(fileName))
                    .addTransferListener(LoggingTransferListener.create())  // Add listener.
                    .source(path)
                    .build();

            CompletableFuture<CompletedFileUpload> future = s3TransferManagerService.uploadFile(uploadFileRequest);
            CompletedFileUpload result = future.join();

            return ObjectUtils.isNotEmpty(result) && ObjectUtils.isNotEmpty(result.response());
        }

        return false;
    }

    @Override
    public boolean download(String bucketName, Path path) {
        if (Files.isRegularFile(path)) {

            String fileName = path.getFileName().toString();

            DownloadFileRequest downloadFileRequest = DownloadFileRequest.builder()
                    .getObjectRequest(b -> b.bucket(bucketName).key(fileName))
                    .addTransferListener(LoggingTransferListener.create())  // Add listener.
                    .destination(path)
                    .build();

            CompletableFuture<CompletedFileDownload> future = s3TransferManagerService.downloadFile(downloadFileRequest);
            CompletedFileDownload result = future.join();

            return ObjectUtils.isNotEmpty(result) && ObjectUtils.isNotEmpty(result.response());
        }

        return false;
    }

    @Override
    public boolean remove(String bucketName, String fileName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        CompletableFuture<DeleteObjectResponse> future = s3AsyncClientObjectService.deleteObject(deleteObjectRequest);
        DeleteObjectResponse result = future.join();

        return ObjectUtils.isNotEmpty(result);
    }
}
