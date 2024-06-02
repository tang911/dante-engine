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

package cn.herodotus.engine.oauth2.core.utils;

import cn.herodotus.engine.assistant.definition.constants.BaseConstants;
import cn.herodotus.engine.assistant.definition.domain.oauth2.PrincipalDetails;
import cn.herodotus.engine.oauth2.core.definition.domain.HerodotusUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;

import java.util.HashSet;
import java.util.List;

/**
 * <p>Description: 身份信息工具类 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/12/31 12:07
 */
public class PrincipalUtils {

    public static PrincipalDetails toPrincipalDetails(HerodotusUser herodotusUser) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(herodotusUser.getUserId());
        details.setUsername(herodotusUser.getUsername());
        details.setRoles(herodotusUser.getRoles());
        details.setAvatar(herodotusUser.getAvatar());
        details.setEmployeeId(herodotusUser.getEmployeeId());
        return details;
    }

    public static PrincipalDetails toPrincipalDetails(OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(authenticatedPrincipal.getAttribute(BaseConstants.OPEN_ID));
        details.setUsername(authenticatedPrincipal.getName());
        List<String> roles = authenticatedPrincipal.getAttribute(BaseConstants.ROLES);
        if (CollectionUtils.isNotEmpty(roles)) {
            details.setRoles(new HashSet<>(roles));
        }
        details.setAvatar(authenticatedPrincipal.getAttribute(BaseConstants.AVATAR));
        details.setEmployeeId(authenticatedPrincipal.getAttribute(BaseConstants.EMPLOYEE_ID));
        return details;
    }

    public static PrincipalDetails toPrincipalDetails(Jwt jwt) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(jwt.getClaimAsString(BaseConstants.OPEN_ID));
        details.setUsername(jwt.getClaimAsString(JwtClaimNames.SUB));
        details.setRoles(jwt.getClaim(BaseConstants.ROLES));
        details.setAvatar(jwt.getClaimAsString(BaseConstants.AVATAR));
        details.setEmployeeId(jwt.getClaimAsString(BaseConstants.EMPLOYEE_ID));
        return details;
    }
}
