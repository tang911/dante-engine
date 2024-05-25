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

package cn.herodotus.engine.rest.condition.definition;

import cn.herodotus.engine.rest.condition.constants.RestPropertyFinder;
import cn.herodotus.engine.rest.core.enums.CryptoStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 国密算法策略条件 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/5/3 22:57
 */
public class SMCryptoCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(SMCryptoCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = RestPropertyFinder.getCryptoStrategy(conditionContext, CryptoStrategy.SM.name());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, CryptoStrategy.SM.name());
        log.debug("[Herodotus] |- Condition [SM Crypto Strategy] value is [{}]", result);
        return result;
    }
}
