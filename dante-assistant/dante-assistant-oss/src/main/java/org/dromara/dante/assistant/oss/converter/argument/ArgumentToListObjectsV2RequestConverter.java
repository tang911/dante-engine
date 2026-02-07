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

import cn.herodotus.stirrup.assistant.oss.entity.argument.ListObjectsV2Argument;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;

/**
 * <p>Description: ListObjectsV2Argument 转换为 ListObjectsV2Request Converter</p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/15 14:26
 */
public class ArgumentToListObjectsV2RequestConverter implements Converter<ListObjectsV2Argument, ListObjectsV2Request> {

    @Override
    public ListObjectsV2Request convert(ListObjectsV2Argument argument) {

        return ListObjectsV2Request.builder()
                .bucket(argument.getBucketName())
                .delimiter(argument.getDelimiter())
                .encodingType(argument.getEncodingType())
                .maxKeys(argument.getMaxKeys())
                .prefix(argument.getPrefix())
                .continuationToken(argument.getContinuationToken())
                .fetchOwner(argument.getFetchOwner())
                .startAfter(argument.getStartAfter())
                .requestPayer(argument.getRequestPayer())
                .build();
    }
}
