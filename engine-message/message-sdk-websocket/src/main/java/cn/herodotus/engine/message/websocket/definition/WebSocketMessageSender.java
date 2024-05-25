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

import cn.herodotus.engine.message.core.constants.MessageConstants;
import cn.herodotus.engine.message.core.definition.domain.WebSocketMessage;

/**
 * <p>Description: WebSocket 消息发送操作定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/26 23:32
 */
public interface WebSocketMessageSender {

    /**
     * 发送 WebSocket 点对点消息。发送信息给指定用户
     *
     * @param user        用户唯一标识
     * @param destination 消息通道
     * @param payload     消息内容
     */
    void toUser(String user, String destination, Object payload);

    /**
     * 送 WebSocket 点对点消息。发送信息给指定用户
     *
     * @param webSocketMessage 消息实体 {@link WebSocketMessage}
     */
    default void toUser(WebSocketMessage webSocketMessage) {
        toUser(webSocketMessage.getUser(), webSocketMessage.getDestination(), webSocketMessage.getPayload());
    }

    /**
     * 发送 WebSocket 广播消息。发送全员信息
     *
     * @param destination 消息通道
     * @param payload     消息内容
     */
    void toAll(String destination, Object payload);

    /**
     * 发送公告信息
     *
     * @param payload 消息内容
     */
    default void announcement(Object payload) {
        toAll(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_NOTICE, payload);
    }

    /**
     * 发送实时在线用户统计信息
     *
     * @param payload 消息内容
     */
    default void online(Object payload) {
        toAll(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_ONLINE, payload);
    }
}
