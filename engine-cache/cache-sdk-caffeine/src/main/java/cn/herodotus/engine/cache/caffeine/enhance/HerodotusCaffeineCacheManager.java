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

package cn.herodotus.engine.cache.caffeine.enhance;

import cn.herodotus.engine.assistant.definition.constants.SymbolConstants;
import cn.herodotus.engine.cache.core.properties.CacheProperties;
import cn.herodotus.engine.cache.core.properties.CacheSetting;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.Map;

/**
 * <p>Description: 扩展的 CaffeineCacheManager </p>
 * <p>
 * 用于支持 Caffeine 缓存可以针对实体进行单独的过期时间设定
 *
 * @author : gengwei.zheng
 * @date : 2021/10/25 18:12
 */
public class HerodotusCaffeineCacheManager extends CaffeineCacheManager {

    private static final Logger log = LoggerFactory.getLogger(HerodotusCaffeineCacheManager.class);

    private final CacheProperties cacheProperties;

    public HerodotusCaffeineCacheManager(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    public HerodotusCaffeineCacheManager(CacheProperties cacheProperties, String... cacheNames) {
        super(cacheNames);
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    @Override
    protected Cache<Object, Object> createNativeCaffeineCache(String name) {
        Map<String, CacheSetting> instances = cacheProperties.getInstances();
        if (MapUtils.isNotEmpty(instances)) {
            String key = StringUtils.replace(name, SymbolConstants.COLON, cacheProperties.getSeparator());
            if (instances.containsKey(key)) {
                CacheSetting cacheSetting = instances.get(key);
                log.debug("[Herodotus] |- CACHE - Caffeine cache [{}] is set to use INSTANCE config.", name);
                return Caffeine.newBuilder().expireAfterWrite(cacheSetting.getExpire()).build();
            }
        }

        return super.createNativeCaffeineCache(name);
    }
}
