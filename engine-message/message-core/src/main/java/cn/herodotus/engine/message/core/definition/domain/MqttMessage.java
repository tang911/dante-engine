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

package cn.herodotus.engine.message.core.definition.domain;

import com.google.common.base.MoreObjects;

/**
 * <p>Description: Mqtt 类型消息参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/11/2 16:05
 */
public class MqttMessage implements Message {

    private String topic;
    private String responseTopic;
    private String correlationData;
    private Integer qos;
    private String payload;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getResponseTopic() {
        return responseTopic;
    }

    public void setResponseTopic(String responseTopic) {
        this.responseTopic = responseTopic;
    }

    public String getCorrelationData() {
        return correlationData;
    }

    public void setCorrelationData(String correlationData) {
        this.correlationData = correlationData;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("topic", topic)
                .add("responseTopic", responseTopic)
                .add("correlationData", correlationData)
                .add("qos", qos)
                .add("payload", payload)
                .toString();
    }
}
