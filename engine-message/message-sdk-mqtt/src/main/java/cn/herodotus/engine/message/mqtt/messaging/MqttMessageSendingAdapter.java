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

import cn.herodotus.engine.message.core.definition.MessageSendingAdapter;
import cn.herodotus.engine.message.core.definition.domain.MqttMessage;
import cn.herodotus.engine.message.core.definition.event.MqttMessageSendingEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Mqtt 消息发送适配器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/11/2 13:43
 */
@Component
public class MqttMessageSendingAdapter implements MessageSendingAdapter<MqttMessage, MqttMessageSendingEvent> {

    private final MqttMessagingTemplate mqttMessagingTemplate;

    public MqttMessageSendingAdapter(MqttMessagingTemplate mqttMessagingTemplate) {
        this.mqttMessagingTemplate = mqttMessagingTemplate;
    }

    @Override
    public void onApplicationEvent(MqttMessageSendingEvent event) {
        MqttMessage mqttMessage = event.getData();

        if (StringUtils.isNotBlank(mqttMessage.getTopic()) && ObjectUtils.isNotEmpty(mqttMessage.getQos())) {
            if (StringUtils.isNotBlank(mqttMessage.getResponseTopic()) && StringUtils.isNotBlank(mqttMessage.getCorrelationData())) {
                mqttMessagingTemplate.publish(mqttMessage.getTopic(),
                        mqttMessage.getResponseTopic(),
                        mqttMessage.getCorrelationData(),
                        mqttMessage.getQos(),
                        mqttMessage.getPayload());
            } else {
                mqttMessagingTemplate.publish(mqttMessage.getTopic(), mqttMessage.getQos(), mqttMessage.getPayload());
            }
        } else {
            if (StringUtils.isNotBlank(mqttMessage.getTopic())) {
                mqttMessagingTemplate.publish(mqttMessage.getTopic(), mqttMessage.getPayload());
            }

            if (ObjectUtils.isNotEmpty(mqttMessage.getQos())) {
                mqttMessagingTemplate.publish(mqttMessage.getQos(), mqttMessage.getPayload());
            }
        }
    }
}
