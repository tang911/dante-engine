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

import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PartDomain;
import cn.herodotus.stirrup.assistant.oss.utils.OssUtils;
import cn.herodotus.stirrup.spring.founction.ListConverter;
import cn.hutool.v7.core.date.DateUtil;
import software.amazon.awssdk.services.s3.model.Part;

/**
 * <p>Description: Part 转 Domain 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 0:26
 */
public class PartToDomainConverter implements ListConverter<Part, PartDomain> {

    @Override
    public PartDomain from(Part source) {

        PartDomain target = new PartDomain();
        target.setLastModified(DateUtil.toLocalDateTime(source.lastModified()));
        target.setSize(source.size());

        ChecksumDomain domain = new ChecksumDomain();
        domain.setChecksumCRC32(source.checksumCRC32());
        domain.setChecksumCRC32C(source.checksumCRC32C());
        domain.setChecksumSHA1(source.checksumSHA1());
        domain.setChecksumSHA256(source.checksumSHA256());

        target.setChecksum(domain);
        target.setETag(OssUtils.unwrapETag(source.eTag()));
        target.setPartNumber(source.partNumber());

        return target;
    }
}
