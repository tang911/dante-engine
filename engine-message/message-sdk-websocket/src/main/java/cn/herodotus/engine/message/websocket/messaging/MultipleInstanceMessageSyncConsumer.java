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

import cn.herodotus.engine.message.core.constants.MessageConstants;
import cn.herodotus.engine.message.core.definition.domain.WebSocketMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

/**
 * <p>Description: WebSocket 点对点消息跨实例处理 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/9/14 17:06
 */
public class MultipleInstanceMessageSyncConsumer implements Consumer<WebSocketMessage> {

    private final WebSocketMessagingTemplate webSocketMessagingTemplate;

    public MultipleInstanceMessageSyncConsumer(WebSocketMessagingTemplate webSocketMessagingTemplate) {
        this.webSocketMessagingTemplate = webSocketMessagingTemplate;
    }

    @Override
    public void accept(WebSocketMessage webSocketMessage) {
        if (StringUtils.equals(webSocketMessage.getUser(), MessageConstants.MESSAGE_TO_ALL)) {
            webSocketMessagingTemplate.broadcast(webSocketMessage.getDestination(), webSocketMessage.getPayload());
        } else {
            webSocketMessagingTemplate.pointToPoint(webSocketMessage.getUser(), webSocketMessage.getDestination(), webSocketMessage.getPayload());
        }
    }
}
