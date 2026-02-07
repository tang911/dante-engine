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

import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ObjectLockDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PutObjectDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.SsekmsDomain;
import cn.herodotus.stirrup.assistant.oss.entity.result.FailedFileUploadResult;
import cn.herodotus.stirrup.spring.founction.ListConverter;
import cn.hutool.v7.core.date.DateUtil;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.model.FailedFileUpload;

import java.nio.file.Path;

/**
 * <p>Description: {@link FailedFileUpload} 转 {@link FailedFileUploadResult} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 21:43
 */
public class FailedFileUploadToResultConverter implements ListConverter<FailedFileUpload, FailedFileUploadResult> {
    @Override
    public FailedFileUploadResult from(FailedFileUpload source) {

        FailedFileUploadResult target = new FailedFileUploadResult();

        PutObjectRequest request = source.request().putObjectRequest();
        Path path = source.request().source();

        target.setException(source.exception());
        target.setFile(path.toString());
        target.setAcl(request.aclAsString());

        ChecksumDomain checksum = new ChecksumDomain();
        checksum.setChecksumCRC32(request.checksumCRC32());
        checksum.setChecksumCRC32C(request.checksumCRC32C());
        checksum.setChecksumSHA1(request.checksumSHA1());
        checksum.setChecksumSHA256(request.checksumSHA256());
        target.setChecksum(checksum);

        target.setChecksumAlgorithm(request.checksumAlgorithmAsString());
        target.setGrantFullControl(request.grantFullControl());
        target.setGrantRead(request.grantRead());
        target.setGrantReadACP(request.grantReadACP());
        target.setGrantWriteACP(request.grantWriteACP());

        PutObjectDomain putObject = new PutObjectDomain();
        putObject.setBucketKeyEnabled(request.bucketKeyEnabled());
        putObject.setCacheControl(request.cacheControl());
        putObject.setContentDisposition(request.contentDisposition());
        putObject.setContentEncoding(request.contentEncoding());
        putObject.setContentLanguage(request.contentLanguage());
        putObject.setContentLength(request.contentLength());
        putObject.setContentType(request.contentType());
        putObject.setExpires(DateUtil.toLocalDateTime(request.expires()));
        putObject.setMetadata(request.metadata());

        ObjectLockDomain objectLock = new ObjectLockDomain();
        objectLock.setObjectLockLegalHoldStatus(request.objectLockLegalHoldStatusAsString());
        objectLock.setObjectLockMode(request.objectLockModeAsString());
        objectLock.setObjectLockRetainUntilDate(DateUtil.toLocalDateTime(request.objectLockRetainUntilDate()));
        putObject.setObjectLock(objectLock);

        putObject.setServerSideEncryption(request.serverSideEncryptionAsString());

        SsekmsDomain ssekms = new SsekmsDomain();
        ssekms.setSsekmsEncryptionContext(request.ssekmsEncryptionContext());
        ssekms.setSsekmsKeyId(request.ssekmsKeyId());
        ssekms.setSseCustomerAlgorithm(request.sseCustomerAlgorithm());
        ssekms.setSseCustomerKey(request.sseCustomerKey());
        ssekms.setSseCustomerKeyMD5(request.sseCustomerKeyMD5());
        putObject.setSsekms(ssekms);

        putObject.setStorageClass(request.storageClassAsString());

        target.setMetadata(putObject);
        target.setTagging(request.tagging());
        target.setWebsiteRedirectLocation(request.websiteRedirectLocation());
        target.setObjectName(request.key());
        target.setRequestPayer(request.requestPayerAsString());
        target.setExpectedBucketOwner(request.expectedBucketOwner());
        target.setBucketName(request.bucket());

        return target;
    }
}
