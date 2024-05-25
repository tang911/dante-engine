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

package cn.herodotus.engine.rest.protect.secure.stamp;

import cn.herodotus.engine.cache.jetcache.stamp.AbstractStampManager;
import cn.herodotus.engine.rest.condition.constants.RestConstants;
import cn.herodotus.engine.rest.condition.properties.SecureProperties;
import org.dromara.hutool.core.data.id.IdUtil;

/**
 * <p>Description: 幂等Stamp管理 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/22 16:05
 */
public class IdempotentStampManager extends AbstractStampManager<String, String> {

    private final SecureProperties secureProperties;

    public IdempotentStampManager(SecureProperties secureProperties) {
        super(RestConstants.CACHE_NAME_TOKEN_IDEMPOTENT);
        this.secureProperties = secureProperties;
    }

    public SecureProperties getSecureProperties() {
        return secureProperties;
    }

    @Override
    public String nextStamp(String key) {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(secureProperties.getIdempotent().getExpire());
    }
}
