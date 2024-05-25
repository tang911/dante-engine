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

package cn.herodotus.engine.oauth2.data.jpa.jackson2;

import cn.herodotus.engine.assistant.core.json.jackson2.utils.JsonNodeUtils;
import cn.herodotus.engine.oauth2.core.definition.domain.HerodotusGrantedAuthority;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.dromara.hutool.core.reflect.FieldUtil;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.io.IOException;
import java.util.Set;

/**
 * <p>Description: OAuth2ClientAuthenticationTokenDeserializer </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/10/24 14:43
 */
public class OAuth2ClientAuthenticationTokenDeserializer extends JsonDeserializer<OAuth2ClientAuthenticationToken> {

    private static final TypeReference<Set<HerodotusGrantedAuthority>> HERODOTUS_GRANTED_AUTHORITY_SET = new TypeReference<Set<HerodotusGrantedAuthority>>() {
    };

    @Override
    public OAuth2ClientAuthenticationToken deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JacksonException {

        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode jsonNode = mapper.readTree(jsonParser);
        return deserialize(jsonParser, mapper, jsonNode);
    }

    private OAuth2ClientAuthenticationToken deserialize(JsonParser parser, ObjectMapper mapper, JsonNode root) throws IOException {
        Set<HerodotusGrantedAuthority> authorities = JsonNodeUtils.findValue(root, "authorities", HERODOTUS_GRANTED_AUTHORITY_SET, mapper);
        RegisteredClient registeredClient = JsonNodeUtils.findValue(root, "registeredClient", new TypeReference<RegisteredClient>() {
        }, mapper);
        String credentials = JsonNodeUtils.findStringValue(root, "credentials");
        ClientAuthenticationMethod clientAuthenticationMethod = JsonNodeUtils.findValue(root, "clientAuthenticationMethod", new TypeReference<ClientAuthenticationMethod>() {
        }, mapper);

        OAuth2ClientAuthenticationToken clientAuthenticationToken = new OAuth2ClientAuthenticationToken(registeredClient, clientAuthenticationMethod, credentials);
        if (CollectionUtils.isNotEmpty(authorities)) {
            FieldUtil.setFieldValue(clientAuthenticationToken, "authorities", authorities);
        }
        return clientAuthenticationToken;
    }
}
