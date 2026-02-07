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

package cn.herodotus.stirrup.assistant.oss.converter.argument;

import cn.herodotus.stirrup.assistant.oss.entity.argument.PutObjectArgument;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ObjectLockDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PutObjectDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.SsekmsDomain;
import cn.herodotus.stirrup.core.utils.TimeUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * <p>Description: PutObjectArgument 转 PutObjectRequest 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/25 17:41
 */
public class ArgumentToPutObjectRequestConverter implements Converter<PutObjectArgument, PutObjectRequest> {
    @Override
    public PutObjectRequest convert(PutObjectArgument source) {

        PutObjectRequest.Builder builder = PutObjectRequest.builder();
        builder.bucket(source.getBucketName());
        builder.key(source.getObjectName());
        builder.requestPayer(source.getRequestPayer());
        builder.expectedBucketOwner(source.getExpectedBucketOwner());
        builder.acl(source.getAcl());
        builder.checksumAlgorithm(source.getChecksumAlgorithm());

        builder.grantFullControl(source.getGrantFullControl());
        builder.grantRead(source.getGrantRead());
        builder.grantReadACP(source.getGrantReadACP());
        builder.grantWriteACP(source.getGrantWriteACP());
        builder.websiteRedirectLocation(source.getWebsiteRedirectLocation());
        builder.tagging(source.getTagging());

        ChecksumDomain checksum = source.getChecksum();
        if (ObjectUtils.isNotEmpty(checksum)) {
            builder.checksumCRC32(checksum.getChecksumCRC32());
            builder.checksumCRC32C(checksum.getChecksumCRC32C());
            builder.checksumSHA1(checksum.getChecksumSHA1());
            builder.checksumSHA256(checksum.getChecksumSHA256());
        }

        PutObjectDomain domain = source.getMetadata();

        builder.cacheControl(domain.getCacheControl());
        builder.contentDisposition(domain.getContentDisposition());
        builder.contentEncoding(domain.getContentEncoding());
        builder.contentLanguage(domain.getContentLanguage());
        builder.contentLength(domain.getContentLength());
        builder.contentType(domain.getContentType());
        builder.expires(TimeUtils.toInstant(domain.getExpires()));
        builder.metadata(domain.getMetadata());
        builder.serverSideEncryption(domain.getServerSideEncryption());
        builder.storageClass(domain.getStorageClass());
        builder.bucketKeyEnabled(domain.getBucketKeyEnabled());

        SsekmsDomain ssekms = domain.getSsekms();
        if (ObjectUtils.isNotEmpty(ssekms)) {
            builder.ssekmsEncryptionContext(ssekms.getSsekmsEncryptionContext());
            builder.ssekmsKeyId(ssekms.getSsekmsKeyId());
            builder.sseCustomerAlgorithm(ssekms.getSseCustomerAlgorithm());
            builder.sseCustomerKey(ssekms.getSseCustomerKey());
            builder.sseCustomerKeyMD5(ssekms.getSseCustomerKeyMD5());
        }

        ObjectLockDomain objectLock = domain.getObjectLock();
        if (ObjectUtils.isNotEmpty(objectLock)) {
            builder.objectLockLegalHoldStatus(objectLock.getObjectLockLegalHoldStatus());
            builder.objectLockMode(objectLock.getObjectLockMode());
            builder.objectLockRetainUntilDate(TimeUtils.toInstant(objectLock.getObjectLockRetainUntilDate()));
        }

        return builder.build();
    }
}
