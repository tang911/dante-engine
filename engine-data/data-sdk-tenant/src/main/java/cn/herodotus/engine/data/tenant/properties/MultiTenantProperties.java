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

package cn.herodotus.engine.data.tenant.properties;

import cn.herodotus.engine.data.core.constants.DataConstants;
import cn.herodotus.engine.data.core.enums.MultiTenantApproach;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 自定义 JPA 配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/9/8 18:22
 */
@ConfigurationProperties(prefix = DataConstants.PROPERTY_PREFIX_MULTI_TENANT)
public class MultiTenantProperties {

    /**
     * 多租户数据源扫描包
     */
    private String[] packageToScan = new String[]{"cn.herodotus.engine", "cn.herodotus.professional"};

    /**
     * 多租户模式，默认：discriminator
     */
    private MultiTenantApproach approach = MultiTenantApproach.DISCRIMINATOR;

    public String[] getPackageToScan() {
        return packageToScan;
    }

    public void setPackageToScan(String[] packageToScan) {
        this.packageToScan = packageToScan;
    }

    public MultiTenantApproach getApproach() {
        return approach;
    }

    public void setApproach(MultiTenantApproach approach) {
        this.approach = approach;
    }
}
