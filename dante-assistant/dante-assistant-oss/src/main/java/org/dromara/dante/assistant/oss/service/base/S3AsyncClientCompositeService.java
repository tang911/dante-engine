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

import cn.herodotus.stirrup.assistant.oss.converter.result.ResponseToGetObjectAttributesResultConverter;
import cn.herodotus.stirrup.assistant.oss.converter.result.ResponseToListBucketDetailsResultConverter;
import cn.herodotus.stirrup.assistant.oss.definition.service.AbstractS3AsyncClientService;
import cn.herodotus.stirrup.assistant.oss.entity.domain.BucketDetailsDomain;
import cn.herodotus.stirrup.assistant.oss.entity.result.GetObjectAttributesResult;
import cn.herodotus.stirrup.assistant.oss.entity.result.ListBucketDetailsResult;
import cn.herodotus.stirrup.assistant.oss.enums.BucketPolicy;
import cn.herodotus.stirrup.assistant.oss.enums.BucketVersioning;
import cn.herodotus.stirrup.assistant.oss.pool.S3AsyncClientObjectPool;
import cn.hutool.v7.core.date.DateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>Description: 存储桶组合逻辑 Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/6 14:30
 */
@Service
public class S3AsyncClientCompositeService extends AbstractS3AsyncClientService {

    private static final Logger log = LoggerFactory.getLogger(S3AsyncClientCompositeService.class);

    public S3AsyncClientCompositeService(S3AsyncClientObjectPool objectPool) {
        super(objectPool);
    }


    /**
     * 获取存储桶相关信息。
     * <p>
     * 以后有需要扩展的信息，可以再这里添加.
     * <p>
     * 传递 {@link S3AsyncClient}，方便在同一个 client 中，完成所有操作
     *
     * @param client S3 异步客户端 {@link S3AsyncClient}
     * @param bucket 存储桶 {@link Bucket}
     * @return 存储桶详情 {@link BucketDetailsDomain}
     */
    private CompletableFuture<BucketDetailsDomain> getBucketDetails(S3AsyncClient client, Bucket bucket) {
        // 使用同一个客户端并行获取策略和版本信息
        CompletableFuture<GetBucketPolicyStatusResponse> policyStatusFuture = client.getBucketPolicyStatus(b -> b.bucket(bucket.name())).exceptionally(e -> {
            // 可能没有policy的情况
            log.warn("[Herodotus] |- S3 getBucketPolicyStatus for [{}] catch error.", bucket.name(), e);
            return null;
        });
        CompletableFuture<GetBucketVersioningResponse> versioningFuture = client.getBucketVersioning(b -> b.bucket(bucket.name())).exceptionally(e -> {
            // 可能没有 version 的情况
            log.warn("[Herodotus] |- S3 getBucketVersioning for [{}] catch error.", bucket.name(), e);
            return null;
        });
        CompletableFuture<GetObjectLockConfigurationResponse> objectLockFuture = client.getObjectLockConfiguration(b -> b.bucket(bucket.name())).exceptionally(e -> {
            // 可能没有 objectLock 的情况
            log.warn("[Herodotus] |- S3 getObjectLockConfiguration for [{}] catch error.", bucket.name(), e);
            return null;
        });


        return CompletableFuture.allOf(policyStatusFuture, versioningFuture, objectLockFuture)
                .thenApply(v -> {

                    GetBucketPolicyStatusResponse policyStatusResponse = policyStatusFuture.join();
                    GetBucketVersioningResponse versioningResponse = versioningFuture.join();
                    GetObjectLockConfigurationResponse objectLockResponse = objectLockFuture.join();

                    BucketDetailsDomain details = new BucketDetailsDomain();
                    details.setCreationDate(DateUtil.toLocalDateTime(bucket.creationDate()));
                    details.setBucketName(bucket.name());
                    details.setBucketRegion(bucket.bucketRegion());

                    if (ObjectUtils.isNotEmpty(policyStatusResponse) && ObjectUtils.isNotEmpty(policyStatusResponse.policyStatus())) {
                        details.setPolicy(policyStatusResponse.policyStatus().isPublic() ? BucketPolicy.PUBLIC : BucketPolicy.PRIVATE);
                    }

                    if (ObjectUtils.isNotEmpty(versioningResponse) && ObjectUtils.isNotEmpty(versioningResponse.status())) {
                        details.setVersioning(BucketVersioning.get(versioningResponse.status().name()));
                    }

                    details.setObjectLockEnabled(ObjectUtils.isNotEmpty(objectLockResponse.objectLockConfiguration()));

                    return details;
                });
    }

    /**
     * 获取包含更详细存储桶信息的列表。
     * <p>
     * 使用同一个 client 完成多项操作。
     *
     * @param client  S3 异步客户端 {@link S3AsyncClient}
     * @param request 请求 {@link ListBucketsRequest}
     * @return 存储桶详情列表 {@link ListBucketDetailsResult}
     */
    private CompletableFuture<ListBucketDetailsResult> listBucketDetails(S3AsyncClient client, ListBucketsRequest request) {
        return client.listBuckets(request)
                .thenCompose(listBucketsResponse -> {
                    // 并行获取每个bucket的详细信息
                    List<CompletableFuture<BucketDetailsDomain>> futures = listBucketsResponse.buckets().stream()
                            .map(bucket -> getBucketDetails(client, bucket))
                            .toList();

                    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                            .thenApply(v -> {
                                List<BucketDetailsDomain> details = futures.stream()
                                        .map(CompletableFuture::join)
                                        .toList();

                                Converter<ListBucketsResponse, ListBucketDetailsResult> converter = new ResponseToListBucketDetailsResultConverter(details);
                                return converter.convert(listBucketsResponse);
                            });
                });
    }

    public CompletableFuture<ListBucketDetailsResult> listBucketDetails(ListBucketsRequest request) {
        return toFuture(client -> listBucketDetails(client, request));
    }


    /**
     * 获取对象详细信息
     * <p>
     * 以后有需要扩展的信息，可以再这里添加.
     * <p>
     * 传递 {@link S3AsyncClient}，方便在同一个 client 中，完成所有操作
     *
     * @param client  S3 异步客户端 {@link S3AsyncClient}
     * @param request 获取对象属性请求参数 {@link GetObjectAttributesRequest}
     * @return 对象详情 {@link GetObjectAttributesResult}
     */
    private CompletableFuture<GetObjectAttributesResult> getObjectAttributes(S3AsyncClient client, GetObjectAttributesRequest request) {

        String bucketName = request.bucket();
        String objectName = request.key();

        return client.getObjectAttributes(request)
                .thenCompose(getObjectAttributesResponse -> {
                    // 使用同一个客户端并行获取策略和版本信息
                    CompletableFuture<GetObjectLockConfigurationResponse> objectLockFuture = client.getObjectLockConfiguration(b -> b.bucket(bucketName)).exceptionally(e -> {
                        // 可能没有 lock 的情况
                        log.warn("[Herodotus] |- S3 getObjectLockConfiguration for [{}] catch error.", bucketName, e);
                        return null;
                    });

                    CompletableFuture<GetObjectLegalHoldResponse> objectLegalHoldFuture = client.getObjectLegalHold(b -> b.bucket(bucketName).key(objectName)).exceptionally(e -> {
                        // 可能没有 legalHold 的情况
                        log.warn("[Herodotus] |- S3 getObjectLegalHold for object [{}] in bucket [{}] catch error.", objectName, bucketName, e);
                        return null;
                    });

                    CompletableFuture<GetObjectRetentionResponse> objectRetentionFuture = client.getObjectRetention(b -> b.bucket(bucketName).key(objectName)).exceptionally(e -> {
                        // 可能没有 legalHold 的情况
                        log.warn("[Herodotus] |- S3 getObjectLegalHold for object [{}] in bucket [{}] catch error.", objectName, bucketName, e);
                        return null;
                    });

                    return CompletableFuture.allOf(objectLockFuture, objectLegalHoldFuture, objectRetentionFuture)
                            .thenApply(v -> {
                                GetObjectLockConfigurationResponse objectLockResponse = objectLockFuture.join();
                                GetObjectLegalHoldResponse objectLegalHoldResponse = objectLegalHoldFuture.join();
                                GetObjectRetentionResponse objectRetentionResponse = objectRetentionFuture.join();

                                Converter<GetObjectAttributesResponse, GetObjectAttributesResult> toDetails = new ResponseToGetObjectAttributesResultConverter(bucketName, objectName, objectLockResponse, objectLegalHoldResponse, objectRetentionResponse);

                                return toDetails.convert(getObjectAttributesResponse);
                            });
                });
    }

    public CompletableFuture<GetObjectAttributesResult> getObjectAttributes(GetObjectAttributesRequest request) {
        return toFuture(client -> getObjectAttributes(client, request));
    }
}
