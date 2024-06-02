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

package cn.herodotus.engine.message.mqtt.messaging;

import cn.herodotus.engine.message.mqtt.gateway.MessageSendingGateway;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Mqtt 消息发送模版 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/31 12:07
 */
@Component
public class MqttMessagingTemplate {

    private final MessageSendingGateway messageSendingGateway;

    public MqttMessagingTemplate(MessageSendingGateway messageSendingGateway) {
        this.messageSendingGateway = messageSendingGateway;
    }

    public void publish(String payload) {
        messageSendingGateway.publish(payload);
    }

    public void publish(Integer qos, String payload) {
        messageSendingGateway.publish(qos, payload);
    }

    public void publish(String topic, String payload) {
        messageSendingGateway.publish(topic, payload);
    }

    public void publish(String topic, Integer qos, String payload) {
        messageSendingGateway.publish(topic, qos, payload);
    }

    public void publish(String topic, String responseTopic, String correlationData, Integer qos, String payload) {
        messageSendingGateway.publish(topic, responseTopic, correlationData, qos, payload);
    }
}
