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

package cn.herodotus.engine.supplier.upms.logic.domain.deserializer;

import cn.herodotus.engine.supplier.upms.logic.entity.hr.SysEmployee;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <p>Description: SysEmployee 反序列化 空对象 '{}' 转 为 null </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/10/10 20:19
 */
public class SysEmployeeEmptyToNull extends JsonDeserializer<SysEmployee> {

    @Override
    public SysEmployee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.readValueAsTree();
        if (jsonNode.isEmpty() || jsonNode.isNull()) {
            return null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.treeToValue(jsonNode, SysEmployee.class);
        }
    }
}
