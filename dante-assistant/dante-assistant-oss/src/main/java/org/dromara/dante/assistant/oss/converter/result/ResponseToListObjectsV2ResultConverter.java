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

import cn.herodotus.stirrup.assistant.oss.converter.domain.CommonPrefixesToDomainConverter;
import cn.herodotus.stirrup.assistant.oss.converter.domain.S3ObjectsToDomainConverter;
import cn.herodotus.stirrup.assistant.oss.definition.converter.ResponseConverter;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ObjectDomain;
import cn.herodotus.stirrup.assistant.oss.entity.result.ListObjectsV2Result;
import cn.herodotus.stirrup.spring.founction.ListConverter;
import org.apache.commons.collections4.CollectionUtils;
import software.amazon.awssdk.services.s3.model.CommonPrefix;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: AWS OSS API 响应转换为 ListObjectsV2Result 转换器</p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/15 16:10
 */
public class ResponseToListObjectsV2ResultConverter implements ResponseConverter<ListObjectsV2Response, ListObjectsV2Result> {

    @Override
    public void prepare(ListObjectsV2Response source, ListObjectsV2Result target) {
        target.setBucketName(source.name());
        target.setContents(getContents(source));
        target.setContinuationToken(source.continuationToken());
        target.setDelimiter(source.delimiter());
        target.setEncodingType(source.encodingTypeAsString());
        target.setTruncated(source.isTruncated());
        target.setKeyCount(source.keyCount());
        target.setMaxKeys(source.maxKeys());
        target.setNextContinuationToken(source.nextContinuationToken());
        target.setPrefix(source.prefix());
        target.setStartAfter(source.startAfter());
        ResponseConverter.super.prepare(source, target);
    }

    @Override
    public ListObjectsV2Result getInstance(ListObjectsV2Response source) {
        return new ListObjectsV2Result();
    }

    private List<ObjectDomain> getContents(ListObjectsV2Response source) {
        ListConverter<CommonPrefix, ObjectDomain> toCommonPrefixesResult = new CommonPrefixesToDomainConverter();
        ListConverter<S3Object, ObjectDomain> toS3ObjectsResult = new S3ObjectsToDomainConverter(source.delimiter());

        List<ObjectDomain> results = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(source.commonPrefixes())) {
            List<ObjectDomain> commonPrefixes = toCommonPrefixesResult.convert(source.commonPrefixes());
            if (CollectionUtils.isNotEmpty(commonPrefixes)) {
                results.addAll(commonPrefixes);
            }
        }

        if (CollectionUtils.isNotEmpty(source.contents())) {
            List<ObjectDomain> contents = toS3ObjectsResult.convert(source.contents());
            if (CollectionUtils.isNotEmpty(contents)) {
                results.addAll(contents);
            }
        }

        return results;
    }
}
