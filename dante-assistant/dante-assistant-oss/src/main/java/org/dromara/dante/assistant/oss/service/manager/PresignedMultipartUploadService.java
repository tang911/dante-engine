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

package cn.herodotus.stirrup.assistant.oss.service.manager;

import cn.herodotus.stirrup.assistant.oss.converter.OssProxyAddressConverter;
import cn.herodotus.stirrup.assistant.oss.entity.business.CreateMultipartUploadBusiness;
import cn.herodotus.stirrup.assistant.oss.properties.OssProperties;
import cn.herodotus.stirrup.assistant.oss.service.base.S3AsyncClientMultipartUploadService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.ListPartsRequest;
import software.amazon.awssdk.services.s3.model.ListPartsResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * <p>Description: 客户端大文件分片上传逻辑 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/30 0:24
 */
@Service
public class PresignedMultipartUploadService {

    private final S3AsyncClientMultipartUploadService s3AsyncClientMultipartUploadService;
    private final PresignedUrlService presignedUrlService;
    private final Converter<String, String> toProxy;

    public PresignedMultipartUploadService(OssProperties ossProperties, S3AsyncClientMultipartUploadService s3AsyncClientMultipartUploadService, PresignedUrlService presignedUrlService) {
        this.s3AsyncClientMultipartUploadService = s3AsyncClientMultipartUploadService;
        this.presignedUrlService = presignedUrlService;
        this.toProxy = new OssProxyAddressConverter(ossProperties);
    }

    /**
     * 第一步：创建分片上传请求, 返回 UploadId
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return 大文件分片上传 UploadId
     */
    public CompletableFuture<CreateMultipartUploadResponse> createUploadId(String bucketName, String objectName) {
        // 直接使用原生请求对象，避免反复转换
        CreateMultipartUploadRequest request = CreateMultipartUploadRequest.builder()
                .bucket(bucketName)
                .key(objectName)
                .build();

        return s3AsyncClientMultipartUploadService.createMultipartUpload(request);
    }

    /**
     * 第二步：创建文件预上传地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param uploadId   第一步中创建的 UploadId
     * @param partNumber 分片号
     * @return 预上传地址
     */
    public String createPresignedObjectUrl(String bucketName, String objectName, String uploadId, int partNumber) {

        Map<String, String> metadata = new HashMap<>();
        metadata.put("partNumber", String.valueOf(partNumber));
        metadata.put("uploadId", uploadId);

        return presignedUrlService.createUploadPresignedUrl(bucketName, objectName, metadata);
    }

    /**
     * 第三步：获取指定 uploadId 下所有的分片文件
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param uploadId   第一步中创建的 UploadId
     * @return uploadId 对应的所有分片
     */
    public CompletableFuture<ListPartsResponse> listParts(String bucketName, String objectName, String uploadId) {

        ListPartsRequest request = ListPartsRequest.builder()
                .bucket(bucketName)
                .key(objectName)
                .uploadId(uploadId)
                .build();
        return s3AsyncClientMultipartUploadService.listParts(request);
    }

    /**
     * 创建大文件分片上传
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param totalParts 分片总数
     * @return {@link CreateMultipartUploadBusiness}
     */
    public CreateMultipartUploadBusiness createMultipartUpload(String bucketName, String objectName, String uploadId, int totalParts) {
        CreateMultipartUploadBusiness entity = new CreateMultipartUploadBusiness(uploadId);

        // 从 1 开始才能保证 Minio 正确上传。
        for (int i = 1; i <= totalParts; i++) {
            String uploadUrl = createPresignedObjectUrl(bucketName, objectName, uploadId, i);
            entity.append(toProxy.convert(uploadUrl));
        }

        return entity;
    }
}
