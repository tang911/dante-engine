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

package cn.herodotus.engine.message.websocket.listener;

import cn.herodotus.engine.message.websocket.definition.AbstractWebSocketStatusListener;
import cn.herodotus.engine.message.websocket.definition.WebSocketMessageSender;
import cn.herodotus.engine.message.websocket.domain.WebSocketPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * <p>Description: WebSocketUserDisconnectListener </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/12/29 22:30
 */
@Component
public class WebSocketDisconnectListener extends AbstractWebSocketStatusListener<SessionDisconnectEvent> {

    public WebSocketDisconnectListener(WebSocketMessageSender webSocketMessageSender) {
        super(webSocketMessageSender);
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        WebSocketPrincipal principal = (WebSocketPrincipal) event.getUser();

        disconnected(principal);
    }
}
