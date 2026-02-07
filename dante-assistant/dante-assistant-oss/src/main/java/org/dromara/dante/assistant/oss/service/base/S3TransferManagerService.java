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

package cn.herodotus.stirrup.assistant.oss.service.base;

import cn.herodotus.stirrup.assistant.oss.definition.service.AbstractS3TransferManagerService;
import cn.herodotus.stirrup.assistant.oss.pool.S3AsyncClientObjectPool;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.transfer.s3.model.*;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Description: AWS SDK V2 高阶操作 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/28 18:02
 */
@Service
public class S3TransferManagerService extends AbstractS3TransferManagerService {

    public S3TransferManagerService(S3AsyncClientObjectPool objectPool) {
        super(objectPool);
    }


    public <ResultT> CompletableFuture<CompletedDownload<ResultT>> download(DownloadRequest<ResultT> downloadRequest) {
        Download<ResultT> download = process(s3TransferManager -> s3TransferManager.download(downloadRequest));
        return download.completionFuture();
    }

    public CompletableFuture<CompletedFileDownload> downloadFile(DownloadFileRequest downloadRequest) {
        return fileDownload(s3TransferManager -> s3TransferManager.downloadFile(downloadRequest));
    }

    public CompletableFuture<CompletedFileDownload> resumeDownloadFile(ResumableFileDownload resumableFileDownload) {
        return fileDownload(s3TransferManager -> s3TransferManager.resumeDownloadFile(resumableFileDownload));
    }

    public CompletableFuture<CompletedDirectoryDownload> downloadDirectory(DownloadDirectoryRequest downloadDirectoryRequest) {
        DirectoryDownload directoryDownload = process(s3TransferManager -> s3TransferManager.downloadDirectory(downloadDirectoryRequest));
        return directoryDownload.completionFuture();
    }

    public CompletableFuture<CompletedUpload> upload(UploadRequest uploadRequest) {
        Upload upload = process(s3TransferManager -> s3TransferManager.upload(uploadRequest));
        return upload.completionFuture();
    }

    public CompletableFuture<CompletedFileUpload> uploadFile(UploadFileRequest uploadFileRequest) {
        return fileUpload(s3TransferManager -> s3TransferManager.uploadFile(uploadFileRequest));
    }

    public CompletableFuture<CompletedFileUpload> resumeUploadFile(ResumableFileUpload resumableFileUpload) {
        return fileUpload(s3TransferManager -> s3TransferManager.resumeUploadFile(resumableFileUpload));
    }

    public CompletableFuture<CompletedDirectoryUpload> uploadDirectory(UploadDirectoryRequest uploadDirectoryRequest) {
        DirectoryUpload directoryUpload = process(s3TransferManager -> s3TransferManager.uploadDirectory(uploadDirectoryRequest));
        return directoryUpload.completionFuture();
    }
}
