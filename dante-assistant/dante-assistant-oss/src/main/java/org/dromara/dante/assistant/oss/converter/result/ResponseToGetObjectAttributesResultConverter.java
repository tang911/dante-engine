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

import cn.herodotus.stirrup.assistant.oss.entity.result.GetObjectAttributesResult;
import cn.herodotus.stirrup.assistant.oss.enums.ObjectRetentionMode;
import cn.hutool.v7.core.date.DateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.*;

/**
 * <p>Description: {@link GetObjectAttributesResponse} 转 {@link GetObjectAttributesResult} 转换器</p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/6 21:33
 */
public class ResponseToGetObjectAttributesResultConverter implements Converter<GetObjectAttributesResponse, GetObjectAttributesResult> {

    private final String bucketName;
    private final String objectName;
    private final GetObjectLockConfigurationResponse objectLockResponse;
    private final GetObjectLegalHoldResponse objectLegalHoldResponse;
    private final GetObjectRetentionResponse objectRetentionResponse;

    public ResponseToGetObjectAttributesResultConverter(String bucketName, String objectName, GetObjectLockConfigurationResponse objectLockResponse, GetObjectLegalHoldResponse objectLegalHoldResponse, GetObjectRetentionResponse objectRetentionResponse) {
        this.bucketName = bucketName;
        this.objectName = objectName;
        this.objectLockResponse = objectLockResponse;
        this.objectLegalHoldResponse = objectLegalHoldResponse;
        this.objectRetentionResponse = objectRetentionResponse;
    }


    @Override
    public GetObjectAttributesResult convert(GetObjectAttributesResponse source) {

        GetObjectAttributesResult details = new GetObjectAttributesResult();
        details.setBucketName(bucketName);
        details.setObjectName(objectName);
        details.setLockEnabled(ObjectUtils.isNotEmpty(this.objectLockResponse.objectLockConfiguration()));

        if (ObjectUtils.isNotEmpty(objectRetentionResponse.retention())) {
            if (ObjectUtils.isNotEmpty(objectRetentionResponse.retention().mode())) {
                details.setRetentionMode(ObjectRetentionMode.get(objectRetentionResponse.retention().mode().name()));
            }
            details.setRetainUntilDate(DateUtil.toLocalDateTime(objectRetentionResponse.retention().retainUntilDate()));
        }

        if (ObjectUtils.isNotEmpty(objectLegalHoldResponse.legalHold()) && ObjectUtils.isNotEmpty(objectLegalHoldResponse.legalHold().status())) {
            details.setLockLegalHold(objectLegalHoldResponse.legalHold().status() == ObjectLockLegalHoldStatus.ON);
        }

        details.setDeleteMarker(source.deleteMarker());
        details.setLastModified(DateUtil.toLocalDateTime(source.lastModified()));
        details.setVersionId(source.versionId());
        details.setTag(source.eTag());
        details.setSize(source.objectSize());

        return details;
    }
}
