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

package cn.herodotus.engine.oauth2.core.definition.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>Description: 自定义 GrantedAuthority </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/3/5 0:12
 */
public class HerodotusGrantedAuthority implements GrantedAuthority {

    private String authority;

    public HerodotusGrantedAuthority() {
    }

    public HerodotusGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HerodotusGrantedAuthority that = (HerodotusGrantedAuthority) o;
        return Objects.equal(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(authority);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("authority", authority)
                .toString();
    }
}