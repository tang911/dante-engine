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

package cn.herodotus.stirrup.assistant.oss.definition.service;

import cn.herodotus.stirrup.assistant.oss.pool.S3AsyncClientObjectPool;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedFileDownload;
import software.amazon.awssdk.transfer.s3.model.CompletedFileUpload;
import software.amazon.awssdk.transfer.s3.model.FileDownload;
import software.amazon.awssdk.transfer.s3.model.FileUpload;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * <p>Description: AWS SDK V2 高阶操作通用定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/28 17:59
 */
public abstract class AbstractS3TransferManagerService extends AbstractS3AsyncClientService {

    public AbstractS3TransferManagerService(S3AsyncClientObjectPool objectPool) {
        super(objectPool);
    }

    protected <T> T process(Function<S3TransferManager, T> operate) {
        S3AsyncClient client = getClient();
        S3TransferManager transferManager = S3TransferManager.builder().s3Client(client).build();
        T future = operate.apply(transferManager);
        transferManager.close();
        close(client);
        return future;
    }

    protected CompletableFuture<CompletedFileDownload> fileDownload(Function<S3TransferManager, FileDownload> operate) {
        FileDownload fileDownload = process(operate);
        return fileDownload.completionFuture();
    }

    protected CompletableFuture<CompletedFileUpload> fileUpload(Function<S3TransferManager, FileUpload> operate) {
        FileUpload fileUpload = process(operate);
        return fileUpload.completionFuture();
    }
}
