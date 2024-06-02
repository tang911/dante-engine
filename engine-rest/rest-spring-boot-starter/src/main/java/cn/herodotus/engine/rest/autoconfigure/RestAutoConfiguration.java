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

package cn.herodotus.engine.rest.autoconfigure;

import cn.herodotus.engine.assistant.core.context.ServiceContextHolder;
import cn.herodotus.engine.assistant.definition.function.ErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.rest.autoconfigure.customizer.Jackson2XssObjectMapperBuilderCustomizer;
import cn.herodotus.engine.rest.autoconfigure.customizer.RestErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.rest.condition.constants.RestPropertyFinder;
import cn.herodotus.engine.rest.condition.properties.EndpointProperties;
import cn.herodotus.engine.rest.condition.properties.PlatformProperties;
import cn.herodotus.engine.rest.service.initializer.ServiceContextHolderBuilder;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Rest 自动注入 </p>
 *
 * {@code RestAutoConfiguration} 注入的时机比较早，所以在此进行 {@link ServiceContextHolder} 的初始化。
 * 如果在其它地方注入 {@link ServiceContextHolder} 因为时机过完，会导致未完成初始化即被使用，出现抛错的问题。
 *
 * @author : gengwei.zheng
 * @date : 2022/1/19 23:16
 */
@AutoConfiguration
@EnableConfigurationProperties({EndpointProperties.class, PlatformProperties.class})
public class RestAutoConfiguration implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(RestAutoConfiguration.class);

    private final ServiceContextHolder serviceContextHolder;

    public RestAutoConfiguration(EndpointProperties endpointProperties, PlatformProperties platformProperties, ServerProperties serverProperties) {
        this.serviceContextHolder = ServiceContextHolderBuilder.builder()
                .endpointProperties(endpointProperties)
                .platformProperties(platformProperties)
                .serverProperties(serverProperties)
                .build();
        log.info("[Herodotus] |- Module [Rest Starter] Auto Configure.");
    }

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        this.serviceContextHolder.setApplicationContext(applicationContext);
        this.serviceContextHolder.setApplicationName(RestPropertyFinder.getApplicationName(applicationContext));
        log.debug("[Herodotus] |- HERODOTUS ApplicationContext initialization completed.");
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer xssObjectMapperBuilderCustomizer() {
        Jackson2XssObjectMapperBuilderCustomizer customizer = new Jackson2XssObjectMapperBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [Jackson2 Xss ObjectMapper Builder Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer restErrorCodeMapperBuilderCustomizer() {
        RestErrorCodeMapperBuilderCustomizer customizer = new RestErrorCodeMapperBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [Rest ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
