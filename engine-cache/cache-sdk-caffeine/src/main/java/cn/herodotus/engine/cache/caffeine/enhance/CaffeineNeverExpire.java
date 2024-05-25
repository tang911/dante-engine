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

import com.github.benmanes.caffeine.cache.Expiry;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * <p>Description: Caffeine 缓存永不过期时间配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/7/30 15:07
 */
public class CaffeineNeverExpire<K, V> implements Expiry<K, V> {

    @Override
    public long expireAfterCreate(@NonNull K key, @NonNull V value, long currentTime) {
        return Long.MAX_VALUE;
    }

    @Override
    public long expireAfterUpdate(@NonNull K key, @NonNull V value, long currentTime, @NonNegative long currentDuration) {
        return currentDuration;
    }

    @Override
    public long expireAfterRead(@NonNull K key, @NonNull V value, long currentTime, @NonNegative long currentDuration) {
        return currentDuration;
    }
}
