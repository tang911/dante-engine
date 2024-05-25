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

package cn.herodotus.engine.oauth2.management.dto;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: OAuth2 Scope Dto </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/4/1 13:55
 */
@Schema(name = "OAuth2 范围请求 Dto")
public class OAuth2ScopeDto {

    @Schema(name = "范围ID")
    @NotNull(message = "范围ID不能为空")
    private String scopeId;

    @Schema(name = "范围权限列表")
    private Set<OAuth2PermissionDto> permissions = new HashSet<>();

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public Set<OAuth2PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<OAuth2PermissionDto> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("scopeId", scopeId)
                .toString();
    }
}
