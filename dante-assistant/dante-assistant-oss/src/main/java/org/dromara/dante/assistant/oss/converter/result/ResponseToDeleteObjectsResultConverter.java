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

import cn.herodotus.stirrup.assistant.oss.converter.domain.DeletedObjectsToDomainConverter;
import cn.herodotus.stirrup.assistant.oss.converter.domain.S3ErrorToDomainConverter;
import cn.herodotus.stirrup.assistant.oss.definition.converter.ResponseConverter;
import cn.herodotus.stirrup.assistant.oss.entity.domain.DeletedObjectDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.S3ErrorDomain;
import cn.herodotus.stirrup.assistant.oss.entity.result.DeleteObjectsResult;
import cn.herodotus.stirrup.spring.founction.ListConverter;
import org.apache.commons.collections4.CollectionUtils;
import software.amazon.awssdk.services.s3.model.DeleteObjectsResponse;
import software.amazon.awssdk.services.s3.model.DeletedObject;
import software.amazon.awssdk.services.s3.model.S3Error;

import java.util.List;

/**
 * <p>Description: Response 转 Result 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 0:53
 */
public class ResponseToDeleteObjectsResultConverter implements ResponseConverter<DeleteObjectsResponse, DeleteObjectsResult> {

    private final ListConverter<DeletedObject, DeletedObjectDomain> toDeletedObject;
    private final ListConverter<S3Error, S3ErrorDomain> toS3Error;

    public ResponseToDeleteObjectsResultConverter() {
        this.toDeletedObject = new DeletedObjectsToDomainConverter();
        this.toS3Error = new S3ErrorToDomainConverter();
    }

    @Override
    public DeleteObjectsResult getInstance(DeleteObjectsResponse source) {
        return new DeleteObjectsResult();
    }

    @Override
    public void prepare(DeleteObjectsResponse source, DeleteObjectsResult target) {
        target.setDeleted(CollectionUtils.isNotEmpty(source.deleted()) ? toDeletedObject.convert(source.deleted()) : List.of());
        target.setErrors(CollectionUtils.isNotEmpty(source.errors()) ? toS3Error.convert(source.errors()) : List.of());
        target.setRequestCharged(source.requestChargedAsString());
        ResponseConverter.super.prepare(source, target);
    }
}
