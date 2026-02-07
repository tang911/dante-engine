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

import cn.herodotus.stirrup.assistant.oss.entity.argument.ResumableFileUploadArgument;
import cn.herodotus.stirrup.assistant.oss.entity.argument.UploadFileArgument;
import cn.hutool.v7.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.transfer.s3.model.ResumableFileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

/**
 * <p>Description: {@link ResumableFileUploadArgument} 转 {@link ResumableFileUpload} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 18:16
 */
public class ArgumentToResumableFileUploadConverter implements Converter<ResumableFileUploadArgument, ResumableFileUpload> {

    private final Converter<UploadFileArgument, UploadFileRequest> toRequest;

    public ArgumentToResumableFileUploadConverter() {
        this.toRequest = new ArgumentToUploadFileRequestConverter();
    }

    @Override
    public ResumableFileUpload convert(ResumableFileUploadArgument source) {

        UploadFileRequest request = toRequest.convert(source);

        return ResumableFileUpload.builder()
                .uploadFileRequest(request)
                .multipartUploadId(source.getUploadId())
                .fileLastModified(DateUtil.toInstant(source.getLastModified()))
                .partSizeInBytes(source.getPartSize())
                .totalParts(source.getTotalParts())
                .fileLength(source.getFileLength())
                .transferredParts(source.getTransferredParts())
                .build();
    }
}
