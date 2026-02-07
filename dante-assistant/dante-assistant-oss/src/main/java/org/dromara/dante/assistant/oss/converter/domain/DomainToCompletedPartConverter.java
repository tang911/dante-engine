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

package cn.herodotus.stirrup.assistant.oss.converter.domain;

import cn.herodotus.stirrup.assistant.oss.entity.domain.CompletedPartDomain;
import cn.herodotus.stirrup.assistant.oss.utils.OssUtils;
import cn.herodotus.stirrup.spring.founction.ListConverter;
import org.apache.commons.lang3.ObjectUtils;
import software.amazon.awssdk.services.s3.model.CompletedPart;

/**
 * <p>Description: CompletedPartDomain 转 CompletedPart 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 17:03
 */
public class DomainToCompletedPartConverter implements ListConverter<CompletedPartDomain, CompletedPart> {

    @Override
    public CompletedPart from(CompletedPartDomain source) {

        CompletedPart.Builder builder = CompletedPart.builder();
        builder.eTag(OssUtils.unwrapETag(source.getETag()));
        builder.partNumber(source.getPartNumber());

        if (ObjectUtils.isNotEmpty(source.getChecksum())) {
            builder.checksumCRC32(source.getChecksum().getChecksumCRC32());
            builder.checksumCRC32C(source.getChecksum().getChecksumCRC32C());
            builder.checksumSHA1(source.getChecksum().getChecksumSHA1());
            builder.checksumSHA256(source.getChecksum().getChecksumSHA256());
        }

        return builder.build();
    }
}
