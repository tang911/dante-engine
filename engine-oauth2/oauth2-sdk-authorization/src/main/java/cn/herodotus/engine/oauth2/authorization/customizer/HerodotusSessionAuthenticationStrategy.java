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

package cn.herodotus.engine.oauth2.authorization.customizer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.session.FindByIndexNameSessionRepository;

/**
 * <p>Description: 自定义扩展 SessionAuthenticationStrategy </p>
 * <p>
 * 扩展 SessionAuthenticationStrategy 以支持 {@link FindByIndexNameSessionRepository#PRINCIPAL_NAME_INDEX_NAME} 的设置。
 *
 * @author : gengwei.zheng
 * @date : 2023/9/5 14:01
 */
public class HerodotusSessionAuthenticationStrategy extends RegisterSessionAuthenticationStrategy {

    public HerodotusSessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
        super(sessionRegistry);
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (ObjectUtils.isNotEmpty(authentication) && authentication.isAuthenticated()) {
            if (authentication instanceof BearerTokenAuthentication) {
                request.getSession().setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, authentication.getName());
            }
        }
        super.onAuthentication(authentication, request, response);
    }
}
