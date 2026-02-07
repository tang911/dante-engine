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

import cn.herodotus.stirrup.assistant.oss.entity.argument.PutObjectRetentionArgument;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.ObjectLockRetention;
import software.amazon.awssdk.services.s3.model.ObjectLockRetentionMode;
import software.amazon.awssdk.services.s3.model.PutObjectRetentionRequest;

import java.time.Instant;

/**
 * <p>Description: {@link PutObjectRetentionArgument} 转 {@link PutObjectRetentionRequest} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/6 23:16
 */
public class ArgumentToPutObjectRetentionRequestConverter implements Converter<PutObjectRetentionArgument, PutObjectRetentionRequest> {

    @Override
    public PutObjectRetentionRequest convert(PutObjectRetentionArgument source) {

        ObjectLockRetention retention = ObjectLockRetention.builder()
                .mode(ObjectLockRetentionMode.fromValue(source.getRetentionMode().name()))
                .retainUntilDate(Instant.from(source.getRetainUntilDate()))
                .build();

        return PutObjectRetentionRequest.builder()
                .bucket(source.getBucketName())
                .key(source.getObjectName())
                .retention(retention)
                .build();
    }
}
