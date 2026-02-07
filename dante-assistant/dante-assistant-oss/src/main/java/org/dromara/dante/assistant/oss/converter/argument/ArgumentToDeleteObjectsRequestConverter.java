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

import cn.herodotus.stirrup.assistant.oss.converter.domain.DomainToObjectIdentifiersConverter;
import cn.herodotus.stirrup.assistant.oss.entity.argument.DeleteObjectsArgument;
import cn.herodotus.stirrup.assistant.oss.entity.domain.DeletedDomain;
import cn.herodotus.stirrup.spring.founction.ListConverter;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;

/**
 * <p>Description: DeleteObjectsArgument 转 DeleteObjectsRequest 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 16:27
 */
public class ArgumentToDeleteObjectsRequestConverter implements Converter<DeleteObjectsArgument, DeleteObjectsRequest> {

    private final ListConverter<DeletedDomain, ObjectIdentifier> toObjectIdentifier;

    public ArgumentToDeleteObjectsRequestConverter() {
        this.toObjectIdentifier = new DomainToObjectIdentifiersConverter();
    }

    @Override
    public DeleteObjectsRequest convert(DeleteObjectsArgument source) {

        return DeleteObjectsRequest.builder()
                .bucket(source.getBucketName())
                .mfa(source.getMfa())
                .requestPayer(source.getRequestPayer())
                .bypassGovernanceRetention(source.getBypassGovernanceRetention())
                .expectedBucketOwner(source.getExpectedBucketOwner())
                .checksumAlgorithm(source.getChecksumAlgorithm())
                .delete(toDelete(source))
                .build();
    }

    private Delete toDelete(DeleteObjectsArgument source) {
        return Delete.builder()
                .objects(toObjectIdentifier.convert(source.getDelete()))
                .quiet(source.getQuiet())
                .build();
    }
}
