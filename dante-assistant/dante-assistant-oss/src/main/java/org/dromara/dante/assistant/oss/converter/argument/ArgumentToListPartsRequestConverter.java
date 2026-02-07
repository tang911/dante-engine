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

import cn.herodotus.stirrup.assistant.oss.entity.argument.ListPartsArgument;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.ListPartsRequest;

/**
 * <p>Description: {@link ListPartsArgument} 转 {@link ListPartsRequest} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 17:23
 */
public class ArgumentToListPartsRequestConverter implements Converter<ListPartsArgument, ListPartsRequest> {
    @Override
    public ListPartsRequest convert(ListPartsArgument source) {

        ListPartsRequest.Builder builder = ListPartsRequest.builder();

        builder.bucket(source.getBucketName());
        builder.key(source.getObjectName());
        builder.maxParts(source.getMaxParts());
        builder.uploadId(source.getUploadId());
        builder.requestPayer(source.getRequestPayer());
        builder.expectedBucketOwner(source.getExpectedBucketOwner());

        if (ObjectUtils.isNotEmpty(source.getSse())) {
            builder.sseCustomerAlgorithm(source.getSse().getSseCustomerAlgorithm());
            builder.sseCustomerKey(source.getSse().getSseCustomerKey());
            builder.sseCustomerKeyMD5(source.getSse().getSseCustomerKeyMD5());
        }

        return builder.build();
    }

}
