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
 * along with this program.  If not, see <https://www.herodotus.vip>.
 */

package cn.herodotus.engine.oauth2.authorization.autoconfigure.message;

import cn.herodotus.engine.message.core.definition.strategy.AccountStatusChangedEventManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 认证服务器 OAuth2 消息配置 </p>
 * <p>
 * 本配置类中，仅配置认证服务器 UAA 所需要的相关信息内容
 *
 * @author : gengwei.zheng
 * @date : 2024/8/21 17:54
 */
@Configuration(proxyBeanMethods = false)
public class OAuth2AuthenticationMessageConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AuthenticationMessageConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- Module [Authentication Server Message] Configure.");
    }

    @Bean
    public AccountStatusChangedEventManager accountStatusChangedEventManager() {
        DefaultAccountStatusChangedEventManager manager = new DefaultAccountStatusChangedEventManager();
        log.trace("[Herodotus] |- Bean [Herodotus Account Status Event Manager] Configure.");
        return manager;
    }
}
