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

package org.dromara.dante.rest.servlet.identity.customizer;

import org.dromara.dante.core.builder.SecurityMatcher;
import org.dromara.dante.core.constant.SymbolConstants;
import org.dromara.dante.core.constant.SystemConstants;
import org.dromara.dante.core.function.SecurityMatcherBuilderCustomizer;
import org.dromara.dante.oauth2.commons.properties.OAuth2AuthenticationProperties;

import java.util.List;

/**
 * <p>Description: Identity 模块 SecurityMatcher 配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2025/12/13 21:44
 */
public class IdentitySecurityMatcherBuilderCustomizer implements SecurityMatcherBuilderCustomizer {

    private final OAuth2AuthenticationProperties authenticationProperties;

    public IdentitySecurityMatcherBuilderCustomizer(OAuth2AuthenticationProperties authenticationProperties) {
        this.authenticationProperties = authenticationProperties;
    }

    @Override
    public void customize(SecurityMatcher.Builder builder) {
        // Identity 模块中包含 Webjars 和 Static 资源
        builder.staticResources(List.of(
                        SystemConstants.MATCHER_WEBJARS))
                .permitAll(List.of(
                        "/open/**",
                        "/oauth2/sign-out",
                        authenticationProperties.getFormLogin().getLoginPageUrl() + SymbolConstants.STAR))
                .hasAuthenticated(List.of(
                        authenticationProperties.getAuthorizationConsentUri()));
    }
}
