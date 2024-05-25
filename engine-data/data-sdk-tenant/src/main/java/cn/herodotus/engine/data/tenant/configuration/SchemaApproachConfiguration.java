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

package cn.herodotus.engine.data.tenant.configuration;

import cn.herodotus.engine.data.tenant.annotation.ConditionalOnSchemaApproach;
import cn.herodotus.engine.data.tenant.hibernate.SchemaMultiTenantConnectionProvider;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * <p>Description: 共享数据库，独立Schema多租户方式配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/3/28 22:26
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnSchemaApproach
public class SchemaApproachConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SchemaApproachConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Schema Approach] Auto Configure.");
    }

    @Bean
    public HibernatePropertiesCustomizer schemaMultiTenantConnectionProvider(DataSource dataSource) {
        SchemaMultiTenantConnectionProvider provider = new SchemaMultiTenantConnectionProvider(dataSource);
        log.debug("[Herodotus] |- Bean [Multi Tenant Connection Provider] Auto Configure.");
        return provider;
    }
}
