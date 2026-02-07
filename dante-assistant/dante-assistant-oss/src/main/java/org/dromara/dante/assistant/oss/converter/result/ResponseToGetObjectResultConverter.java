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

package cn.herodotus.stirrup.assistant.oss.converter.result;

import cn.herodotus.stirrup.assistant.oss.definition.converter.ResponseConverter;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ObjectLockDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PutObjectDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.SsekmsDomain;
import cn.herodotus.stirrup.assistant.oss.entity.result.GetObjectResult;
import cn.herodotus.stirrup.assistant.oss.utils.OssUtils;
import cn.herodotus.stirrup.core.utils.TimeUtils;
import cn.hutool.v7.core.date.DateFormatPool;
import cn.hutool.v7.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.util.Date;

/**
 * <p>Description: {@link GetObjectResponse} 转 {@link GetObjectResult} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 17:35
 */
public class ResponseToGetObjectResultConverter implements ResponseConverter<GetObjectResponse, GetObjectResult> {

    @Override
    public GetObjectResult getInstance(GetObjectResponse source) {
        return new GetObjectResult();
    }

    @Override
    public void prepare(GetObjectResponse source, GetObjectResult target) {
        target.setDeleteMarker(source.deleteMarker());
        target.setAcceptRanges(source.acceptRanges());
        target.setExpiration(source.expiration());
        target.setRestore(source.restore());
        target.setETag(OssUtils.unwrapETag(source.eTag()));
        target.setExpiresString(source.expiresString());
        target.setLastModified(DateUtil.toLocalDateTime(source.lastModified()));
        target.setMissingMeta(source.missingMeta());
        target.setPartsCount(source.partsCount());
        target.setReplicationStatus(source.replicationStatusAsString());
        target.setRequestCharged(source.requestChargedAsString());
        target.setVersionId(source.versionId());

        ChecksumDomain checksum = new ChecksumDomain();
        checksum.setChecksumCRC32(source.checksumCRC32());
        checksum.setChecksumCRC32C(source.checksumCRC32C());
        checksum.setChecksumSHA1(source.checksumSHA1());
        checksum.setChecksumSHA256(source.checksumSHA256());
        target.setChecksum(checksum);

        PutObjectDomain domain = new PutObjectDomain();
        domain.setBucketKeyEnabled(source.bucketKeyEnabled());
        domain.setCacheControl(source.cacheControl());

        domain.setContentDisposition(source.contentDisposition());
        domain.setContentEncoding(source.contentEncoding());
        domain.setContentLanguage(source.contentLanguage());
        domain.setContentLength(source.contentLength());
        domain.setContentType(source.contentType());

        if (StringUtils.isNotEmpty(source.expiresString())) {
            Date expires = DateFormatPool.HTTP_DATETIME_FORMAT_GMT.parse(source.expiresString());
            domain.setExpires(TimeUtils.toLocalDateTime(expires));
        }

        domain.setMetadata(source.metadata());

        domain.setServerSideEncryption(source.serverSideEncryptionAsString());

        domain.setStorageClass(source.storageClassAsString());

        SsekmsDomain ssekms = new SsekmsDomain();
        ssekms.setSsekmsEncryptionContext(ssekms.getSsekmsEncryptionContext());
        ssekms.setSsekmsKeyId(ssekms.getSsekmsKeyId());
        ssekms.setSseCustomerAlgorithm(ssekms.getSseCustomerAlgorithm());
        ssekms.setSseCustomerKey(ssekms.getSseCustomerKey());
        ssekms.setSseCustomerKeyMD5(ssekms.getSseCustomerKeyMD5());
        domain.setSsekms(ssekms);

        ObjectLockDomain objectLock = new ObjectLockDomain();
        objectLock.setObjectLockLegalHoldStatus(objectLock.getObjectLockLegalHoldStatus());
        objectLock.setObjectLockMode(objectLock.getObjectLockMode());
        objectLock.setObjectLockRetainUntilDate(objectLock.getObjectLockRetainUntilDate());
        domain.setObjectLock(objectLock);

        target.setMetadata(domain);

        ResponseConverter.super.prepare(source, target);
    }
}
