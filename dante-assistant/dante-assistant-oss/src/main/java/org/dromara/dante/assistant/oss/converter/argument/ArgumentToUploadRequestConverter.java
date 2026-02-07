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

import cn.herodotus.stirrup.assistant.oss.entity.argument.PutObjectArgument;
import cn.herodotus.stirrup.assistant.oss.entity.argument.UploadArgument;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.nio.file.Paths;

/**
 * <p>Description: {@link UploadArgument} 转 {@link UploadRequest} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 21:16
 */
public class ArgumentToUploadRequestConverter implements Converter<UploadArgument, UploadRequest> {

    private final Converter<PutObjectArgument, PutObjectRequest> toRequest;

    public ArgumentToUploadRequestConverter() {
        this.toRequest = new ArgumentToPutObjectRequestConverter();
    }

    @Override
    public UploadRequest convert(UploadArgument source) {

        PutObjectRequest request = toRequest.convert(source);

        return UploadRequest.builder()
                .putObjectRequest(request)
                .requestBody(AsyncRequestBody.fromFile(Paths.get(source.getFile())))
                .build();
    }
}
