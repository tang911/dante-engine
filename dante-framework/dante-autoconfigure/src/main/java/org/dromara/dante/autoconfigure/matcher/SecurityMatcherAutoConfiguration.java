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

package org.dromara.dante.autoconfigure.matcher;

import org.dromara.dante.core.builder.SecurityMatcher;
import org.dromara.dante.core.function.SecurityMatcherBuilderCustomizer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * <p>Description: Security Matcher 自动配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2025/12/13 18:43
 */
@AutoConfiguration
public class SecurityMatcherAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecurityMatcherAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Auto [Security Matcher] Configure.");
    }

    @Bean
    public SecurityMatcherBuilderCustomizer standardSecurityMatcherBuilderCustomizer() {
        StandardSecurityMatcherBuilderCustomizer customizer = new StandardSecurityMatcherBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [Standard Security Matcher Builder Customizer] Configure.");
        return customizer;
    }

    @Bean
    public SecurityMatcher securityMatcherBuilder(List<SecurityMatcherBuilderCustomizer> customizers) {
        SecurityMatcher.Builder builder = SecurityMatcher.builder();
        customize(builder, customizers);
        log.trace("[Herodotus] |- Bean [Security Matcher Builder] Configure.");
        return builder.build();
    }

    private void customize(SecurityMatcher.Builder builder, List<SecurityMatcherBuilderCustomizer> customizers) {
        for (SecurityMatcherBuilderCustomizer customizer : customizers) {
            customizer.customize(builder);
        }
    }
}
