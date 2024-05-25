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

package cn.herodotus.engine.data.tenant.datasource;

import cn.herodotus.engine.data.tenant.entity.SysTenantDataSource;
import cn.herodotus.engine.data.tenant.repository.SysTenantDataSourceRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * <p>Description: 多租户数据源工厂 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/3/29 11:43
 */
@Component
public class MultiTenantDataSourceFactory {

    @Autowired
    private SysTenantDataSourceRepository sysTenantDataSourceRepository;

    private DataSource createDataSource(DataSource defaultDataSource, SysTenantDataSource sysTenantDataSource) {
        if (defaultDataSource instanceof HikariDataSource defaultHikariDataSource) {
            Properties defaultDataSourceProperties = defaultHikariDataSource.getDataSourceProperties();
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(sysTenantDataSource.getDriverClassName());
            hikariConfig.setJdbcUrl(sysTenantDataSource.getUrl());
            hikariConfig.setUsername(sysTenantDataSource.getUsername());
            hikariConfig.setPassword(sysTenantDataSource.getPassword());

            if (ObjectUtils.isNotEmpty(defaultDataSource)) {
                defaultDataSourceProperties.forEach((key, value) -> hikariConfig.addDataSourceProperty(String.valueOf(key), value));
            }

            return new HikariDataSource(hikariConfig);
        } else {
            return DataSourceBuilder.create()
                    .type(HikariDataSource.class)
                    .url(sysTenantDataSource.getUrl())
                    .driverClassName(sysTenantDataSource.getDriverClassName())
                    .username(sysTenantDataSource.getUsername())
                    .password(sysTenantDataSource.getPassword())
                    .build();
        }
    }

    public Map<String, DataSource> getAll(DataSource defaultDataSource) {
        List<SysTenantDataSource> sysTenantDataSources = sysTenantDataSourceRepository.findAll();
        if (CollectionUtils.isNotEmpty(sysTenantDataSources)) {
            return sysTenantDataSources.stream().collect(Collectors.toMap(SysTenantDataSource::getTenantId, value -> createDataSource(defaultDataSource, value)));
        } else {
            return new HashMap<>();
        }
    }
}
