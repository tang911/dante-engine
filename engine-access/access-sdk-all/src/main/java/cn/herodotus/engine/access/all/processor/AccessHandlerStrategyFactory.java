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

package cn.herodotus.engine.access.all.processor;

import cn.herodotus.engine.access.core.definition.AccessHandler;
import cn.herodotus.engine.access.core.definition.AccessResponse;
import cn.herodotus.engine.access.core.definition.AccessUserDetails;
import cn.herodotus.engine.access.core.exception.AccessHandlerNotFoundException;
import cn.herodotus.engine.access.core.exception.IllegalAccessArgumentException;
import cn.herodotus.engine.assistant.definition.domain.oauth2.AccessPrincipal;
import cn.herodotus.engine.assistant.core.enums.AccountType;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: Access Handler 工厂 </p>
 * <p>
 * 通过该工厂模式，对接入的常规操作进行封装。避免导入引用各个组件，导致耦合性增大
 * <p>
 * 本处使用基于Spring Boot 的工厂模式
 * {@see :https://www.pianshen.com/article/466978086/}
 *
 * @author : gengwei.zheng
 * @date : 2021/4/4 17:40
 */
@Component
public class AccessHandlerStrategyFactory {

    private static final Logger log = LoggerFactory.getLogger(AccessHandlerStrategyFactory.class);

    @Autowired
    private final Map<String, AccessHandler> handlers = new ConcurrentHashMap<>();

    public AccessResponse preProcess(String source, String core, String... params) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(source);
        return socialAuthenticationHandler.preProcess(core, params);
    }

    public AccessResponse preProcess(AccountType accountType, String core, String... params) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(accountType);
        return socialAuthenticationHandler.preProcess(core, params);
    }

    public AccessUserDetails findAccessUserDetails(String source, AccessPrincipal accessPrincipal) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(source);
        AccessUserDetails accessUserDetails = socialAuthenticationHandler.loadUserDetails(source, accessPrincipal);

        log.debug("[Herodotus] |- AccessHandlerFactory findAccessUserDetails.");
        return accessUserDetails;
    }

    public AccessHandler getAccessHandler(String source) {
        if (ObjectUtils.isEmpty(source)) {
            throw new IllegalAccessArgumentException("Cannot found SocialProvider");
        }

        AccountType accountType = AccountType.getAccountType(source);
        if (ObjectUtils.isEmpty(accountType)) {
            throw new IllegalAccessArgumentException("Cannot parse the source parameter.");
        }

        return getAccessHandler(accountType);
    }

    public AccessHandler getAccessHandler(AccountType accountType) {
        String handlerName = accountType.getHandler();
        AccessHandler socialAuthenticationHandler = handlers.get(handlerName);
        if (ObjectUtils.isNotEmpty(socialAuthenticationHandler)) {
            return socialAuthenticationHandler;
        } else {
            throw new AccessHandlerNotFoundException("Can not found Social Handler for " + handlerName);
        }
    }
}
