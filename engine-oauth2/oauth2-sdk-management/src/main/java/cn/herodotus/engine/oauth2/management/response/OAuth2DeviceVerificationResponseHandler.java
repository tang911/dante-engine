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

import cn.herodotus.engine.assistant.definition.constants.DefaultConstants;
import cn.herodotus.engine.oauth2.management.service.OAuth2DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2DeviceVerificationAuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

/**
 * <p>Description: 设备验证成功后续逻辑处理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/3 9:35
 */
public class OAuth2DeviceVerificationResponseHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(OAuth2DeviceVerificationResponseHandler.class);

    private final OAuth2DeviceService deviceService;

    public OAuth2DeviceVerificationResponseHandler(OAuth2DeviceService deviceService) {
        super(DefaultConstants.DEVICE_VERIFICATION_SUCCESS_URI);
        this.deviceService = deviceService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2DeviceVerificationAuthenticationToken deviceVerificationAuthenticationToken =
                (OAuth2DeviceVerificationAuthenticationToken) authentication;

        log.info("[Herodotus] |- Device verification authentication token is : [{}]", deviceVerificationAuthenticationToken);

        String clientId = deviceVerificationAuthenticationToken.getClientId();

        if (StringUtils.isNotBlank(clientId)) {
            boolean success = deviceService.activate(clientId, true);
            log.info("[Herodotus] |- The activation status of the device is : [{}]", success);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
