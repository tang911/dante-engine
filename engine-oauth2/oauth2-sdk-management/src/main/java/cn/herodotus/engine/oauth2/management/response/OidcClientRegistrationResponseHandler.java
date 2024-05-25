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

package cn.herodotus.engine.oauth2.management.response;

import cn.herodotus.engine.oauth2.management.service.OAuth2DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.oidc.OidcClientRegistration;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcClientRegistrationAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.oidc.http.converter.OidcClientRegistrationHttpMessageConverter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * <p>Description: 客户端自动注册成功后续逻辑处理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/23 17:37
 */
public class OidcClientRegistrationResponseHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(OidcClientRegistrationResponseHandler.class);

    private final OAuth2DeviceService deviceService;

    private final HttpMessageConverter<OidcClientRegistration> clientRegistrationHttpMessageConverter =
            new OidcClientRegistrationHttpMessageConverter();

    public OidcClientRegistrationResponseHandler(OAuth2DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OidcClientRegistrationAuthenticationToken clientRegistrationAuthenticationToken =
                (OidcClientRegistrationAuthenticationToken) authentication;

        OidcClientRegistration clientRegistration = clientRegistrationAuthenticationToken.getClientRegistration();

        boolean success = deviceService.sync(clientRegistration);
        if (success) {
            log.info("[Herodotus] |- Sync oidcClientRegistration to device succeed.");
        } else {
            log.warn("[Herodotus] |- Sync oidcClientRegistration to device failed!");
        }

        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        if (HttpMethod.POST.name().equals(request.getMethod())) {
            httpResponse.setStatusCode(HttpStatus.CREATED);
        } else {
            httpResponse.setStatusCode(HttpStatus.OK);
        }
        this.clientRegistrationHttpMessageConverter.write(clientRegistration, null, httpResponse);
    }
}
