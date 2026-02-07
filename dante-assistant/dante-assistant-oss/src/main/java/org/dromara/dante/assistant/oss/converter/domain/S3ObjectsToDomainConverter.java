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

import cn.herodotus.stirrup.assistant.oss.entity.domain.ObjectDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.OwnerDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.RestoreStatusDomain;
import cn.herodotus.stirrup.assistant.oss.utils.OssUtils;
import cn.herodotus.stirrup.spring.founction.ListConverter;
import cn.hutool.v7.core.date.DateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.Owner;
import software.amazon.awssdk.services.s3.model.RestoreStatus;
import software.amazon.awssdk.services.s3.model.S3Object;

/**
 * <p>Description: {@link S3Object} 转 {@link ObjectDomain} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/15 15:38
 */
public class S3ObjectsToDomainConverter implements ListConverter<S3Object, ObjectDomain> {

    private final Converter<Owner, OwnerDomain> toOwnerResult;
    private final Converter<RestoreStatus, RestoreStatusDomain> toRestoreStatusResult;
    private final String delimiter;

    public S3ObjectsToDomainConverter(String delimiter) {
        this.delimiter = delimiter;
        this.toOwnerResult = new OwnerToDomainConverter();
        this.toRestoreStatusResult = new RestoreStatusToDomainConverter();
    }

    @Override
    public ObjectDomain from(S3Object source) {
        ObjectDomain target = new ObjectDomain();
        target.setChecksumAlgorithm(source.checksumAlgorithmAsStrings());
        target.setETag(OssUtils.unwrapETag(source.eTag()));
        target.setObjectName(source.key());
        target.setLastModified(DateUtil.toLocalDateTime(source.lastModified()));
        target.setOwner(ObjectUtils.isNotEmpty(source.owner()) ? toOwnerResult.convert(source.owner()) : null);
        target.setRestoreStatus(ObjectUtils.isNotEmpty(source.restoreStatus()) ? toRestoreStatusResult.convert(source.restoreStatus()) : null);
        target.setSize(source.size());
        target.setStorageClass(source.storageClassAsString());
        target.setDir(StringUtils.isNotBlank(this.delimiter) && Strings.CS.contains(source.key(), this.delimiter));
        return target;
    }
}
