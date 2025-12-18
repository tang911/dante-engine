/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Stirrup.
 *
 * Herodotus Stirrup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Stirrup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package org.dromara.dante.oauth2.commons.properties;

import org.dromara.dante.core.constant.BaseConstants;
import org.dromara.dante.security.condition.TokenFormat;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: OAuth2 通用配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2025/12/14 0:36
 */
@ConfigurationProperties(prefix = BaseConstants.PROPERTY_PREFIX_OAUTH2)
public class OAuth2Properties {

    /**
     * Token 校验是采用远程方式还是本地方式。
     */
    private TokenFormat tokenFormat = TokenFormat.OPAQUE;

    public TokenFormat getTokenFormat() {
        return tokenFormat;
    }

    public void setTokenFormat(TokenFormat tokenFormat) {
        this.tokenFormat = tokenFormat;
    }

    public boolean isRemoteValidate() {
        return this.getTokenFormat() == TokenFormat.OPAQUE;
    }
}
