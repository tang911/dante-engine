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

package cn.herodotus.engine.access.justauth.configuration;

import cn.herodotus.engine.access.justauth.annotation.ConditionalOnJustAuthEnabled;
import cn.herodotus.engine.access.justauth.processor.JustAuthAccessHandler;
import cn.herodotus.engine.access.justauth.processor.JustAuthProcessor;
import cn.herodotus.engine.access.justauth.properties.JustAuthProperties;
import cn.herodotus.engine.access.justauth.stamp.JustAuthStateStampManager;
import cn.herodotus.engine.assistant.core.enums.AccountType;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: JustAuth配置 </p>
 * <p>
 * 仅在存在herodotus.platform.social.justauth.configs配置的情况下才注入
 *
 * @author : gengwei.zheng
 * @date : 2021/5/22 11:25
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnJustAuthEnabled
@EnableConfigurationProperties(JustAuthProperties.class)
public class AccessJustAuthConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessJustAuthConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[Herodotus] |- SDK [Access Just Auth] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public JustAuthStateStampManager justAuthStateStampManager(JustAuthProperties justAuthProperties) {
        JustAuthStateStampManager justAuthStateStampManager = new JustAuthStateStampManager();
        justAuthStateStampManager.setJustAuthProperties(justAuthProperties);
        log.trace("[Herodotus] |- Bean [Just Auth State Redis Cache] Auto Configure.");
        return justAuthStateStampManager;
    }

    @Bean
    @ConditionalOnBean(JustAuthStateStampManager.class)
    @ConditionalOnMissingBean
    public JustAuthProcessor justAuthProcessor(JustAuthStateStampManager justAuthStateStampManager, JustAuthProperties justAuthProperties) {
        JustAuthProcessor justAuthProcessor = new JustAuthProcessor();
        justAuthProcessor.setJustAuthStateRedisCache(justAuthStateStampManager);
        justAuthProcessor.setJustAuthProperties(justAuthProperties);
        log.trace("[Herodotus] |- Bean [Just Auth Request Generator] Auto Configure.");
        return justAuthProcessor;
    }

    @Bean(AccountType.JUST_AUTH_HANDLER)
    @ConditionalOnBean(JustAuthProcessor.class)
    @ConditionalOnMissingBean
    public JustAuthAccessHandler justAuthAccessHandler(JustAuthProcessor justAuthProcessor) {
        JustAuthAccessHandler justAuthAccessHandler = new JustAuthAccessHandler(justAuthProcessor);
        log.debug("[Herodotus] |- Bean [Just Auth Access Handler] Auto Configure.");
        return justAuthAccessHandler;
    }
}
