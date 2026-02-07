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
import cn.herodotus.stirrup.assistant.oss.entity.result.DeleteBucketResult;
import software.amazon.awssdk.services.s3.model.DeleteBucketResponse;

/**
 * <p>Description: Response 转 DeleteBucketResult 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 23:30
 */
public class ResponseToDeleteBucketResultConverter implements ResponseConverter<DeleteBucketResponse, DeleteBucketResult> {

    @Override
    public DeleteBucketResult getInstance(DeleteBucketResponse source) {
        return new DeleteBucketResult();
    }

}
