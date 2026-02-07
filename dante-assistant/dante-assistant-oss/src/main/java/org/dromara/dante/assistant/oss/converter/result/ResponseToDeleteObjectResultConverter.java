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
import cn.herodotus.stirrup.assistant.oss.entity.result.DeleteObjectResult;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;

/**
 * <p>Description: DeleteObjectResponse 转 DeleteObjectResult 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/25 18:30
 */
public class ResponseToDeleteObjectResultConverter implements ResponseConverter<DeleteObjectResponse, DeleteObjectResult> {

    @Override
    public DeleteObjectResult getInstance(DeleteObjectResponse source) {
        return new DeleteObjectResult();
    }

    @Override
    public void prepare(DeleteObjectResponse source, DeleteObjectResult target) {

        target.setDeleteMarker(source.deleteMarker());
        target.setVersionId(source.versionId());
        target.setRequestCharged(source.requestChargedAsString());

        ResponseConverter.super.prepare(source, target);
    }
}
