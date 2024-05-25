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

package cn.herodotus.engine.data.core.condition;

import cn.herodotus.engine.assistant.core.context.PropertyResolver;
import cn.herodotus.engine.data.core.constants.DataConstants;
import cn.herodotus.engine.data.core.enums.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 以 MongoDB 作为基础核心应用数据存储条件 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/9 10:47
 */
public class MongoDBDataSourceCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(MongoDBDataSourceCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = PropertyResolver.getProperty(conditionContext, DataConstants.ITEM_DATA_DATA_SOURCE);
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, DataSource.MONGODB.name());
        log.debug("[Herodotus] |- Condition [MongoDB Data Source] value is [{}]", result);
        return result;
    }
}
