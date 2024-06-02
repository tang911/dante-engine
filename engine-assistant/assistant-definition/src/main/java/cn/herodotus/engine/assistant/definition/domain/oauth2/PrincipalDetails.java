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

package cn.herodotus.engine.assistant.definition.domain.oauth2;

import cn.herodotus.engine.assistant.definition.constants.BaseConstants;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: 用户登录额外信息 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/13 14:31
 */
public class PrincipalDetails {

    private String openId;

    private String username;

    private Set<String> roles;

    private String employeeId;

    private String avatar;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(BaseConstants.OPEN_ID, this.openId);
        map.put(BaseConstants.USERNAME, this.username);
        map.put(BaseConstants.ROLES, this.roles);
        map.put(BaseConstants.EMPLOYEE_ID, this.employeeId);
        map.put(BaseConstants.AVATAR, this.avatar);
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrincipalDetails that = (PrincipalDetails) o;
        return Objects.equal(openId, that.openId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(openId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", openId)
                .add("username", username)
                .add("roles", roles)
                .add("employeeId", employeeId)
                .add("avatar", avatar)
                .toString();
    }
}
