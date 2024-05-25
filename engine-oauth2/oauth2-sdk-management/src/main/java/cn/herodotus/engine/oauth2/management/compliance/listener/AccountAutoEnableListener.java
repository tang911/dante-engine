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

package cn.herodotus.engine.oauth2.management.compliance.listener;

import cn.herodotus.engine.assistant.definition.constants.SymbolConstants;
import cn.herodotus.engine.oauth2.core.constants.OAuth2Constants;
import cn.herodotus.engine.oauth2.management.compliance.OAuth2AccountStatusManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * <p>Description: 账户锁定状态监听 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/8 11:11
 */
public class AccountAutoEnableListener extends KeyExpirationEventMessageListener {

    private static final Logger log = LoggerFactory.getLogger(AccountAutoEnableListener.class);

    private final OAuth2AccountStatusManager accountStatusManager;

    public AccountAutoEnableListener(RedisMessageListenerContainer listenerContainer, OAuth2AccountStatusManager accountStatusManager) {
        super(listenerContainer);
        this.accountStatusManager = accountStatusManager;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        if (StringUtils.contains(key, OAuth2Constants.CACHE_NAME_TOKEN_LOCKED_USER_DETAIL)) {
            String userId = StringUtils.substringAfterLast(key, SymbolConstants.COLON);
            log.info("[Herodotus] |- Parse the user [{}] at expired redis cache key [{}]", userId, key);
            if (StringUtils.isNotBlank(userId)) {
                log.debug("[Herodotus] |- Automatically unlock user account [{}]", userId);
                accountStatusManager.enable(userId);
            }
        }
    }
}
