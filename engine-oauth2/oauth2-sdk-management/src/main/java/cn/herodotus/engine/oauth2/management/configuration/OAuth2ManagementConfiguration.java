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

package cn.herodotus.engine.oauth2.management.configuration;

import cn.herodotus.engine.oauth2.authentication.configuration.OAuth2AuthenticationConfiguration;
import cn.herodotus.engine.oauth2.authentication.stamp.SignInFailureLimitedStampManager;
import cn.herodotus.engine.oauth2.data.jpa.configuration.OAuth2DataJpaConfiguration;
import cn.herodotus.engine.oauth2.management.compliance.listener.AuthenticationSuccessListener;
import cn.herodotus.engine.oauth2.management.response.OAuth2DeviceVerificationResponseHandler;
import cn.herodotus.engine.oauth2.management.response.OidcClientRegistrationResponseHandler;
import cn.herodotus.engine.oauth2.management.service.OAuth2ComplianceService;
import cn.herodotus.engine.oauth2.management.service.OAuth2DeviceService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Description: OAuth2 Manager 模块配置 </p>
 * <p>
 * {@link org.springframework.security.oauth2.jwt.JwtTimestampValidator}
 *
 * @author : gengwei.zheng
 * @date : 2022/2/26 12:35
 */
@Configuration(proxyBeanMethods = false)
@Import({OAuth2DataJpaConfiguration.class, OAuth2AuthenticationConfiguration.class, OAuth2ComplianceConfiguration.class})
@EntityScan(basePackages = {
        "cn.herodotus.engine.oauth2.management.entity"
})
@EnableJpaRepositories(basePackages = {
        "cn.herodotus.engine.oauth2.management.repository",
})
@ComponentScan(basePackages = {
        "cn.herodotus.engine.oauth2.management.service",
        "cn.herodotus.engine.oauth2.management.controller",
})
public class OAuth2ManagementConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ManagementConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- Module [OAuth2 Management] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationSuccessListener authenticationSuccessListener(SignInFailureLimitedStampManager stampManager, OAuth2ComplianceService complianceService, OAuth2DeviceService deviceService) {
        AuthenticationSuccessListener listener = new AuthenticationSuccessListener(stampManager, complianceService);
        log.trace("[Herodotus] |- Bean [OAuth2 Authentication Success Listener] Auto Configure.");
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2DeviceVerificationResponseHandler oauth2DeviceVerificationResponseHandler(OAuth2DeviceService oauth2DeviceService) {
        OAuth2DeviceVerificationResponseHandler handler = new OAuth2DeviceVerificationResponseHandler(oauth2DeviceService);
        log.trace("[Herodotus] |- Bean [OAuth2 Device Verification Response Handler] Auto Configure.");
        return handler;
    }

    @Bean
    @ConditionalOnMissingBean
    public OidcClientRegistrationResponseHandler oidcClientRegistrationResponseHandler(OAuth2DeviceService oauth2DeviceService) {
        OidcClientRegistrationResponseHandler handler = new OidcClientRegistrationResponseHandler(oauth2DeviceService);
        log.trace("[Herodotus] |- Bean [Oidc Client Registration Response Handler] Auto Configure.");
        return handler;
    }

}
