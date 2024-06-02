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

package cn.herodotus.engine.data.core.properties;

import cn.herodotus.engine.data.core.constants.DataConstants;
import cn.herodotus.engine.data.core.enums.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Data 相关模块配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/4 12:02
 */
@ConfigurationProperties(DataConstants.PROPERTY_PREFIX_DATA)
public class DataProperties {

    /**
     * 基础数据源切换。用于某些基础核心应用底层存储切换的配置。默认，JPA
     */
    private DataSource dataSource = DataSource.JPA;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
