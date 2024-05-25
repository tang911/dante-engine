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

package cn.herodotus.engine.oauth2.management.configuration;

import cn.herodotus.engine.message.core.logic.strategy.AccountStatusEventManager;
import cn.herodotus.engine.oauth2.authentication.stamp.LockedUserDetailsStampManager;
import cn.herodotus.engine.oauth2.authentication.stamp.SignInFailureLimitedStampManager;
import cn.herodotus.engine.oauth2.management.compliance.OAuth2AccountStatusManager;
import cn.herodotus.engine.oauth2.management.compliance.annotation.ConditionalOnAutoUnlockUserAccount;
import cn.herodotus.engine.oauth2.management.compliance.listener.AccountAutoEnableListener;
import cn.herodotus.engine.oauth2.management.compliance.listener.AuthenticationFailureListener;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>Description: OAuth2 应用安全合规配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/11 10:20
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(AccountStatusEventManager.class)
public class OAuth2ComplianceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ComplianceConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [OAuth2 Compliance] Auto Configure.");
    }

    @Bean
    public OAuth2AccountStatusManager accountStatusManager(UserDetailsService userDetailsService, AccountStatusEventManager accountStatusChanger, LockedUserDetailsStampManager lockedUserDetailsStampManager) {
        OAuth2AccountStatusManager manager = new OAuth2AccountStatusManager(userDetailsService, accountStatusChanger, lockedUserDetailsStampManager);
        log.trace("[Herodotus] |- Bean [OAuth2 Account Status Manager] Auto Configure.");
        return manager;
    }

    @Bean
    @ConditionalOnAutoUnlockUserAccount
    public AccountAutoEnableListener accountLockStatusListener(RedisMessageListenerContainer redisMessageListenerContainer, OAuth2AccountStatusManager accountStatusManager) {
        AccountAutoEnableListener listener = new AccountAutoEnableListener(redisMessageListenerContainer, accountStatusManager);
        log.trace("[Herodotus] |- Bean [OAuth2 Account Lock Status Listener] Auto Configure.");
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationFailureListener authenticationFailureListener(SignInFailureLimitedStampManager stampManager, OAuth2AccountStatusManager accountLockService) {
        AuthenticationFailureListener listener = new AuthenticationFailureListener(stampManager, accountLockService);
        log.trace("[Herodotus] |- Bean [OAuth2 Authentication Failure Listener] Auto Configure.");
        return listener;
    }
}
