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

package cn.herodotus.engine.assistant.core.json.jackson2.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

/**
 * <p>Description: Set集合反序列化为逗号分隔字符串 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/22 13:55
 */
public class ArrayToCommaDelimitedStringDeserializer extends StdDeserializer<String> {

    protected ArrayToCommaDelimitedStringDeserializer() {
        super(String.class);
    }

    public JavaType getValueType() {
        return TypeFactory.defaultInstance().constructType(Set.class);
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        // spring-cloud-function-context.jar 会开启DeserializationFeature.FAIL_ON_TRAILING_TOKENS，这会导致前端传过来的数组无法解析抛错
        // see ContextFunctionCatalogAutoConfiguration#JsonMapperConfiguration
        // 临时新建一个 ObjectMapper，解决数组解析
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
        jsonParser.setCodec(objectMapper);

        Set<String> collection = jsonParser.readValueAs(new TypeReference<Set<String>>() {
        });

        if (CollectionUtils.isNotEmpty(collection)) {
            return StringUtils.collectionToCommaDelimitedString(collection);
        }

        return null;
    }
}
