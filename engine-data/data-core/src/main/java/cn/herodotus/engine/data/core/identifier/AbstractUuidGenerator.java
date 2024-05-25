/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Dante Engine.
 *
 * Dante Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dante Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.data.core.identifier;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.factory.spi.StandardGenerator;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.hibernate.type.descriptor.java.UUIDJavaType;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * <p>Description: 基于 Hibernate 6 的自定义 Uuid 生成器 </p>
 * <p>
 * 主要解决手动设置实体 ID 不生效问题。
 *
 * @author : gengwei.zheng
 * @date : 2022/11/7 17:15
 */
public abstract class AbstractUuidGenerator implements IdentifierGenerator, StandardGenerator {

    private final StandardRandomStrategy generator;
    private final UUIDJavaType.ValueTransformer valueTransformer;

    public AbstractUuidGenerator(Member idMember) {
        generator = StandardRandomStrategy.INSTANCE;

        final Class<?> propertyType;
        if (idMember instanceof Method) {
            propertyType = ((Method) idMember).getReturnType();
        } else {
            propertyType = ((Field) idMember).getType();
        }

        if (UUID.class.isAssignableFrom(propertyType)) {
            valueTransformer = UUIDJavaType.PassThroughTransformer.INSTANCE;
        } else if (String.class.isAssignableFrom(propertyType)) {
            valueTransformer = UUIDJavaType.ToStringTransformer.INSTANCE;
        } else if (byte[].class.isAssignableFrom(propertyType)) {
            valueTransformer = UUIDJavaType.ToBytesTransformer.INSTANCE;
        } else {
            throw new HibernateException("Unanticipated return type [" + propertyType.getName() + "] for UUID conversion");
        }
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return valueTransformer.transform(generator.generateUuid(session));
    }
}
