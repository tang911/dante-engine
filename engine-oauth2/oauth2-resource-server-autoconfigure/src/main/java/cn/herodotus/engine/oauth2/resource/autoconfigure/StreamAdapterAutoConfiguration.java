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

package cn.herodotus.engine.oauth2.resource.autoconfigure;

import cn.herodotus.engine.oauth2.resource.autoconfigure.stream.StreamMessageSendingAdapter;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.stream.function.FunctionConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Stream 消息发送适配器配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/27 23:25
 */
@AutoConfiguration(after = FunctionConfiguration.class)
@ConditionalOnBean(StreamBridge.class)
public class StreamAdapterAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(StreamAdapterAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Stream Adapter] Auto Configure.");
    }

    @Bean
    public StreamMessageSendingAdapter streamMessageSendingAdapter(StreamBridge streamBridge) {
        StreamMessageSendingAdapter adapter = new StreamMessageSendingAdapter(streamBridge);
        log.trace("[Herodotus] |- Bean [Stream Message Sending Adapter] Auto Configure.");
        return adapter;
    }
}
