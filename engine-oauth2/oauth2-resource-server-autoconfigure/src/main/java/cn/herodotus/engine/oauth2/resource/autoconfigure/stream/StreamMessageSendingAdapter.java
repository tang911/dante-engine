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

package cn.herodotus.engine.oauth2.resource.autoconfigure.stream;

import cn.herodotus.engine.message.core.definition.MessageSendingAdapter;
import cn.herodotus.engine.message.core.definition.domain.StreamMessage;
import cn.herodotus.engine.message.core.definition.event.StreamMessageSendingEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cloud.stream.function.StreamBridge;

/**
 * <p>Description: Spring Cloud Stream 消息发送适配器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/26 18:01
 */
public class StreamMessageSendingAdapter implements MessageSendingAdapter<StreamMessage, StreamMessageSendingEvent<StreamMessage>> {

    private final StreamBridge streamBridge;

    public StreamMessageSendingAdapter(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void onApplicationEvent(StreamMessageSendingEvent<StreamMessage> event) {
        StreamMessage message = event.getData();

        if (ObjectUtils.isEmpty(message.getBinderName())) {
            if (ObjectUtils.isEmpty(message.getOutputContentType())) {
                streamBridge.send(message.getBindingName(), message.getBinderName(), message.getData());
            } else {
                streamBridge.send(message.getBindingName(), message.getBinderName(), message.getData(), message.getOutputContentType());
            }
        } else {
            if (ObjectUtils.isEmpty(message.getOutputContentType())) {
                streamBridge.send(message.getBindingName(), message.getData());
            } else {
                streamBridge.send(message.getBindingName(), message.getData(), message.getOutputContentType());
            }
        }
    }
}
