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

package cn.herodotus.engine.message.mqtt.condition;

import cn.herodotus.engine.assistant.core.context.PropertyResolver;
import cn.herodotus.engine.message.core.constants.MessageConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Mqtt 开启判断条件 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/11/2 13:44
 */
public class MqttEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(MqttEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        String username = PropertyResolver.getProperty(conditionContext, MessageConstants.ITEM_MQTT_USERNAME);
        String password = PropertyResolver.getProperty(conditionContext, MessageConstants.ITEM_MQTT_PASSWORD);
        boolean result = StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password);
        log.debug("[Herodotus] |- Condition [Mqtt Enabled] value is [{}]", result);
        return result;
    }
}
