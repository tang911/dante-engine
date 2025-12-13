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

package org.dromara.dante.oauth2.authorization.customizer;

import org.dromara.dante.core.builder.SecurityMatcher;
import org.dromara.dante.core.function.SecurityMatcherBuilderCustomizer;
import org.dromara.dante.oauth2.authorization.properties.OAuth2AuthorizationProperties;

/**
 * <p>Description: OAuth2AuthorizationServer 模块 SecurityMatcher 配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2025/12/13 22:57
 */
public class OAuth2AuthorizationSecurityMatcherBuilderCustomizer implements SecurityMatcherBuilderCustomizer {

    private final OAuth2AuthorizationProperties authorizationProperties;

    public OAuth2AuthorizationSecurityMatcherBuilderCustomizer(OAuth2AuthorizationProperties authorizationProperties) {
        this.authorizationProperties = authorizationProperties;
    }

    @Override
    public void customize(SecurityMatcher.Builder builder) {
        builder.staticResources(authorizationProperties.getMatcher().getStaticResources())
                .permitAll(authorizationProperties.getMatcher().getPermitAll())
                .hasAuthenticated(authorizationProperties.getMatcher().getHasAuthenticated());
    }
}
