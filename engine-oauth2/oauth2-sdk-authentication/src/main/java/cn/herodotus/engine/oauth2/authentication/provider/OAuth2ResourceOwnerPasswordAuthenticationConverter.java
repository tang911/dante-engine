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

package cn.herodotus.engine.oauth2.authentication.provider;

import cn.herodotus.engine.oauth2.authentication.utils.OAuth2EndpointUtils;
import cn.herodotus.engine.oauth2.core.definition.HerodotusGrantType;
import cn.herodotus.engine.rest.protect.crypto.processor.HttpCryptoProcessor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.MultiValueMap;

/**
 * <p>Description: 自定义密码模式认证转换器 </p>
 * <p>
 * {@code AuthenticationConverter} 类似于以前的 {@code AbstractTokenGranter}
 * 主要用途是从请求中获取参数，并拼装Token类
 *
 * @author : gengwei.zheng
 * @date : 2022/2/22 17:03
 */
public final class OAuth2ResourceOwnerPasswordAuthenticationConverter extends AbstractAuthenticationConverter {

    public OAuth2ResourceOwnerPasswordAuthenticationConverter(HttpCryptoProcessor httpCryptoProcessor) {
        super(httpCryptoProcessor);
    }

    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {
        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!HerodotusGrantType.PASSWORD.getValue().equals(grantType)) {
            return null;
        }

        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);

        // scope (OPTIONAL)
        String scope = OAuth2EndpointUtils.checkOptionalParameter(parameters, OAuth2ParameterNames.SCOPE);

        // username (REQUIRED)
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.USERNAME);

        // password (REQUIRED)
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.PASSWORD);

        return new OAuth2ResourceOwnerPasswordAuthenticationToken(getClientPrincipal(), getRequestedScopes(scope), getAdditionalParameters(request, parameters));
    }
}
