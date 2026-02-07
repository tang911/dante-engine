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
import cn.herodotus.stirrup.assistant.oss.entity.domain.SsekmsDomain;
import cn.herodotus.stirrup.assistant.oss.entity.result.CreateMultipartUploadResult;
import cn.hutool.v7.core.date.DateUtil;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;

/**
 * <p>Description: Response 转 Result 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 0:11
 */
public class ResponseToCreateMultipartUploadResultConverter implements ResponseConverter<CreateMultipartUploadResponse, CreateMultipartUploadResult> {
    @Override
    public CreateMultipartUploadResult getInstance(CreateMultipartUploadResponse source) {
        return new CreateMultipartUploadResult();
    }

    @Override
    public void prepare(CreateMultipartUploadResponse source, CreateMultipartUploadResult target) {

        target.setAbortDate(DateUtil.toLocalDateTime(source.abortDate()));
        target.setAbortRuleId(source.abortRuleId());
        target.setBucketName(source.bucket());
        target.setObjectName(source.key());
        target.setServerSideEncryption(source.serverSideEncryptionAsString());
        target.setBucketKeyEnabled(source.bucketKeyEnabled());
        target.setRequestCharged(source.requestChargedAsString());
        target.setUploadId(source.uploadId());
        target.setChecksumAlgorithm(source.checksumAlgorithmAsString());

        SsekmsDomain ssekms = new SsekmsDomain();
        ssekms.setSsekmsEncryptionContext(source.ssekmsEncryptionContext());
        ssekms.setSsekmsKeyId(source.ssekmsKeyId());
        ssekms.setSseCustomerAlgorithm(source.sseCustomerAlgorithm());
        ssekms.setSseCustomerKeyMD5(source.sseCustomerKeyMD5());
        target.setSsekms(ssekms);

        ResponseConverter.super.prepare(source, target);
    }
}
