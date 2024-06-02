/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Engine.
 *
 * Herodotus Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.supplier.upms.logic.domain.generator;

import cn.herodotus.engine.data.core.identifier.AbstractUuidGenerator;
import cn.herodotus.engine.supplier.upms.logic.entity.security.SysAttribute;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Member;

/**
 * <p>Description: 自定义UUID生成器 </p>
 * <p>
 * 使得保存实体类时可以在保留主键生成策略的情况下自定义表的主键
 *
 * @author : gengwei.zheng
 * @date : 2021/8/4 3:20
 */
public class SysAttributeUuidGeneratorType extends AbstractUuidGenerator {

    public SysAttributeUuidGeneratorType(SysAttributeUuidGenerator config, Member idMember, CustomIdGeneratorCreationContext creationContext) {
        super(idMember);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (ObjectUtils.isEmpty(object)) {
            throw new HibernateException(new NullPointerException());
        }

        SysAttribute sysAttribute = (SysAttribute) object;

        if (StringUtils.isEmpty(sysAttribute.getAttributeId())) {
            return super.generate(session, object);
        } else {
            return sysAttribute.getAttributeId();
        }
    }
}
