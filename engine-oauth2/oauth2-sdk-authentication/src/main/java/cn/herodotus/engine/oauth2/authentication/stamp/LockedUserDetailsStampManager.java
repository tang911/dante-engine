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

package cn.herodotus.engine.oauth2.authentication.stamp;

import cn.herodotus.engine.cache.jetcache.stamp.AbstractStampManager;
import cn.herodotus.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.herodotus.engine.oauth2.core.constants.OAuth2Constants;
import org.dromara.hutool.core.data.id.IdUtil;

/**
 * <p>Description: 锁定账户签章管理 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/8 21:27
 */
public class LockedUserDetailsStampManager extends AbstractStampManager<String, String> {

    private final OAuth2AuthenticationProperties authenticationProperties;

    public LockedUserDetailsStampManager(OAuth2AuthenticationProperties authenticationProperties) {
        super(OAuth2Constants.CACHE_NAME_TOKEN_LOCKED_USER_DETAIL);
        this.authenticationProperties = authenticationProperties;
    }

    @Override
    public String nextStamp(String key) {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(authenticationProperties.getSignInFailureLimited().getExpire());
    }
}
