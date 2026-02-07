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

import cn.herodotus.stirrup.assistant.oss.entity.argument.GetObjectArgument;
import cn.herodotus.stirrup.assistant.oss.entity.domain.SseCustomerDomain;
import cn.herodotus.stirrup.core.utils.TimeUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

/**
 * <p>Description: GetObjectArgument 转 GetObjectRequest 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/25 17:30
 */
public class ArgumentToGetObjectRequestConverter implements Converter<GetObjectArgument, GetObjectRequest> {

    @Override
    public GetObjectRequest convert(GetObjectArgument source) {
        GetObjectRequest.Builder builder = GetObjectRequest.builder();

        builder.bucket(source.getBucketName());
        builder.key(source.getObjectName());
        builder.versionId(source.getVersionId());
        builder.requestPayer(source.getRequestPayer());
        builder.expectedBucketOwner(source.getExpectedBucketOwner());

        builder.ifMatch(source.getIfMatch());
        builder.ifNoneMatch(source.getIfNoneMatch());
        builder.ifModifiedSince(TimeUtils.toInstant(source.getIfModifiedSince()));
        builder.ifUnmodifiedSince(TimeUtils.toInstant(source.getIfUnmodifiedSince()));
        builder.range(source.getRange());
        builder.responseCacheControl(source.getResponseCacheControl());
        builder.responseContentDisposition(source.getResponseContentDisposition());
        builder.responseContentEncoding(source.getResponseContentEncoding());
        builder.responseContentLanguage(source.getResponseContentLanguage());
        builder.responseContentType(source.getResponseContentType());
        builder.responseExpires(TimeUtils.toInstant(source.getResponseExpires()));
        builder.checksumMode(source.getChecksumMode());
        builder.partNumber(source.getPartNumber());

        SseCustomerDomain sseCustomer = source.getSseCustomer();
        if (ObjectUtils.isNotEmpty(sseCustomer)) {
            builder.sseCustomerAlgorithm(sseCustomer.getSseCustomerAlgorithm());
            builder.sseCustomerKey(sseCustomer.getSseCustomerKey());
            builder.sseCustomerKeyMD5(sseCustomer.getSseCustomerKeyMD5());
        }

        return builder.build();
    }
}
