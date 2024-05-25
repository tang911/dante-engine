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

package cn.herodotus.engine.message.core.constants;

/**
 * <p>Description: 消息通道定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/11/22 18:49
 */
public interface HerodotusChannels {

    /**
     * Mqtt 默认入站通道
     */
    String MQTT_DEFAULT_INBOUND_CHANNEL = "mqttDefaultInboundChannel";
    /**
     * Mqtt 默认出站通道
     */
    String MQTT_DEFAULT_OUTBOUND_CHANNEL = "mqttDefaultOutboundChannel";
    /**
     * Emqx 默认的监控指标数据数据 Mqtt 类型入站通道
     */
    String EMQX_DEFAULT_MONITOR_MQTT_INBOUND_CHANNEL = "emqxDefaultMonitorMqttInboundChannel";
    /**
     * Emqx 默认的 Webhook 数据 HTTP 类型入站通道
     */
    String EMQX_DEFAULT_WEBHOOK_HTTP_INBOUND_CHANNEL = "emqxDefaultWebhookHttpInboundChannel";
    /**
     * Emqx 默认的系统时间数据 EVENT 类型出站通道
     */
    String EMQX_DEFAULT_EVENT_OUTBOUND_CHANNEL = "emqxDefaultEventOutboundChannel";
}
