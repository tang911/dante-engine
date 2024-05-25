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
import cn.herodotus.engine.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationDetailsSource;

/**
 * <p>Description: 表单登录 Details 定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/4/12 10:41
 */
public class OAuth2FormLoginWebAuthenticationDetailSource implements AuthenticationDetailsSource<HttpServletRequest, FormLoginWebAuthenticationDetails> {

    private final OAuth2AuthenticationProperties authenticationProperties;

    public OAuth2FormLoginWebAuthenticationDetailSource(OAuth2AuthenticationProperties authenticationProperties) {
        this.authenticationProperties = authenticationProperties;
    }

    @Override
    public FormLoginWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new FormLoginWebAuthenticationDetails(context, authenticationProperties.getFormLogin().getCloseCaptcha(), authenticationProperties.getFormLogin().getCaptchaParameter(), authenticationProperties.getFormLogin().getCategory());
    }
}
