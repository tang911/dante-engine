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

package cn.herodotus.engine.cache.redis.definition;

import org.springframework.beans.factory.InitializingBean;

/**
 * <p>Description: 简单数据的Redis存储 </p>
 * <p>
 * 很多使用Redis进行缓存的数据都比较简单，通常就是单一的键值对存储。例如，手机验证码、JustAuth的State数据、第三方集成的Token等。
 * 因此，抽象出SimpleRedisCache，对此类操作进行定义。
 *
 * @author : gengwei.zheng
 * @date : 2021/5/21 23:37
 */
public interface StringRedisCache extends InitializingBean {

    /**
     * 存入缓存
     *
     * @param key   缓存key
     * @param value 缓存内容
     */
    void cache(String key, String value);

    /**
     * 存入缓存
     *
     * @param key     缓存key
     * @param value   缓存内容
     * @param timeout 指定缓存过期时间（毫秒）
     */
    void cache(String key, String value, long timeout);

    /**
     * 获取缓存内容
     *
     * @param key 缓存key
     * @return 缓存内容
     */
    String get(String key);

    /**
     * 是否存在key，如果对应key的value值已过期，也返回false
     *
     * @param key 缓存key
     * @return true：存在key，并且value没过期；false：key不存在或者已过期
     */
    boolean containsKey(String key);

    boolean delete(String key);
}
