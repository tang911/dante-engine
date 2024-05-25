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

package cn.herodotus.engine.oauth2.authentication.configurer;

import cn.herodotus.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.herodotus.engine.oauth2.authentication.provider.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import cn.herodotus.engine.oauth2.authentication.provider.OAuth2SocialCredentialsAuthenticationProvider;
import cn.herodotus.engine.oauth2.authentication.utils.OAuth2ConfigurerUtils;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * <p>Description: 自定义 AuthenticationProvider 配置器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/9/1 15:46
 */
public class OAuth2AuthenticationProviderConfigurer extends AbstractHttpConfigurer<OAuth2AuthenticationProviderConfigurer, HttpSecurity> {

    private final SessionRegistry sessionRegistry;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final OAuth2AuthenticationProperties authenticationProperties;

    public OAuth2AuthenticationProviderConfigurer(SessionRegistry sessionRegistry, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, OAuth2AuthenticationProperties authenticationProperties) {
        this.sessionRegistry = sessionRegistry;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationProperties = authenticationProperties;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        OAuth2AuthorizationService authorizationService = OAuth2ConfigurerUtils.getAuthorizationService(httpSecurity);
        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = OAuth2ConfigurerUtils.getTokenGenerator(httpSecurity);

        OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider =
                new OAuth2ResourceOwnerPasswordAuthenticationProvider(authorizationService, tokenGenerator, userDetailsService, authenticationProperties);
        resourceOwnerPasswordAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        resourceOwnerPasswordAuthenticationProvider.setSessionRegistry(sessionRegistry);
        httpSecurity.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);

        OAuth2SocialCredentialsAuthenticationProvider socialCredentialsAuthenticationProvider =
                new OAuth2SocialCredentialsAuthenticationProvider(authorizationService, tokenGenerator, userDetailsService, authenticationProperties);
        socialCredentialsAuthenticationProvider.setSessionRegistry(sessionRegistry);
        httpSecurity.authenticationProvider(socialCredentialsAuthenticationProvider);
    }
}
