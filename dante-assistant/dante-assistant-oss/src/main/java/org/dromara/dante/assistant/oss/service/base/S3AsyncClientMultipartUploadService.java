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

import cn.herodotus.stirrup.assistant.oss.definition.service.AbstractS3AsyncClientService;
import cn.herodotus.stirrup.assistant.oss.pool.S3AsyncClientObjectPool;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.*;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Description: 对象存储分片上传基本操作Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 22:07
 */
@Service
public class S3AsyncClientMultipartUploadService extends AbstractS3AsyncClientService {

    public S3AsyncClientMultipartUploadService(S3AsyncClientObjectPool objectPool) {
        super(objectPool);
    }

    public CompletableFuture<AbortMultipartUploadResponse> abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest) {
        return toFuture(client -> client.abortMultipartUpload(abortMultipartUploadRequest));
    }

    public CompletableFuture<CompleteMultipartUploadResponse> completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest) {
        return toFuture(client -> client.completeMultipartUpload(completeMultipartUploadRequest));
    }

    public CompletableFuture<CreateMultipartUploadResponse> createMultipartUpload(CreateMultipartUploadRequest createMultipartUploadRequest) {
        return toFuture(client -> client.createMultipartUpload(createMultipartUploadRequest));
    }

    public CompletableFuture<ListPartsResponse> listParts(ListPartsRequest listPartsRequest) {
        return toFuture(client -> client.listParts(listPartsRequest));
    }

    public CompletableFuture<UploadPartResponse> uploadPart(UploadPartRequest uploadPartRequest, AsyncRequestBody requestBody) {
        return toFuture(client -> client.uploadPart(uploadPartRequest, requestBody));
    }
}
