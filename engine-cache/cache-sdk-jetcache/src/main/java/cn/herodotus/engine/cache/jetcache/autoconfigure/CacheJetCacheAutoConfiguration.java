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

package cn.herodotus.engine.cache.jetcache.autoconfigure;

import cn.herodotus.engine.cache.caffeine.configuration.CacheCaffeineConfiguration;
import cn.herodotus.engine.cache.core.properties.CacheProperties;
import cn.herodotus.engine.cache.jetcache.enhance.HerodotusCacheManager;
import cn.herodotus.engine.cache.jetcache.enhance.JetCacheCreateCacheFactory;
import cn.herodotus.engine.cache.jetcache.utils.JetCacheUtils;
import cn.herodotus.engine.cache.redis.configuration.CacheRedisConfiguration;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.autoconfigure.JetCacheAutoConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * <p>Description: JetCacheConfiguration </p>
 * <p>
 * 新增JetCache配置，解决JetCache依赖循环问题
 * <p>
 * 注解 @AutoConfiguration 它是 @Configuration、@AutoConfigureBefore、@AutoConfigureAfter三个注解结合体，以一顶三。
 * 标注 @AutoConfiguration 注解的类也必须放进
 * META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * 文件里才算自动配置类，否则也只是普通配置类而已
 *
 * @author : gengwei.zheng
 * @date : 2021/12/4 10:44
 */
@AutoConfiguration(after = JetCacheAutoConfiguration.class)
@ConditionalOnClass({CacheManager.class})
@EnableConfigurationProperties(CacheProperties.class)
@Import({CacheCaffeineConfiguration.class, CacheRedisConfiguration.class})
public class CacheJetCacheAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CacheJetCacheAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Cache JetCache] Auto Configure.");
    }

    @Bean
    public JetCacheCreateCacheFactory jetCacheCreateCacheFactory(@Qualifier("jcCacheManager") CacheManager cacheManager, CacheProperties cacheProperties) {
        JetCacheCreateCacheFactory factory = new JetCacheCreateCacheFactory(cacheManager, cacheProperties);
        JetCacheUtils.setJetCacheCreateCacheFactory(factory);
        log.trace("[Herodotus] |- Bean [Jet Cache Create Cache Factory] Auto Configure.");
        return factory;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public HerodotusCacheManager herodotusCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties) {
        HerodotusCacheManager herodotusCacheManager = new HerodotusCacheManager(jetCacheCreateCacheFactory, cacheProperties);
        herodotusCacheManager.setAllowNullValues(cacheProperties.getAllowNullValues());
        log.trace("[Herodotus] |- Bean [Jet Cache Herodotus Cache Manager] Auto Configure.");
        return herodotusCacheManager;
    }
}
