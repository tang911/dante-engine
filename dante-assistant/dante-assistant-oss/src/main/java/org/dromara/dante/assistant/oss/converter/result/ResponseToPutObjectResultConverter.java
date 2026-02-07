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
import cn.herodotus.stirrup.assistant.oss.entity.domain.SsekmsDomain;
import cn.herodotus.stirrup.assistant.oss.entity.result.PutObjectResult;
import cn.herodotus.stirrup.assistant.oss.utils.OssUtils;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

/**
 * <p>Description: PutObjectResponse 转 PutObjectResult 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/25 23:45
 */
public class ResponseToPutObjectResultConverter implements ResponseConverter<PutObjectResponse, PutObjectResult> {

    @Override
    public void prepare(PutObjectResponse source, PutObjectResult target) {

        target.setExpiration(source.expiration());
        target.setETag(OssUtils.unwrapETag(source.eTag()));
        target.setVersionId(source.versionId());

        ChecksumDomain checksum = new ChecksumDomain();
        checksum.setChecksumCRC32(source.checksumCRC32());
        checksum.setChecksumCRC32C(source.checksumCRC32C());
        checksum.setChecksumSHA1(source.checksumSHA1());
        checksum.setChecksumSHA256(source.checksumSHA256());
        target.setChecksum(checksum);

        SsekmsDomain ssekms = new SsekmsDomain();
        ssekms.setSsekmsEncryptionContext(source.ssekmsEncryptionContext());
        ssekms.setSsekmsKeyId(source.ssekmsKeyId());
        ssekms.setSseCustomerAlgorithm(source.sseCustomerAlgorithm());
        ssekms.setSseCustomerKeyMD5(source.sseCustomerKeyMD5());
        target.setSsekms(ssekms);

        ResponseConverter.super.prepare(source, target);
    }

    @Override
    public PutObjectResult getInstance(PutObjectResponse source) {
        return new PutObjectResult();
    }
}
