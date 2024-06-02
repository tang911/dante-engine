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

package cn.herodotus.engine.oauth2.authentication.customizer;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: Opaque Token Customizer </p>
 * <p>
 * An {@link OAuth2TokenCustomizer} provides the ability to customize the attributes of an OAuth2Token, which are accessible in the provided {@link org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext}.
 * It is used by an {@link org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator} to let it customize the attributes of the OAuth2Token before it is generated.
 *
 * @author : gengwei.zheng
 * @date : 2022/10/9 20:43
 */
public class HerodotusOpaqueTokenCustomizer extends AbstractTokenCustomizer implements OAuth2TokenCustomizer<OAuth2TokenClaimsContext> {

    @Override
    public void customize(OAuth2TokenClaimsContext context) {

        AbstractAuthenticationToken token = null;
        Authentication clientAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (clientAuthentication instanceof OAuth2ClientAuthenticationToken) {
            token = (OAuth2ClientAuthenticationToken) clientAuthentication;
        }

        if (ObjectUtils.isNotEmpty(token)) {
            if (token.isAuthenticated()) {

                if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                    Authentication authentication = context.getPrincipal();
                    if (ObjectUtils.isNotEmpty(authentication)) {
                        Map<String, Object> attributes = new HashMap<>();
                        appendAll(attributes, authentication, context.getAuthorizedScopes());
                        OAuth2TokenClaimsSet.Builder tokenClaimSetBuilder = context.getClaims();
                        tokenClaimSetBuilder.claims(claims -> claims.putAll(attributes));
                    }
                }
            }

        }
    }
}
