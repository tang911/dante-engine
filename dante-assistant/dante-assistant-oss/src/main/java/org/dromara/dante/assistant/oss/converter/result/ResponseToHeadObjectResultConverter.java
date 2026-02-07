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
import cn.herodotus.stirrup.assistant.oss.entity.result.HeadObjectResult;
import cn.herodotus.stirrup.assistant.oss.utils.OssUtils;
import cn.hutool.v7.core.date.DateFormatPool;
import cn.hutool.v7.core.date.DateUtil;
import cn.hutool.v7.core.date.TimeUtil;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;

/**
 * <p>Description: HeadObjectResponse 转 HeadObjectResult 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/25 18:09
 */
public class ResponseToHeadObjectResultConverter implements ResponseConverter<HeadObjectResponse, HeadObjectResult> {

    @Override
    public HeadObjectResult getInstance(HeadObjectResponse source) {
        return new HeadObjectResult();
    }

    @Override
    public void prepare(HeadObjectResponse source, HeadObjectResult target) {

        target.setAcceptRanges(source.acceptRanges());
        target.setArchiveStatus(source.archiveStatusAsString());
        target.setETag(OssUtils.unwrapETag(source.eTag()));
        target.setExpiration(source.expiration());
        target.setExpiresString(source.expiresString());
        target.setLastModified(DateUtil.toLocalDateTime(source.lastModified()));
        target.setMissingMeta(source.missingMeta());
        target.setPartsCount(source.partsCount());
        target.setReplicationStatus(source.replicationStatusAsString());
        target.setRestore(source.restore());
        target.setDeleteMarker(source.deleteMarker());
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
        domain.setExpires(TimeUtil.parse(source.expiresString(), DateFormatPool.NORM_DATETIME_PATTERN));
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
