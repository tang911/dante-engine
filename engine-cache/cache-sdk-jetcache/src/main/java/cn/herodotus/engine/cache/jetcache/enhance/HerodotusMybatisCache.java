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

import org.apache.ibatis.cache.Cache;
import org.dromara.hutool.extra.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Description: 扩展的Mybatis二级缓存 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/10/26 17:02
 */
public class HerodotusMybatisCache implements Cache {

    private static final Logger log = LoggerFactory.getLogger(HerodotusMybatisCache.class);

    private final String id;
    private final com.alicp.jetcache.Cache<Object, Object> cache;
    private final AtomicInteger counter = new AtomicInteger(0);

    public HerodotusMybatisCache(String id) {
        this.id = id;
        JetCacheCreateCacheFactory jetCacheCreateCacheFactory = SpringUtil.getBean("jetCacheCreateCacheFactory");
        this.cache = jetCacheCreateCacheFactory.create(this.id);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        cache.put(key, value);
        counter.incrementAndGet();
        log.debug("[Herodotus] |- CACHE - Put data into Mybatis Cache, with key: [{}]", key);
    }

    @Override
    public Object getObject(Object key) {
        Object obj = cache.get(key);
        log.debug("[Herodotus] |- CACHE - Get data from Mybatis Cache, with key: [{}]", key);
        return obj;
    }

    @Override
    public Object removeObject(Object key) {
        Object obj = cache.remove(key);
        counter.decrementAndGet();
        log.debug("[Herodotus] |- CACHE - Remove data from Mybatis Cache, with key: [{}]", key);
        return obj;
    }

    @Override
    public void clear() {
        cache.close();
        log.debug("[Herodotus] |- CACHE - Clear Mybatis Cache.");
    }

    @Override
    public int getSize() {
        return counter.get();
    }
}
