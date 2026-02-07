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

package org.dromara.dante.assistant.oss.service.base;

import org.dromara.dante.assistant.oss.definition.service.AbstractS3AsyncClientService;
import org.dromara.dante.assistant.oss.pool.S3AsyncClientObjectPool;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.model.*;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Description: 对象存储 Object 基本操作Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 17:38
 */
@Service
public class S3AsyncClientObjectService extends AbstractS3AsyncClientService {

    public S3AsyncClientObjectService(S3AsyncClientObjectPool objectPool) {
        super(objectPool);
    }

    public CompletableFuture<HeadObjectResponse> headObject(HeadObjectRequest headObjectRequest) {
        return toFuture(client -> client.headObject(headObjectRequest));
    }

    public CompletableFuture<DeleteObjectResponse> deleteObject(DeleteObjectRequest deleteObjectRequest) {
        return toFuture(client -> client.deleteObject(deleteObjectRequest));
    }

    public CompletableFuture<DeleteObjectsResponse> deleteObjects(DeleteObjectsRequest deleteObjectsRequest) {
        return toFuture(client -> client.deleteObjects(deleteObjectsRequest));
    }

    public <ReturnT> CompletableFuture<ReturnT> getObject(GetObjectRequest getObjectRequest, AsyncResponseTransformer<GetObjectResponse, ReturnT> asyncResponseTransformer) {
        return toFuture(client -> client.getObject(getObjectRequest, asyncResponseTransformer));
    }

    public CompletableFuture<ListObjectsV2Response> listObjectsV2(ListObjectsV2Request listObjectsV2Request) {
        return toFuture(client -> client.listObjectsV2(listObjectsV2Request));
    }

    public CompletableFuture<PutObjectResponse> putObject(PutObjectRequest putObjectRequest, AsyncRequestBody requestBody) {
        return toFuture(client -> client.putObject(putObjectRequest, requestBody));
    }

    public CompletableFuture<WriteGetObjectResponseResponse> writeGetObjectResponse(WriteGetObjectResponseRequest writeGetObjectResponseRequest, AsyncRequestBody requestBody) {
        return toFuture(client -> client.writeGetObjectResponse(writeGetObjectResponseRequest, requestBody));
    }

    public CompletableFuture<GetObjectAclResponse> getObjectAcl(GetObjectAclRequest request) {
        return toFuture(client -> client.getObjectAcl(request));
    }

    public CompletableFuture<GetObjectAttributesResponse> getObjectAttributes(GetObjectAttributesRequest request) {
        return toFuture(client -> client.getObjectAttributes(request));
    }

    public CompletableFuture<GetObjectLegalHoldResponse> getObjectLegalHold(GetObjectLegalHoldRequest request) {
        return toFuture(client -> client.getObjectLegalHold(request));
    }

    public CompletableFuture<GetObjectLockConfigurationResponse> getObjectLockConfiguration(GetObjectLockConfigurationRequest request) {
        return toFuture(client -> client.getObjectLockConfiguration(request));
    }

    public CompletableFuture<GetObjectRetentionResponse> getObjectRetention(GetObjectRetentionRequest request) {
        return toFuture(client -> client.getObjectRetention(request));
    }

    public CompletableFuture<GetObjectTaggingResponse> getObjectTagging(GetObjectTaggingRequest request) {
        return toFuture(client -> client.getObjectTagging(request));
    }

    public <ReturnT> CompletableFuture<ReturnT> getObjectTorrent(GetObjectTorrentRequest request, AsyncResponseTransformer<GetObjectTorrentResponse, ReturnT> asyncResponseTransformer) {
        return toFuture(client -> client.getObjectTorrent(request, asyncResponseTransformer));
    }

    public CompletableFuture<PutObjectAclResponse> putObjectAcl(PutObjectAclRequest request) {
        return toFuture(client -> client.putObjectAcl(request));
    }

    public CompletableFuture<PutObjectLegalHoldResponse> putObjectLegalHold(PutObjectLegalHoldRequest request) {
        return toFuture(client -> client.putObjectLegalHold(request));
    }

    public CompletableFuture<PutObjectLockConfigurationResponse> putObjectLockConfiguration(PutObjectLockConfigurationRequest request) {
        return toFuture(client -> client.putObjectLockConfiguration(request));
    }

    public CompletableFuture<PutObjectRetentionResponse> putObjectRetention(PutObjectRetentionRequest request) {
        return toFuture(client -> client.putObjectRetention(request));
    }

    public CompletableFuture<PutObjectTaggingResponse> putObjectTagging(PutObjectTaggingRequest request) {
        return toFuture(client -> client.putObjectTagging(request));
    }

    public CompletableFuture<DeleteObjectTaggingResponse> deleteObjectTagging(DeleteObjectTaggingRequest request) {
        return toFuture(client -> client.deleteObjectTagging(request));
    }

    public CompletableFuture<RenameObjectResponse> renameObject(RenameObjectRequest request) {
        return toFuture(client -> client.renameObject(request));
    }

    public CompletableFuture<RestoreObjectResponse> restoreObject(RestoreObjectRequest request) {
        return toFuture(client -> client.restoreObject(request));
    }

    public CompletableFuture<ListObjectVersionsResponse> listObjectVersions(ListObjectVersionsRequest request) {
        return toFuture(client -> client.listObjectVersions(request));
    }

    public CompletableFuture<UpdateObjectEncryptionResponse> updateObjectEncryption(UpdateObjectEncryptionRequest request) {
        return toFuture(client -> client.updateObjectEncryption(request));
    }
}
