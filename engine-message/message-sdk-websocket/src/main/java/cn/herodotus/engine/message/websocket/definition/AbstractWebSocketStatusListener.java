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

package cn.herodotus.engine.message.websocket.definition;

import cn.herodotus.engine.cache.redis.utils.RedisBitMapUtils;
import cn.herodotus.engine.message.core.constants.MessageConstants;
import cn.herodotus.engine.message.websocket.domain.WebSocketPrincipal;
import cn.herodotus.engine.message.websocket.utils.WebSocketUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>Description: 公共 WebSocketUserListener </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/12/29 22:20
 */
public abstract class AbstractWebSocketStatusListener<E extends ApplicationEvent> implements ApplicationListener<E> {

    private static final Logger log = LoggerFactory.getLogger(AbstractWebSocketStatusListener.class);

    private final WebSocketMessageSender webSocketMessageSender;

    public AbstractWebSocketStatusListener(WebSocketMessageSender webSocketMessageSender) {
        this.webSocketMessageSender = webSocketMessageSender;
    }

    private void changeStatus(WebSocketPrincipal principal, boolean isOnline) {
        if (ObjectUtils.isNotEmpty(principal)) {

            RedisBitMapUtils.setBit(MessageConstants.REDIS_CURRENT_ONLINE_USER, principal.getName(), isOnline);

            String status = isOnline ? "Online" : "Offline";

            log.debug("[Herodotus] |- WebSocket user [{}] is [{}].", principal, status);

            int count = WebSocketUtils.getOnlineCount();

            webSocketMessageSender.online(count);
        }
    }

    protected void connected(WebSocketPrincipal principal) {
        changeStatus(principal, true);
    }

    protected void disconnected(WebSocketPrincipal principal) {
        changeStatus(principal, false);
    }
}
