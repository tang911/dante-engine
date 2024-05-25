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

package cn.herodotus.engine.message.autoconfigure;

import cn.herodotus.engine.assistant.definition.function.ErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.message.autoconfigure.customizer.MessageErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.message.mqtt.annotation.EnableHerodotusMqtt;
import cn.herodotus.engine.message.websocket.annotation.EnableHerodotusWebSocket;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Message 模块自动注入配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/2/4 17:08
 */
@AutoConfiguration
@EnableHerodotusWebSocket
@EnableHerodotusMqtt
public class MessageAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Message Starter] Auto Configure.");
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer messageErrorCodeMapperBuilderCustomizer() {
        MessageErrorCodeMapperBuilderCustomizer customizer = new MessageErrorCodeMapperBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [Message ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
