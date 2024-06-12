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

package cn.herodotus.engine.oauth2.authentication.configurer;

import cn.herodotus.engine.captcha.core.processor.CaptchaRendererFactory;
import cn.herodotus.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.herodotus.engine.oauth2.authentication.provider.OAuth2FormLoginAuthenticationProvider;
import cn.herodotus.engine.oauth2.authentication.response.OAuth2FormLoginAuthenticationFailureHandler;
import cn.herodotus.engine.rest.protect.crypto.processor.HttpCryptoProcessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * <p>Description: OAuth2 Form Login Configurer </p>
 * <p>
 * 使用此种方式，相当于额外增加了一种表单登录方式。因此对原有的 http.formlogin进行的配置，对当前此种方式的配置并不生效。
 *
 * @author : gengwei.zheng
 * @date : 2022/4/12 13:29
 * @see org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer
 */
public class OAuth2FormLoginSecureConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<OAuth2FormLoginSecureConfigurer<H>, H> {

    private final UserDetailsService userDetailsService;
    private final OAuth2AuthenticationProperties authenticationProperties;
    private final CaptchaRendererFactory captchaRendererFactory;
    private final HttpCryptoProcessor httpCryptoProcessor;

    public OAuth2FormLoginSecureConfigurer(UserDetailsService userDetailsService, OAuth2AuthenticationProperties authenticationProperties, CaptchaRendererFactory captchaRendererFactory, HttpCryptoProcessor httpCryptoProcessor) {
        this.userDetailsService = userDetailsService;
        this.authenticationProperties = authenticationProperties;
        this.captchaRendererFactory = captchaRendererFactory;
        this.httpCryptoProcessor = httpCryptoProcessor;
    }

    @Override
    public void configure(H httpSecurity) throws Exception {

        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        SecurityContextRepository securityContextRepository = httpSecurity.getSharedObject(SecurityContextRepository.class);

        OAuth2FormLoginAuthenticationFilter filter = getOAuth2FormLoginAuthenticationFilter(authenticationManager, this.httpCryptoProcessor, securityContextRepository);

        OAuth2FormLoginAuthenticationProvider provider = new OAuth2FormLoginAuthenticationProvider(captchaRendererFactory);
        provider.setUserDetailsService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false);

        httpSecurity
                .authenticationProvider(provider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    private OAuth2FormLoginAuthenticationFilter getOAuth2FormLoginAuthenticationFilter(AuthenticationManager authenticationManager, HttpCryptoProcessor httpCryptoProcessor, SecurityContextRepository securityContextRepository) {
        OAuth2FormLoginAuthenticationFilter filter = new OAuth2FormLoginAuthenticationFilter(authenticationManager, httpCryptoProcessor);
        filter.setUsernameParameter(getFormLogin().getUsernameParameter());
        filter.setPasswordParameter(getFormLogin().getPasswordParameter());
        filter.setAuthenticationDetailsSource(new OAuth2FormLoginWebAuthenticationDetailSource(authenticationProperties, httpCryptoProcessor));
        filter.setAuthenticationFailureHandler(new OAuth2FormLoginAuthenticationFailureHandler(getFormLogin().getFailureUrl()));
        filter.setSecurityContextRepository(securityContextRepository);
        return filter;
    }

    private OAuth2AuthenticationProperties.FormLogin getFormLogin() {
        return authenticationProperties.getFormLogin();
    }
}
