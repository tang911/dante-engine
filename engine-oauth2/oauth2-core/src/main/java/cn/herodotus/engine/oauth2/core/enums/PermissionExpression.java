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

package cn.herodotus.engine.oauth2.core.enums;

import cn.herodotus.engine.assistant.definition.enums.BaseUiEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 安全表达式 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/14 3:49
 */
@Schema(title = "Security 权限表达式")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PermissionExpression implements BaseUiEnum<String> {
    /**
     * 权限表达式
     */
    PERMIT_ALL("permitAll", "permitAll"),
    ANONYMOUS("anonymous", "anonymous"),
    REMEMBER_ME("rememberMe", "rememberMe"),
    DENY_ALL("denyAll", "denyAll"),
    AUTHENTICATED("authenticated", "authenticated"),
    FULLY_AUTHENTICATED("fullyAuthenticated", "fullyAuthenticated");

    private static final Map<String, PermissionExpression> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (PermissionExpression permissionExpression : PermissionExpression.values()) {
            INDEX_MAP.put(permissionExpression.getValue(), permissionExpression);
            JSON_STRUCTURE.add(permissionExpression.ordinal(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", permissionExpression.getValue())
                            .put("key", permissionExpression.name())
                            .put("text", permissionExpression.getDescription())
                            .put("index", permissionExpression.ordinal())
                            .build());
        }
    }

    @Schema(title = "索引")
    private final String value;
    @Schema(title = "说明")
    private final String description;

    PermissionExpression(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static PermissionExpression get(String value) {
        return INDEX_MAP.get(value);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
