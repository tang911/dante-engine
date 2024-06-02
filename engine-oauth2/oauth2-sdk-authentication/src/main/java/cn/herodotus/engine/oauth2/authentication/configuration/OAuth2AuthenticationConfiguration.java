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

package cn.herodotus.engine.oauth2.authentication.configuration;

import cn.herodotus.engine.assistant.definition.function.ErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.oauth2.authentication.customizer.HerodotusJwtTokenCustomizer;
import cn.herodotus.engine.oauth2.authentication.customizer.HerodotusOpaqueTokenCustomizer;
import cn.herodotus.engine.oauth2.authentication.customizer.OAuth2ErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.oauth2.authentication.customizer.OAuth2FormLoginConfigurerCustomizer;
import cn.herodotus.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.herodotus.engine.oauth2.authentication.stamp.LockedUserDetailsStampManager;
import cn.herodotus.engine.oauth2.authentication.stamp.SignInFailureLimitedStampManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * <p>Description: OAuth2 认证基础模块配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/13 15:40
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({OAuth2AuthenticationProperties.class})
public class OAuth2AuthenticationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AuthenticationConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [OAuth2 Authentication] Auto Configure.");
    }

    @Bean
    public LockedUserDetailsStampManager lockedUserDetailsStampManager(OAuth2AuthenticationProperties authenticationProperties) {
        LockedUserDetailsStampManager manager = new LockedUserDetailsStampManager(authenticationProperties);
        log.trace("[Herodotus] |- Bean [Locked UserDetails Stamp Manager] Auto Configure.");
        return manager;
    }

    @Bean
    public SignInFailureLimitedStampManager signInFailureLimitedStampManager(OAuth2AuthenticationProperties authenticationProperties) {
        SignInFailureLimitedStampManager manager = new SignInFailureLimitedStampManager(authenticationProperties);
        log.trace("[Herodotus] |- Bean [SignIn Failure Limited Stamp Manager] Auto Configure.");
        return manager;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2FormLoginConfigurerCustomizer oauth2FormLoginConfigurerCustomer(OAuth2AuthenticationProperties authenticationProperties) {
        OAuth2FormLoginConfigurerCustomizer configurer = new OAuth2FormLoginConfigurerCustomizer(authenticationProperties);
        log.trace("[Herodotus] |- Bean [OAuth2 FormLogin Configurer Customer] Auto Configure.");
        return configurer;
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        HerodotusJwtTokenCustomizer customizer = new HerodotusJwtTokenCustomizer();
        log.trace("[Herodotus] |- Bean [OAuth2 Jwt Token Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public OAuth2TokenCustomizer<OAuth2TokenClaimsContext> opaqueTokenCustomizer() {
        HerodotusOpaqueTokenCustomizer customizer = new HerodotusOpaqueTokenCustomizer();
        log.trace("[Herodotus] |- Bean [OAuth2 Opaque Token Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer oauth2ErrorCodeMapperBuilderCustomizer() {
        OAuth2ErrorCodeMapperBuilderCustomizer customizer = new OAuth2ErrorCodeMapperBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [OAuth2 ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
