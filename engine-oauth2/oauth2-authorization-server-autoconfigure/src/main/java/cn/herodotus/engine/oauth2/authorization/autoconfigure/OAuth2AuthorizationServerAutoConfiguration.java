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

package cn.herodotus.engine.oauth2.authorization.autoconfigure;

import cn.herodotus.engine.message.core.logic.strategy.AccountStatusEventManager;
import cn.herodotus.engine.oauth2.authorization.autoconfigure.status.DefaultAccountStatusEventManager;
import cn.herodotus.engine.oauth2.management.configuration.OAuth2ManagementConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: OAuth2 授权服务器自动配置模块 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/29 10:19
 */
@AutoConfiguration
@Import({
        OAuth2ManagementConfiguration.class
})
public class OAuth2AuthorizationServerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AuthorizationServerAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [OAuth2 Authorization Server Starter] Auto Configure.");
    }

    @Bean
    public AccountStatusEventManager accountStatusEventManager() {
        DefaultAccountStatusEventManager manager = new DefaultAccountStatusEventManager();
        log.trace("[Herodotus] |- Bean [Herodotus Account Status Event Manager] Auto Configure.");
        return manager;
    }
}
