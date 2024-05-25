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

package cn.herodotus.engine.assistant.autoconfigure;

import cn.herodotus.engine.assistant.autoconfigure.customizer.Jackson2DefaultObjectMapperBuilderCustomizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * <p>Description: Jackson2 配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/6/1 0:09
 */
@AutoConfiguration
public class Jackson2AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(Jackson2AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Jackson2] Auto Configure.");
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer defaultObjectMapperBuilderCustomizer() {
        Jackson2DefaultObjectMapperBuilderCustomizer customizer = new Jackson2DefaultObjectMapperBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [Jackson2 Default ObjectMapper Builder Customizer] Auto Configure.");
        return customizer;
    }

    /**
     * 转换器全局配置
     *
     * @return MappingJackson2HttpMessageConverter
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        log.trace("[Herodotus] |- Bean [Jackson2 Http Message Converter] Auto Configure.");
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Configuration(proxyBeanMethods = false)
    @ComponentScan({
            "cn.herodotus.engine.assistant.core.json.jackson2.utils"
    })
    static class JacksonUtilsConfiguration {

        @PostConstruct
        public void postConstruct() {
            log.debug("[Herodotus] |- SDK [Assistant Jackson2 Utils] Auto Configure.");
        }
    }
}
