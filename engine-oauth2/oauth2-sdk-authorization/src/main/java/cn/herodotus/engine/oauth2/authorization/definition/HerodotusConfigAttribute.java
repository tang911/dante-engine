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

package cn.herodotus.engine.oauth2.authorization.definition;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 自定义SecurityConfig </p>
 * <p>
 * 自定义SecurityConfig，主要为了构建无参数构造函数，以解决序列化出错问题
 *
 * @author : gengwei.zheng
 * @date : 2021/9/11 15:57
 */
public class HerodotusConfigAttribute implements ConfigAttribute {

    private String attribute;

    public HerodotusConfigAttribute() {
    }

    public HerodotusConfigAttribute(String config) {
        Assert.hasText(config, "You must provide a configuration attribute");
        this.attribute = config;
    }

    public static HerodotusConfigAttribute create(String attribute) {
        Assert.notNull(attribute, "You must supply an array of attribute names");
        return new HerodotusConfigAttribute(attribute.trim());
    }

    public static List<ConfigAttribute> createListFromCommaDelimitedString(String access) {
        return createList(StringUtils.commaDelimitedListToStringArray(access));
    }

    public static List<ConfigAttribute> createList(String... attributeNames) {
        Assert.notNull(attributeNames, "You must supply an array of attribute names");
        List<ConfigAttribute> attributes = new ArrayList<>(attributeNames.length);
        for (String attribute : attributeNames) {
            attributes.add(new HerodotusConfigAttribute(attribute.trim()));
        }
        return attributes;
    }

    public String getAttribute() {
        return this.attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HerodotusConfigAttribute that = (HerodotusConfigAttribute) o;
        return Objects.equal(attribute, that.attribute);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(attribute);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("attrib", attribute)
                .toString();
    }
}
