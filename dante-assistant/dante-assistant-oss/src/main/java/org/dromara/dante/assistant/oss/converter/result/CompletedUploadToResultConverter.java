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

import cn.herodotus.stirrup.assistant.oss.entity.result.PutObjectResult;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.transfer.s3.model.CompletedUpload;

/**
 * <p>Description: {@link CompletedUpload} 转 {@link PutObjectResult} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 21:25
 */
public class CompletedUploadToResultConverter implements Converter<CompletedUpload, PutObjectResult> {

    private final Converter<PutObjectResponse, PutObjectResult> toResult;

    public CompletedUploadToResultConverter() {
        this.toResult = new ResponseToPutObjectResultConverter();
    }

    @Override
    public PutObjectResult convert(CompletedUpload source) {
        return toResult.convert(source.response());
    }
}
