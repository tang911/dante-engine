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

import cn.herodotus.stirrup.assistant.oss.entity.argument.UploadPartArgument;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.UploadPartRequest;

/**
 * <p>Description: {@link UploadPartArgument} 转 {@link UploadPartRequest} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 17:29
 */
public class ArgumentToUploadPartRequestConverter implements Converter<UploadPartArgument, UploadPartRequest> {

    @Override
    public UploadPartRequest convert(UploadPartArgument source) {

        UploadPartRequest.Builder builder = UploadPartRequest.builder();

        builder.bucket(source.getBucketName());
        builder.key(source.getObjectName());
        builder.contentLength(source.getContentLength());
        builder.contentMD5(source.getContentMD5());
        builder.checksumAlgorithm(source.getChecksumAlgorithm());
        builder.partNumber(source.getPartNumber());
        builder.uploadId(source.getUploadId());
        builder.requestPayer(source.getRequestPayer());
        builder.expectedBucketOwner(source.getExpectedBucketOwner());
        builder.sdkPartType(source.getSdkPartType());

        if (ObjectUtils.isNotEmpty(source.getSse())) {
            builder.sseCustomerAlgorithm(source.getSse().getSseCustomerAlgorithm());
            builder.sseCustomerKey(source.getSse().getSseCustomerKey());
            builder.sseCustomerKeyMD5(source.getSse().getSseCustomerKeyMD5());
        }

        if (ObjectUtils.isNotEmpty(source.getChecksum())) {
            builder.checksumCRC32(source.getChecksum().getChecksumCRC32());
            builder.checksumCRC32C(source.getChecksum().getChecksumCRC32C());
            builder.checksumSHA1(source.getChecksum().getChecksumSHA1());
            builder.checksumSHA256(source.getChecksum().getChecksumSHA256());
        }

        return builder.build();
    }
}
