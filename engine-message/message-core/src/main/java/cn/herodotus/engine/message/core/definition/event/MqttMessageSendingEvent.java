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

package cn.herodotus.engine.message.core.definition.event;

import cn.herodotus.engine.message.core.definition.domain.MqttMessage;

import java.time.Clock;

/**
 * <p>Description: Mqtt 类型消息 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/11/2 16:05
 */
public class MqttMessageSendingEvent extends AbstractApplicationEvent<MqttMessage> {

    public MqttMessageSendingEvent(MqttMessage data) {
        super(data);
    }

    public MqttMessageSendingEvent(MqttMessage data, Clock clock) {
        super(data, clock);
    }
}
