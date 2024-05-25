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

package cn.herodotus.engine.cache.jetcache.stamp;

import cn.herodotus.engine.cache.core.exception.StampHasExpiredException;
import cn.herodotus.engine.cache.core.exception.StampMismatchException;
import cn.herodotus.engine.cache.core.exception.StampParameterIllegalException;
import cn.herodotus.engine.cache.jetcache.utils.JetCacheUtils;
import com.alicp.jetcache.AutoReleaseLock;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 抽象Stamp管理 </p>
 *
 * @param <K> 签章缓存对应Key值的类型。
 * @param <V> 签章缓存存储数据，对应的具体存储值的类型，
 * @author : gengwei.zheng
 * @date : 2021/8/23 11:51
 */
public abstract class AbstractStampManager<K, V> implements StampManager<K, V> {

    private static final Logger log = LoggerFactory.getLogger(AbstractStampManager.class);

    private static final Duration DEFAULT_EXPIRE = Duration.ofMinutes(30);

    private Duration expire;
    private final Cache<K, V> cache;

    public AbstractStampManager(String cacheName) {
        this(cacheName, CacheType.BOTH);
    }

    public AbstractStampManager(String cacheName, CacheType cacheType) {
        this(cacheName, cacheType, DEFAULT_EXPIRE);
    }

    public AbstractStampManager(String cacheName, CacheType cacheType, Duration expire) {
        this.expire = expire;
        this.cache = JetCacheUtils.create(cacheName, cacheType, this.expire);
    }

    /**
     * 指定数据存储缓存
     *
     * @return {@link Cache}
     */
    protected Cache<K, V> getCache() {
        return this.cache;
    }

    @Override
    public Duration getExpire() {
        return this.expire;
    }

    public void setExpire(Duration expire) {
        this.expire = expire;
    }

    @Override
    public boolean check(K key, V value) throws StampParameterIllegalException, StampHasExpiredException, StampMismatchException {
        if (ObjectUtils.isEmpty(value)) {
            throw new StampParameterIllegalException("Parameter Stamp value is null");
        }

        V storedStamp = this.get(key);
        if (ObjectUtils.isEmpty(storedStamp)) {
            throw new StampHasExpiredException("Stamp is invalid!");
        }

        if (ObjectUtils.notEqual(storedStamp, value)) {
            throw new StampMismatchException("Stamp is mismathch!");
        }

        return true;
    }

    @Override
    public V get(K key) {
        return this.getCache().get(key);
    }

    @Override
    public void delete(K key) {
        boolean result = this.getCache().remove(key);
        if (!result) {
            log.warn("[Herodotus] |- Delete stamp [{}] failed.", key);
        }
    }

    @Override
    public void put(K key, V value, long expireAfterWrite, TimeUnit timeUnit) {
        this.getCache().put(key, value, expireAfterWrite, timeUnit);
    }

    @Override
    public AutoReleaseLock lock(K key, long expire, TimeUnit timeUnit) {
        return this.getCache().tryLock(key, expire, timeUnit);
    }

    @Override
    public boolean lockAndRun(K key, long expire, TimeUnit timeUnit, Runnable action) {
        return this.getCache().tryLockAndRun(key, expire, timeUnit, action);
    }
}
