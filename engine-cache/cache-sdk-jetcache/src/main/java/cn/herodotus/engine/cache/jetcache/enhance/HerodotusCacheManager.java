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

package cn.herodotus.engine.cache.jetcache.enhance;

import cn.herodotus.engine.assistant.definition.constants.SymbolConstants;
import cn.herodotus.engine.cache.core.properties.CacheProperties;
import cn.herodotus.engine.cache.core.properties.CacheSetting;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import java.util.Map;

/**
 * <p>Description: 自定义 缓存管理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/23 17:02
 */
public class HerodotusCacheManager extends JetCacheSpringCacheManager {

    private static final Logger log = LoggerFactory.getLogger(HerodotusCacheManager.class);

    private final CacheProperties cacheProperties;

    public HerodotusCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties) {
        super(jetCacheCreateCacheFactory);
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    public HerodotusCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties, String... cacheNames) {
        super(jetCacheCreateCacheFactory, cacheNames);
        this.cacheProperties = cacheProperties;
    }

    @Override
    protected Cache createJetCache(String name) {
        Map<String, CacheSetting> instances = cacheProperties.getInstances();
        if (MapUtils.isNotEmpty(instances)) {
            String key = StringUtils.replace(name, SymbolConstants.COLON, cacheProperties.getSeparator());
            if (instances.containsKey(key)) {
                CacheSetting cacheSetting = instances.get(key);
                log.debug("[Herodotus] |- CACHE - Cache [{}] is set to use INSTANCE cache.", name);
                return super.createJetCache(name, cacheSetting);
            }
        }
        return super.createJetCache(name);
    }
}
