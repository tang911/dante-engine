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

package cn.herodotus.engine.oauth2.data.jpa.converter;

import cn.herodotus.engine.oauth2.data.jpa.definition.converter.AbstractRegisteredClientConverter;
import cn.herodotus.engine.oauth2.data.jpa.entity.HerodotusRegisteredClient;
import cn.herodotus.engine.oauth2.data.jpa.jackson2.OAuth2JacksonProcessor;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * <p>Description: HerodotusRegisteredClient 转 换适配器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/12 23:56
 */
public class HerodotusToOAuth2RegisteredClientConverter extends AbstractRegisteredClientConverter<HerodotusRegisteredClient> {

    public HerodotusToOAuth2RegisteredClientConverter(OAuth2JacksonProcessor jacksonProcessor) {
        super(jacksonProcessor);
    }

    @Override
    public Set<String> getScopes(HerodotusRegisteredClient details) {
        return StringUtils.commaDelimitedListToSet(details.getScopes());
    }

    @Override
    public ClientSettings getClientSettings(HerodotusRegisteredClient details) {
        Map<String, Object> clientSettingsMap = parseMap(details.getClientSettings());
        return ClientSettings.withSettings(clientSettingsMap).build();
    }

    @Override
    public TokenSettings getTokenSettings(HerodotusRegisteredClient details) {
        Map<String, Object> tokenSettingsMap = parseMap(details.getTokenSettings());
        return TokenSettings.withSettings(tokenSettingsMap).build();
    }
}
