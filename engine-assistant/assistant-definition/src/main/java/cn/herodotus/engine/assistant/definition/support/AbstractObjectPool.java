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

package cn.herodotus.engine.assistant.definition.support;

import cn.herodotus.engine.assistant.definition.domain.Pool;
import cn.herodotus.engine.assistant.definition.exception.BorrowObjectFromPoolErrorException;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: 对象池抽象定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/11/6 13:36
 */
public abstract class AbstractObjectPool<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractObjectPool.class);

    private final GenericObjectPool<T> genericObjectPool;

    protected AbstractObjectPool(@Nonnull PooledObjectFactory<T> pooledObjectFactory, @Nonnull Pool pool) {
        GenericObjectPoolConfig<T> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(pool.getMaxTotal());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        config.setMaxWait(pool.getMaxWait());
        config.setMinEvictableIdleDuration(pool.getMinEvictableIdleDuration());
        config.setSoftMinEvictableIdleDuration(pool.getSoftMinEvictableIdleDuration());
        config.setLifo(pool.getLifo());
        config.setBlockWhenExhausted(pool.getBlockWhenExhausted());
        genericObjectPool = new GenericObjectPool<>(pooledObjectFactory, config);
    }

    public T get() {
        try {
            T object = genericObjectPool.borrowObject();
            log.debug("[Herodotus] |- Fetch object from object pool.");
            return object;
        } catch (Exception e) {
            log.error("[Herodotus] |- Can not fetch object from pool.", e);
            throw new BorrowObjectFromPoolErrorException("Can not fetch object from pool.");
        }
    }

    public void close(T client) {
        if (ObjectUtils.isNotEmpty(client)) {
            log.debug("[Herodotus] |- Close object in pool.");
            genericObjectPool.returnObject(client);
        }
    }
}
