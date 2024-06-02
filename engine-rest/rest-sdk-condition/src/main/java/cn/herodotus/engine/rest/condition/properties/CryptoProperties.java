/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Engine.
 *
 * Herodotus Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.rest.condition.properties;

import cn.herodotus.engine.rest.condition.constants.RestConstants;
import cn.herodotus.engine.rest.core.enums.CryptoStrategy;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 加密算法配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/5/1 21:13
 */
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_CRYPTO)
public class CryptoProperties {

    /**
     * 加密算法策略，默认：国密算法
     */
    private CryptoStrategy cryptoStrategy = CryptoStrategy.SM;

    public CryptoStrategy getCryptoStrategy() {
        return cryptoStrategy;
    }

    public void setCryptoStrategy(CryptoStrategy cryptoStrategy) {
        this.cryptoStrategy = cryptoStrategy;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("strategy", cryptoStrategy)
                .toString();
    }
}
