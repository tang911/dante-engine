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

import cn.herodotus.engine.message.websocket.messaging.WebSocketMessagingTemplate;

/**
 * <p>Description: WebSocketMessageSender 抽象实现 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/26 23:45
 */
public abstract class AbstractWebSocketMessageSender implements WebSocketMessageSender {

    private final WebSocketMessagingTemplate webSocketMessagingTemplate;

    protected AbstractWebSocketMessageSender(WebSocketMessagingTemplate webSocketMessagingTemplate) {
        this.webSocketMessagingTemplate = webSocketMessagingTemplate;
    }

    @Override
    public void toUser(String user, String destination, Object payload) {
        webSocketMessagingTemplate.pointToPoint(user, destination, payload);
    }

    @Override
    public void toAll(String destination, Object payload) {
        webSocketMessagingTemplate.broadcast(destination, payload);
    }
}
