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

package cn.herodotus.engine.oauth2.management.compliance;

import cn.herodotus.engine.data.core.enums.DataItemStatus;
import cn.herodotus.engine.message.core.logic.strategy.AccountStatusEventManager;
import cn.herodotus.engine.message.core.logic.domain.UserStatus;
import cn.herodotus.engine.oauth2.authentication.stamp.LockedUserDetailsStampManager;
import cn.herodotus.engine.oauth2.core.definition.domain.HerodotusUser;
import cn.herodotus.engine.oauth2.core.definition.service.EnhanceUserDetailsService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 账户锁定处理服务 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/8 19:25
 */
@Service
public class OAuth2AccountStatusManager {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AccountStatusManager.class);

    private final UserDetailsService userDetailsService;
    private final AccountStatusEventManager accountStatusEventManager;
    private final LockedUserDetailsStampManager lockedUserDetailsStampManager;

    public OAuth2AccountStatusManager(UserDetailsService userDetailsService, AccountStatusEventManager accountStatusEventManager, LockedUserDetailsStampManager lockedUserDetailsStampManager) {
        this.userDetailsService = userDetailsService;
        this.lockedUserDetailsStampManager = lockedUserDetailsStampManager;
        this.accountStatusEventManager = accountStatusEventManager;
    }

    private EnhanceUserDetailsService getUserDetailsService() {
        return (EnhanceUserDetailsService) userDetailsService;
    }

    private String getUserId(String username) {
        EnhanceUserDetailsService enhanceUserDetailsService = getUserDetailsService();
        HerodotusUser user = enhanceUserDetailsService.loadHerodotusUserByUsername(username);
        if (ObjectUtils.isNotEmpty(user)) {
            return user.getUserId();
        }

        log.warn("[Herodotus] |- Can not found the userid for [{}]", username);
        return null;
    }

    public void lock(String username) {
        String userId = getUserId(username);
        if (ObjectUtils.isNotEmpty(userId)) {
            accountStatusEventManager.postProcess(new UserStatus(userId, DataItemStatus.LOCKING.name()));
            lockedUserDetailsStampManager.put(userId, username);
            log.info("[Herodotus] |- User count [{}] has been locked, and record into cache!", username);
        }
    }

    public void enable(String userId) {
        if (ObjectUtils.isNotEmpty(userId)) {
            accountStatusEventManager.postProcess(new UserStatus(userId, DataItemStatus.ENABLE.name()));
        }
    }

    public void releaseFromCache(String username) {
        String userId = getUserId(username);
        if (ObjectUtils.isNotEmpty(userId)) {
            String value = lockedUserDetailsStampManager.get(userId);
            if (StringUtils.isNotEmpty(value)) {
                this.lockedUserDetailsStampManager.delete(userId);
                log.info("[Herodotus] |- User count [{}] locked info has been release!", username);
            }
        }
    }
}
