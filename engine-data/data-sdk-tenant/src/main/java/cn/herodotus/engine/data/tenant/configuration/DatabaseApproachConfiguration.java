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

package cn.herodotus.engine.data.tenant.configuration;

import cn.herodotus.engine.data.tenant.annotation.ConditionalOnDatabaseApproach;
import cn.herodotus.engine.data.tenant.datasource.MultiTenantDataSourceFactory;
import cn.herodotus.engine.data.tenant.hibernate.DatabaseMultiTenantConnectionProvider;
import cn.herodotus.engine.data.tenant.hibernate.HerodotusHibernatePropertiesProvider;
import cn.herodotus.engine.data.tenant.properties.MultiTenantProperties;
import jakarta.annotation.PostConstruct;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.SchemaManagementProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Description: 独立数据库多租户方式配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/3/28 23:37
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnDatabaseApproach
@EnableTransactionManagement
@EntityScan(basePackages = {
        "cn.herodotus.engine.data.tenant.entity",
})
@EnableJpaRepositories(basePackages = {
        "cn.herodotus.engine.data.tenant.repository",
})
public class DatabaseApproachConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DatabaseApproachConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Database Approach] Auto Configure.");
    }

    @Bean
    public HibernatePropertiesCustomizer databaseMultiTenantConnectionProvider(DataSource dataSource) {
        DatabaseMultiTenantConnectionProvider provider = new DatabaseMultiTenantConnectionProvider(dataSource);
        log.debug("[Herodotus] |- Bean [Multi Tenant Connection Provider] Auto Configure.");
        return provider;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, HibernateProperties hibernateProperties,
                                                                       JpaProperties jpaProperties, JpaVendorAdapter jpaVendorAdapter,
                                                                       ConfigurableListableBeanFactory beanFactory,
                                                                       ObjectProvider<SchemaManagementProvider> providers,
                                                                       ObjectProvider<PhysicalNamingStrategy> physicalNamingStrategy,
                                                                       ObjectProvider<ImplicitNamingStrategy> implicitNamingStrategy,
                                                                       ObjectProvider<HibernatePropertiesCustomizer> hibernatePropertiesCustomizers,
                                                                       MultiTenantProperties multiTenantProperties
    ) {

        HerodotusHibernatePropertiesProvider provider = new HerodotusHibernatePropertiesProvider(dataSource, hibernateProperties, jpaProperties, beanFactory, providers, physicalNamingStrategy, implicitNamingStrategy, hibernatePropertiesCustomizers);
        Map<String, Object> properties = provider.getVendorProperties();

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        //此处不能省略，哪怕你使用了 @EntityScan，实际上 @EntityScan 会失效
        entityManagerFactory.setPackagesToScan(multiTenantProperties.getPackageToScan());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setJpaPropertyMap(properties);
        return entityManagerFactory;
    }

    @Primary
    @Bean
    @ConditionalOnClass({LocalContainerEntityManagerFactoryBean.class})
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }

    @Bean
    @ConditionalOnClass({LocalContainerEntityManagerFactoryBean.class})
    public MultiTenantDataSourceFactory multiTenantDataSourceFactory() {
        MultiTenantDataSourceFactory multiTenantDataSourceFactory = new MultiTenantDataSourceFactory();
        log.debug("[Herodotus] |- Bean [Multi Tenant DataSource Factory] Auto Configure.");
        return multiTenantDataSourceFactory;
    }
}
