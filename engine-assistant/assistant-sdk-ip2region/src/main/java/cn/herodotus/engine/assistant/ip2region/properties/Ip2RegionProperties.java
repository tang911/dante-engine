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

package cn.herodotus.engine.assistant.ip2region.properties;

import cn.herodotus.engine.assistant.definition.constants.BaseConstants;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Ip2Region 配置参数 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/24 11:39
 */
@ConfigurationProperties(prefix = BaseConstants.PROPERTY_PREFIX_IP2REGION)
public class Ip2RegionProperties {

    /**
     * Ip V4 地址数据库位置，默认值：classpath*:/db/ip2region.db
     */
    private String ipV4Resource = "classpath:/db/ip2region.xdb";
    /**
     * Ip V6 地址数据库位置，默认值：classpath:db/ipv6wry.db
     */
    private String ipV6Resource = "classpath:/db/ipv6wry.db";

    public String getIpV4Resource() {
        return ipV4Resource;
    }

    public void setIpV4Resource(String ipV4Resource) {
        this.ipV4Resource = ipV4Resource;
    }

    public String getIpV6Resource() {
        return ipV6Resource;
    }

    public void setIpV6Resource(String ipV6Resource) {
        this.ipV6Resource = ipV6Resource;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ipV4Resource", ipV4Resource)
                .add("ipV6Resource", ipV6Resource)
                .toString();
    }
}
