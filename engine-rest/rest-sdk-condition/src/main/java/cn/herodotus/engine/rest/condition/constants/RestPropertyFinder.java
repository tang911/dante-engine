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

package cn.herodotus.engine.rest.condition.constants;


import cn.herodotus.engine.assistant.core.context.PropertyResolver;
import cn.herodotus.engine.assistant.definition.constants.BaseConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ConditionContext;

/**
 * <p>Description: 策略模块配置获取器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/2/1 19:23
 */
public class RestPropertyFinder {

    public static String getApplicationName(ApplicationContext applicationContext) {
        return PropertyResolver.getProperty(applicationContext.getEnvironment(), BaseConstants.ITEM_SPRING_APPLICATION_NAME);
    }

    public static String getCryptoStrategy(ConditionContext conditionContext, String defaultValue) {
        return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PROTECT_CRYPTO_STRATEGY, defaultValue);
    }

    public static String getCryptoStrategy(ConditionContext conditionContext) {
        return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PROTECT_CRYPTO_STRATEGY);
    }

    public static boolean isScanEnabled(ConditionContext conditionContext, boolean defaultValue) {
        return PropertyResolver.getBoolean(conditionContext, RestConstants.ITEM_SCAN_ENABLED, defaultValue);
    }

    public static boolean isOpenFeignHttp2ClientEnabled(ConditionContext conditionContext) {
        return PropertyResolver.getBoolean(conditionContext, RestConstants.ITEM_OPENFEIGN_HTTP2CLIENT_ENABLED);
    }

    public static boolean isOpenFeignHttpClient5Enabled(ConditionContext conditionContext) {
        return PropertyResolver.getBoolean(conditionContext, RestConstants.ITEM_OPENFEIGN_HTTPCLIENT5_ENABLED);
    }

    public static String getDataAccessStrategy(ConditionContext conditionContext, String defaultValue) {
        return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PLATFORM_DATA_ACCESS_STRATEGY, defaultValue);
    }

    public static String getDataAccessStrategy(ConditionContext conditionContext) {
        return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PLATFORM_DATA_ACCESS_STRATEGY);
    }

    public static String getArchitecture(ConditionContext conditionContext, String defaultValue) {
        return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PLATFORM_ARCHITECTURE, defaultValue);
    }

    public static String getArchitecture(ConditionContext conditionContext) {
        return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PLATFORM_ARCHITECTURE);
    }
}
