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
import software.amazon.awssdk.services.s3.model.CreateSessionRequest;
import software.amazon.awssdk.services.s3.model.CreateSessionResponse;
import software.amazon.awssdk.services.s3.model.GetPublicAccessBlockRequest;
import software.amazon.awssdk.services.s3.model.GetPublicAccessBlockResponse;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Description: 对象存储功能性基本操作Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/4 22:40
 */
public class S3AsyncClientFunctionService extends AbstractS3AsyncClientService {

    public S3AsyncClientFunctionService(S3AsyncClientObjectPool objectPool) {
        super(objectPool);
    }

    public CompletableFuture<GetPublicAccessBlockResponse> getPublicAccessBlock(GetPublicAccessBlockRequest request) {
        return toFuture(client -> client.getPublicAccessBlock(request));
    }

    public CompletableFuture<CreateSessionResponse> createSession(CreateSessionRequest request) {
        return toFuture(client -> client.createSession(request));
    }
}
