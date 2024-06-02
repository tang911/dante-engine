/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Engine.
 *
 * Herodotus Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.message.websocket.messaging;

import cn.herodotus.engine.assistant.core.context.ServiceContextHolder;
import cn.herodotus.engine.message.core.constants.MessageConstants;
import cn.herodotus.engine.message.core.definition.domain.StreamMessage;
import cn.herodotus.engine.message.core.definition.domain.WebSocketMessage;
import cn.herodotus.engine.message.core.definition.event.StreamMessageSendingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: Web Socket 多实例服务端消息发送器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/26 23:29
 */
public class MultipleInstanceMessageSender extends SingleInstanceMessageSender {

    private static final Logger log = LoggerFactory.getLogger(MultipleInstanceMessageSender.class);

    public MultipleInstanceMessageSender(WebSocketMessagingTemplate webSocketMessagingTemplate) {
        super(webSocketMessagingTemplate);
    }

    @Override
    public void toUser(String user, String destination, Object payload) {
        syncToUserMessage(user, destination, payload);
        super.toUser(user, destination, payload);
    }

    @Override
    public void toAll(String destination, Object payload) {
        syncToAllMessage(destination, payload);
        super.toAll(destination, payload);
    }

    private void syncMessageToOtherInstants(WebSocketMessage webSocketMessage) {
        StreamMessage streamMessage = new StreamMessage();
        streamMessage.setBindingName(MessageConstants.MULTIPLE_INSTANCE_OUTPUT);
        streamMessage.setData(webSocketMessage);

        log.debug("[Herodotus] |- Sync message to other WebSocket instance.");
        ServiceContextHolder.getInstance().publishEvent(new StreamMessageSendingEvent<>(streamMessage));
    }

    private void syncToUserMessage(String user, String destination, Object payload) {
        WebSocketMessage webSocketMessage = new WebSocketMessage();
        webSocketMessage.setUser(user);
        webSocketMessage.setDestination(destination);
        webSocketMessage.setPayload(payload);
        syncMessageToOtherInstants(webSocketMessage);
    }

    private void syncToAllMessage(String destination, Object payload) {
        syncToUserMessage(MessageConstants.MESSAGE_TO_ALL, destination, payload);
    }
}
