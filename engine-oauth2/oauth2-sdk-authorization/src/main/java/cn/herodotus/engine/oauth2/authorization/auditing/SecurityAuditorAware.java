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

package cn.herodotus.engine.oauth2.authorization.auditing;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

import java.util.Optional;

/**
 * <p>Description: 基于 Security 的数据库审计用户信息获取 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/4/7 15:56
 */
public class SecurityAuditorAware implements AuditorAware<String> {

    private static final Logger log = LoggerFactory.getLogger(SecurityAuditorAware.class);

    @Override
    public Optional<String> getCurrentAuditor() {

        SecurityContext context = SecurityContextHolder.getContext();

        if (ObjectUtils.isNotEmpty(context)) {
            Authentication authentication = context.getAuthentication();
            if (ObjectUtils.isNotEmpty(authentication)) {
                if (authentication.isAuthenticated()) {
                    if (authentication instanceof BearerTokenAuthentication bearerTokenAuthentication) {
                        Object object = bearerTokenAuthentication.getPrincipal();
                        if (object instanceof OAuth2IntrospectionAuthenticatedPrincipal principal) {
                            String username = principal.getName();
                            log.trace("[Herodotus] |- Current auditor is : [{}]", username);
                            return Optional.of(username);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }
}
