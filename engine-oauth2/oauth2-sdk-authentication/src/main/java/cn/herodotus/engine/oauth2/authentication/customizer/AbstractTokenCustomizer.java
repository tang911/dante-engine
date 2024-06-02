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

import cn.herodotus.engine.assistant.definition.constants.BaseConstants;
import cn.herodotus.engine.oauth2.core.definition.domain.HerodotusUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: TokenCustomizer 通用处理 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/10/12 10:20
 */
public abstract class AbstractTokenCustomizer {

    protected void appendAll(Map<String, Object> attributes, Authentication authentication, Set<String> authorizedScopes) {

        appendAuthorities(attributes, authentication);
        appendCommons(attributes, authentication, authorizedScopes);

    }

    protected void appendAuthorities(Map<String, Object> attributes, Authentication authentication) {

        if (CollectionUtils.isNotEmpty(authentication.getAuthorities())) {
            Set<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            attributes.put(BaseConstants.AUTHORITIES, authorities);
        }
    }

    protected void appendCommons(Map<String, Object> attributes, Authentication authentication, Set<String> authorizedScopes) {

        if (CollectionUtils.isNotEmpty(authorizedScopes)) {
            attributes.put(OAuth2ParameterNames.SCOPE, authorizedScopes);
        }

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            putUserInfo(attributes, authentication.getPrincipal());
        }

        if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
            Object details = authentication.getDetails();
            putUserInfo(attributes, details);
        }

        attributes.put("license", "Apache-2.0 Licensed | Copyright © 2020-2023 码 匠 君");
    }

    private void putUserInfo(Map<String, Object> attributes, Object object) {
        if (ObjectUtils.isNotEmpty(object) && object instanceof HerodotusUser principal) {
            attributes.put(BaseConstants.OPEN_ID, principal.getUserId());
            attributes.put(BaseConstants.ROLES, principal.getRoles());
            attributes.put(BaseConstants.AVATAR, principal.getAvatar());
            attributes.put(BaseConstants.EMPLOYEE_ID, principal.getEmployeeId());
        }
    }
}
