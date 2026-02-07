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

import cn.herodotus.stirrup.assistant.oss.entity.argument.UploadDirectoryArgument;
import cn.herodotus.stirrup.assistant.oss.entity.argument.UploadFileArgument;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.transfer.s3.model.UploadDirectoryRequest;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

import java.nio.file.Paths;
import java.util.Objects;

/**
 * <p>Description: {@link UploadDirectoryArgument} 转 {@link UploadDirectoryRequest} 转换器  </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 21:01
 */
public class ArgumentToUploadDirectoryRequestConverter implements Converter<UploadDirectoryArgument, UploadDirectoryRequest> {

    private final Converter<UploadFileArgument, UploadFileRequest> toRequest;

    public ArgumentToUploadDirectoryRequestConverter() {
        this.toRequest = new ArgumentToUploadFileRequestConverter();
    }

    @Override
    public UploadDirectoryRequest convert(UploadDirectoryArgument source) {

        UploadFileRequest request = toRequest.convert(source.getArgument());

        return UploadDirectoryRequest.builder()
                .source(Paths.get(source.getSource()))
                .bucket(source.getBucketName())
                .s3Prefix(source.getPrefix())
                .s3Delimiter(source.getDelimiter())
                .followSymbolicLinks(source.getFollowSymbolicLinks())
                .maxDepth(source.getMaxDepth())
                .uploadFileRequestTransformer(r -> {
                    r.putObjectRequest(Objects.requireNonNull(request).putObjectRequest());
                    r.source(request.source());
                })
                .build();
    }
}
