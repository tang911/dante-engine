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

import cn.herodotus.stirrup.assistant.oss.entity.argument.WriteGetObjectResponseArgument;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ObjectLockDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.SsekmsDomain;
import cn.herodotus.stirrup.core.utils.TimeUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.WriteGetObjectResponseRequest;

/**
 * <p>Description: {@link WriteGetObjectResponseArgument} 转 {@link WriteGetObjectResponseRequest} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/25 17:58
 */
public class ArgumentToWriteGetObjectResponseRequestConverter implements Converter<WriteGetObjectResponseArgument, WriteGetObjectResponseRequest> {
    @Override
    public WriteGetObjectResponseRequest convert(WriteGetObjectResponseArgument source) {

        WriteGetObjectResponseRequest.Builder builder = WriteGetObjectResponseRequest.builder();


        builder.requestRoute(source.getRequestRoute());
        builder.requestToken(source.getRequestToken());
        builder.statusCode(source.getStatusCode());
        builder.errorCode(source.getErrorCode());
        builder.errorMessage(source.getErrorMessage());
        builder.acceptRanges(source.getAcceptRanges());
        builder.deleteMarker(source.getDeleteMarker());
        builder.eTag(source.getETag());
        builder.expiration(source.getExpiration());
        builder.lastModified(TimeUtils.toInstant(source.getLastModified()));
        builder.missingMeta(source.getMissingMeta());
        builder.partsCount(source.getPartsCount());
        builder.replicationStatus(source.getReplicationStatus());
        builder.requestCharged(source.getRequestCharged());
        builder.restore(source.getRestore());
        builder.versionId(source.getVersionId());
        builder.tagCount(source.getTagCount());

        builder.cacheControl(source.getCacheControl());
        builder.contentDisposition(source.getContentDisposition());
        builder.contentEncoding(source.getContentEncoding());
        builder.contentLanguage(source.getContentLanguage());
        builder.contentLength(source.getContentLength());
        builder.contentType(source.getContentType());
        builder.expires(TimeUtils.toInstant(source.getExpires()));
        builder.metadata(source.getMetadata());
        builder.serverSideEncryption(source.getServerSideEncryption());
        builder.storageClass(source.getStorageClass());
        builder.bucketKeyEnabled(source.getBucketKeyEnabled());

        ChecksumDomain checksum = source.getChecksum();
        if (ObjectUtils.isNotEmpty(checksum)) {
            builder.checksumCRC32(checksum.getChecksumCRC32());
            builder.checksumCRC32C(checksum.getChecksumCRC32C());
            builder.checksumSHA1(checksum.getChecksumSHA1());
            builder.checksumSHA256(checksum.getChecksumSHA256());
        }

        SsekmsDomain ssekms = source.getSsekms();
        if (ObjectUtils.isNotEmpty(ssekms)) {
            builder.ssekmsKeyId(ssekms.getSsekmsKeyId());
            builder.sseCustomerAlgorithm(ssekms.getSseCustomerAlgorithm());
            builder.sseCustomerKeyMD5(ssekms.getSseCustomerKeyMD5());
        }

        ObjectLockDomain objectLock = source.getObjectLock();
        if (ObjectUtils.isNotEmpty(objectLock)) {
            builder.objectLockLegalHoldStatus(objectLock.getObjectLockLegalHoldStatus());
            builder.objectLockMode(objectLock.getObjectLockMode());
            builder.objectLockRetainUntilDate(TimeUtils.toInstant(objectLock.getObjectLockRetainUntilDate()));
        }

        return builder.build();
    }
}
