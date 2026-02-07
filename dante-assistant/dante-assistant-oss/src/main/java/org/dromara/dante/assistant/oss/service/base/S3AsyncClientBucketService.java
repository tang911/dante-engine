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
import software.amazon.awssdk.services.s3.model.*;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Description: 对象存储 Bucket 基本操作Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 17:09
 */
@Service
public class S3AsyncClientBucketService extends AbstractS3AsyncClientService {

    public S3AsyncClientBucketService(S3AsyncClientObjectPool objectPool) {
        super(objectPool);
    }

    public CompletableFuture<HeadBucketResponse> headBucket(HeadBucketRequest request) {
        return toFuture(client -> client.headBucket(request));
    }

    public CompletableFuture<CreateBucketResponse> createBucket(CreateBucketRequest request) {
        return toFuture(client -> client.createBucket(request));
    }

    public CompletableFuture<DeleteBucketResponse> deleteBucket(DeleteBucketRequest request) {
        return toFuture(client -> client.deleteBucket(request));
    }

    public CompletableFuture<ListBucketsResponse> listBuckets(ListBucketsRequest request) {
        return toFuture(client -> client.listBuckets(request));
    }

    public CompletableFuture<GetBucketAbacResponse> getBucketAbac(GetBucketAbacRequest request) {
        return toFuture(client -> client.getBucketAbac(request));
    }

    public CompletableFuture<GetBucketAclResponse> getBucketAcl(GetBucketAclRequest request) {
        return toFuture(client -> client.getBucketAcl(request));
    }

    public CompletableFuture<GetBucketAccelerateConfigurationResponse> getBucketAccelerateConfiguration(GetBucketAccelerateConfigurationRequest request) {
        return toFuture(client -> client.getBucketAccelerateConfiguration(request));
    }

    public CompletableFuture<GetBucketAnalyticsConfigurationResponse> getBucketAnalyticsConfiguration(GetBucketAnalyticsConfigurationRequest request) {
        return toFuture(client -> client.getBucketAnalyticsConfiguration(request));
    }

    public CompletableFuture<GetBucketCorsResponse> getBucketCors(GetBucketCorsRequest request) {
        return toFuture(client -> client.getBucketCors(request));
    }

    public CompletableFuture<GetBucketEncryptionResponse> getBucketEncryption(GetBucketEncryptionRequest request) {
        return toFuture(client -> client.getBucketEncryption(request));
    }

    public CompletableFuture<GetBucketIntelligentTieringConfigurationResponse> getBucketIntelligentTieringConfiguration(GetBucketIntelligentTieringConfigurationRequest request) {
        return toFuture(client -> client.getBucketIntelligentTieringConfiguration(request));
    }

    public CompletableFuture<GetBucketInventoryConfigurationResponse> getBucketInventoryConfiguration(GetBucketInventoryConfigurationRequest request) {
        return toFuture(client -> client.getBucketInventoryConfiguration(request));
    }

    public CompletableFuture<GetBucketLifecycleConfigurationResponse> getBucketLifecycleConfiguration(GetBucketLifecycleConfigurationRequest request) {
        return toFuture(client -> client.getBucketLifecycleConfiguration(request));
    }

    public CompletableFuture<GetBucketLocationResponse> getBucketLocation(GetBucketLocationRequest request) {
        return toFuture(client -> client.getBucketLocation(request));
    }

    public CompletableFuture<GetBucketLoggingResponse> getBucketLogging(GetBucketLoggingRequest request) {
        return toFuture(client -> client.getBucketLogging(request));
    }

    public CompletableFuture<GetBucketMetadataConfigurationResponse> getBucketMetadataConfiguration(GetBucketMetadataConfigurationRequest request) {
        return toFuture(client -> client.getBucketMetadataConfiguration(request));
    }

    public CompletableFuture<GetBucketMetadataTableConfigurationResponse> getBucketMetadataTableConfiguration(GetBucketMetadataTableConfigurationRequest request) {
        return toFuture(client -> client.getBucketMetadataTableConfiguration(request));
    }

    public CompletableFuture<GetBucketMetricsConfigurationResponse> getBucketMetricsConfiguration(GetBucketMetricsConfigurationRequest request) {
        return toFuture(client -> client.getBucketMetricsConfiguration(request));
    }

    public CompletableFuture<GetBucketNotificationConfigurationResponse> getBucketNotificationConfiguration(GetBucketNotificationConfigurationRequest request) {
        return toFuture(client -> client.getBucketNotificationConfiguration(request));
    }

    public CompletableFuture<GetBucketOwnershipControlsResponse> getBucketOwnershipControls(GetBucketOwnershipControlsRequest request) {
        return toFuture(client -> client.getBucketOwnershipControls(request));
    }

    public CompletableFuture<GetBucketPolicyResponse> getBucketPolicy(GetBucketPolicyRequest request) {
        return toFuture(client -> client.getBucketPolicy(request));
    }

    public CompletableFuture<GetBucketPolicyStatusResponse> getBucketPolicyStatus(GetBucketPolicyStatusRequest request) {
        return toFuture(client -> client.getBucketPolicyStatus(request));
    }

    public CompletableFuture<GetBucketReplicationResponse> getBucketReplication(GetBucketReplicationRequest request) {
        return toFuture(client -> client.getBucketReplication(request));
    }

    public CompletableFuture<GetBucketRequestPaymentResponse> getBucketRequestPayment(GetBucketRequestPaymentRequest request) {
        return toFuture(client -> client.getBucketRequestPayment(request));
    }

    public CompletableFuture<GetBucketTaggingResponse> getBucketTagging(GetBucketTaggingRequest request) {
        return toFuture(client -> client.getBucketTagging(request));
    }

    public CompletableFuture<GetBucketVersioningResponse> getBucketVersioning(GetBucketVersioningRequest request) {
        return toFuture(client -> client.getBucketVersioning(request));
    }

    public CompletableFuture<GetBucketWebsiteResponse> getBucketWebsite(GetBucketWebsiteRequest request) {
        return toFuture(client -> client.getBucketWebsite(request));
    }

    public CompletableFuture<PutBucketAbacResponse> putBucketAbac(PutBucketAbacRequest request) {
        return toFuture(client -> client.putBucketAbac(request));
    }

    public CompletableFuture<PutBucketAclResponse> putBucketAcl(PutBucketAclRequest request) {
        return toFuture(client -> client.putBucketAcl(request));
    }

    public CompletableFuture<PutBucketAccelerateConfigurationResponse> putBucketAccelerateConfiguration(PutBucketAccelerateConfigurationRequest request) {
        return toFuture(client -> client.putBucketAccelerateConfiguration(request));
    }

    public CompletableFuture<PutBucketAnalyticsConfigurationResponse> putBucketAnalyticsConfiguration(PutBucketAnalyticsConfigurationRequest request) {
        return toFuture(client -> client.putBucketAnalyticsConfiguration(request));
    }

    public CompletableFuture<PutBucketCorsResponse> putBucketCors(PutBucketCorsRequest request) {
        return toFuture(client -> client.putBucketCors(request));
    }

    public CompletableFuture<PutBucketEncryptionResponse> putBucketEncryption(PutBucketEncryptionRequest request) {
        return toFuture(client -> client.putBucketEncryption(request));
    }

    public CompletableFuture<PutBucketIntelligentTieringConfigurationResponse> putBucketIntelligentTieringConfiguration(PutBucketIntelligentTieringConfigurationRequest request) {
        return toFuture(client -> client.putBucketIntelligentTieringConfiguration(request));
    }

    public CompletableFuture<PutBucketInventoryConfigurationResponse> putBucketInventoryConfiguration(PutBucketInventoryConfigurationRequest request) {
        return toFuture(client -> client.putBucketInventoryConfiguration(request));
    }

    public CompletableFuture<PutBucketLifecycleConfigurationResponse> putBucketLifecycleConfiguration(PutBucketLifecycleConfigurationRequest request) {
        return toFuture(client -> client.putBucketLifecycleConfiguration(request));
    }

    public CompletableFuture<PutBucketLoggingResponse> putBucketLogging(PutBucketLoggingRequest request) {
        return toFuture(client -> client.putBucketLogging(request));
    }

    public CompletableFuture<PutBucketMetricsConfigurationResponse> putBucketMetricsConfiguration(PutBucketMetricsConfigurationRequest request) {
        return toFuture(client -> client.putBucketMetricsConfiguration(request));
    }

    public CompletableFuture<PutBucketNotificationConfigurationResponse> putBucketNotificationConfiguration(PutBucketNotificationConfigurationRequest request) {
        return toFuture(client -> client.putBucketNotificationConfiguration(request));
    }

    public CompletableFuture<PutBucketOwnershipControlsResponse> putBucketOwnershipControls(PutBucketOwnershipControlsRequest request) {
        return toFuture(client -> client.putBucketOwnershipControls(request));
    }

    public CompletableFuture<PutBucketPolicyResponse> putBucketPolicy(PutBucketPolicyRequest request) {
        return toFuture(client -> client.putBucketPolicy(request));
    }

    public CompletableFuture<PutBucketReplicationResponse> putBucketReplication(PutBucketReplicationRequest request) {
        return toFuture(client -> client.putBucketReplication(request));
    }

    public CompletableFuture<PutBucketRequestPaymentResponse> putBucketRequestPayment(PutBucketRequestPaymentRequest request) {
        return toFuture(client -> client.putBucketRequestPayment(request));
    }

    public CompletableFuture<PutBucketTaggingResponse> putBucketTagging(PutBucketTaggingRequest request) {
        return toFuture(client -> client.putBucketTagging(request));
    }

    public CompletableFuture<PutBucketVersioningResponse> putBucketVersioning(PutBucketVersioningRequest request) {
        return toFuture(client -> client.putBucketVersioning(request));
    }

    public CompletableFuture<PutBucketWebsiteResponse> putBucketWebsite(PutBucketWebsiteRequest request) {
        return toFuture(client -> client.putBucketWebsite(request));
    }

    public CompletableFuture<DeleteBucketAnalyticsConfigurationResponse> deleteBucketAnalyticsConfiguration(DeleteBucketAnalyticsConfigurationRequest request) {
        return toFuture(client -> client.deleteBucketAnalyticsConfiguration(request));
    }

    public CompletableFuture<DeleteBucketCorsResponse> deleteBucketCors(DeleteBucketCorsRequest request) {
        return toFuture(client -> client.deleteBucketCors(request));
    }

    public CompletableFuture<DeleteBucketEncryptionResponse> deleteBucketEncryption(DeleteBucketEncryptionRequest request) {
        return toFuture(client -> client.deleteBucketEncryption(request));
    }

    public CompletableFuture<DeleteBucketIntelligentTieringConfigurationResponse> deleteBucketIntelligentTieringConfiguration(DeleteBucketIntelligentTieringConfigurationRequest request) {
        return toFuture(client -> client.deleteBucketIntelligentTieringConfiguration(request));
    }

    public CompletableFuture<DeleteBucketInventoryConfigurationResponse> deleteBucketInventoryConfiguration(DeleteBucketInventoryConfigurationRequest request) {
        return toFuture(client -> client.deleteBucketInventoryConfiguration(request));
    }

    public CompletableFuture<DeleteBucketMetadataConfigurationResponse> deleteBucketMetadataConfiguration(DeleteBucketMetadataConfigurationRequest request) {
        return toFuture(client -> client.deleteBucketMetadataConfiguration(request));
    }

    public CompletableFuture<DeleteBucketMetadataTableConfigurationResponse> deleteBucketMetadataTableConfiguration(DeleteBucketMetadataTableConfigurationRequest request) {
        return toFuture(client -> client.deleteBucketMetadataTableConfiguration(request));
    }

    public CompletableFuture<DeleteBucketMetricsConfigurationResponse> deleteBucketMetricsConfiguration(DeleteBucketMetricsConfigurationRequest request) {
        return toFuture(client -> client.deleteBucketMetricsConfiguration(request));
    }

    public CompletableFuture<DeleteBucketOwnershipControlsResponse> deleteBucketOwnershipControls(DeleteBucketOwnershipControlsRequest request) {
        return toFuture(client -> client.deleteBucketOwnershipControls(request));
    }

    public CompletableFuture<DeleteBucketPolicyResponse> deleteBucketPolicy(DeleteBucketPolicyRequest request) {
        return toFuture(client -> client.deleteBucketPolicy(request));
    }

    public CompletableFuture<DeleteBucketReplicationResponse> deleteBucketReplication(DeleteBucketReplicationRequest request) {
        return toFuture(client -> client.deleteBucketReplication(request));
    }

    public CompletableFuture<DeleteBucketTaggingResponse> deleteBucketTagging(DeleteBucketTaggingRequest request) {
        return toFuture(client -> client.deleteBucketTagging(request));
    }

    public CompletableFuture<DeleteBucketWebsiteResponse> deleteBucketWebsite(DeleteBucketWebsiteRequest request) {
        return toFuture(client -> client.deleteBucketWebsite(request));
    }

    public CompletableFuture<ListBucketAnalyticsConfigurationsResponse> listBucketAnalyticsConfigurations(ListBucketAnalyticsConfigurationsRequest request) {
        return toFuture(client -> client.listBucketAnalyticsConfigurations(request));
    }

    public CompletableFuture<ListBucketIntelligentTieringConfigurationsResponse> listBucketIntelligentTieringConfigurations(ListBucketIntelligentTieringConfigurationsRequest request) {
        return toFuture(client -> client.listBucketIntelligentTieringConfigurations(request));
    }

    public CompletableFuture<ListBucketInventoryConfigurationsResponse> listBucketInventoryConfigurations(ListBucketInventoryConfigurationsRequest request) {
        return toFuture(client -> client.listBucketInventoryConfigurations(request));
    }

    public CompletableFuture<ListBucketMetricsConfigurationsResponse> listBucketMetricsConfigurations(ListBucketMetricsConfigurationsRequest request) {
        return toFuture(client -> client.listBucketMetricsConfigurations(request));
    }

    public CompletableFuture<UpdateBucketMetadataInventoryTableConfigurationResponse> updateBucketMetadataInventoryTableConfiguration(UpdateBucketMetadataInventoryTableConfigurationRequest request) {
        return toFuture(client -> client.updateBucketMetadataInventoryTableConfiguration(request));
    }

    public CompletableFuture<UpdateBucketMetadataJournalTableConfigurationResponse> updateBucketMetadataJournalTableConfiguration(UpdateBucketMetadataJournalTableConfigurationRequest request) {
        return toFuture(client -> client.updateBucketMetadataJournalTableConfiguration(request));
    }

    public CompletableFuture<CreateBucketMetadataConfigurationResponse> createBucketMetadataConfiguration(CreateBucketMetadataConfigurationRequest request) {
        return toFuture(client -> client.createBucketMetadataConfiguration(request));
    }

    public CompletableFuture<CreateBucketMetadataTableConfigurationResponse> createBucketMetadataTableConfiguration(CreateBucketMetadataTableConfigurationRequest request) {
        return toFuture(client -> client.createBucketMetadataTableConfiguration(request));
    }
}
