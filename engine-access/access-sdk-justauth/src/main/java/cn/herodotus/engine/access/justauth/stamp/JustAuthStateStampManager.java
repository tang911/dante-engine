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

package cn.herodotus.engine.access.justauth.stamp;

import cn.herodotus.engine.access.core.constants.AccessConstants;
import cn.herodotus.engine.access.justauth.properties.JustAuthProperties;
import cn.herodotus.engine.cache.jetcache.stamp.AbstractStampManager;
import me.zhyd.oauth.cache.AuthStateCache;
import org.dromara.hutool.core.data.id.IdUtil;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 自定义JustAuth State Cache </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/5/22 10:22
 */
public class JustAuthStateStampManager extends AbstractStampManager<String, String> implements AuthStateCache {

    public JustAuthStateStampManager() {
        super(AccessConstants.CACHE_NAME_TOKEN_JUSTAUTH);
    }

    private JustAuthProperties justAuthProperties;

    public void setJustAuthProperties(JustAuthProperties justAuthProperties) {
        this.justAuthProperties = justAuthProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(justAuthProperties.getTimeout());
    }

    @Override
    public String nextStamp(String key) {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public void cache(String key, String value) {
        this.put(key, value);
    }

    @Override
    public void cache(String key, String value, long expire) {
        this.put(key, value, expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean containsKey(String key) {
        return this.containKey(key);
    }
}
