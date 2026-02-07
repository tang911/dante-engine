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

import cn.herodotus.stirrup.assistant.oss.definition.service.AbstractS3PresignerService;
import cn.herodotus.stirrup.assistant.oss.pool.S3PresignerObjectPool;
import cn.herodotus.stirrup.assistant.oss.properties.OssProperties;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.*;

import java.net.URL;
import java.time.Duration;
import java.util.Map;

/**
 * <p>Description: 预签名处理 Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/10/19 16:46
 */
@Service
public class PresignedUrlService extends AbstractS3PresignerService {

    private static final Logger log = LoggerFactory.getLogger(PresignedUrlService.class);

    private final OssProperties ossProperties;

    public PresignedUrlService(S3PresignerObjectPool objectPool, OssProperties ossProperties) {
        super(objectPool);
        this.ossProperties = ossProperties;
    }

    /**
     * 创建下载预签名地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param expiration 有效期 {@link Duration}
     * @return 预签名地址
     */
    public String createDownloadPresignedUrl(String bucketName, String objectName, Duration expiration) {

        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectName)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(expiration)
                .getObjectRequest(objectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = process(presigner -> presigner.presignGetObject(presignRequest));

        URL url = presignedRequest.url();
        log.debug("[Herodotus] |- PreSigned URL to download a file with HTTP method [{}] to: [{}]", presignedRequest.httpRequest().method(), url.toString());
        return url.toExternalForm();
    }

    /**
     * 创建下载预签名地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return 预签名地址
     */
    public String createDownloadPresignedUrl(String bucketName, String objectName) {
        return createDownloadPresignedUrl(bucketName, objectName, ossProperties.getPreSignedExpires());
    }

    /**
     * 创建上传预签名地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param expiration 有效期 {@link Duration}
     * @param metadata   元数据 {@link Map}
     * @return 预签名地址
     */
    public String createUploadPresignedUrl(String bucketName, String objectName, Duration expiration, Map<String, String> metadata) {

        PutObjectRequest.Builder request = PutObjectRequest.builder();
        request.bucket(bucketName);
        request.key(objectName);
        if (MapUtils.isNotEmpty(metadata)) {
            request.metadata(metadata);
        }

        PutObjectPresignRequest preSignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(expiration)
                .putObjectRequest(request.build())
                .build();

        PresignedPutObjectRequest preSignedRequest = process(presigner -> presigner.presignPutObject(preSignRequest));

        URL url = preSignedRequest.url();
        log.debug("[Herodotus] |- PreSigned URL to upload a file with HTTP method [{}] to: [{}]", preSignedRequest.httpRequest().method(), url.toString());
        return url.toExternalForm();
    }

    /**
     * 创建上传预签名地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param metadata   元数据 {@link Map}
     * @return 预签名地址
     */
    public String createUploadPresignedUrl(String bucketName, String objectName, Map<String, String> metadata) {
        return createUploadPresignedUrl(bucketName, objectName, ossProperties.getPreSignedExpires(), metadata);
    }

    /**
     * 创建上传预签名地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return 预签名地址
     */
    public String createUploadPresignedUrl(String bucketName, String objectName) {
        return createUploadPresignedUrl(bucketName, objectName, null);
    }

    /**
     * 创建上传预签名地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param expiration 有效期 {@link Duration}
     * @return 预签名地址
     */
    public String createDeletePresignedUrl(String bucketName, String objectName, Duration expiration) {

        DeleteObjectRequest.Builder request = DeleteObjectRequest.builder();
        request.bucket(bucketName);
        request.key(objectName);

        DeleteObjectPresignRequest preSignRequest = DeleteObjectPresignRequest.builder()
                .signatureDuration(expiration)
                .deleteObjectRequest(request.build())
                .build();

        PresignedDeleteObjectRequest preSignedRequest = process(presigner -> presigner.presignDeleteObject(preSignRequest));

        URL url = preSignedRequest.url();
        log.debug("[Herodotus] |- PreSigned URL to delete a file with HTTP method [{}] to: [{}]", preSignedRequest.httpRequest().method(), url.toString());
        return url.toExternalForm();
    }

    /**
     * 创建上传预签名地址
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return 预签名地址
     */
    public String createDeletePresignedUrl(String bucketName, String objectName) {
        return createDeletePresignedUrl(bucketName, objectName, ossProperties.getPreSignedExpires());
    }
}
