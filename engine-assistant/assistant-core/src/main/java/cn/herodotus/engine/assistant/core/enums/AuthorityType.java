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

package cn.herodotus.engine.assistant.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 权限资源类型 </p>
 *
 * @author : gengwei.zheng
 * @date : 2019/11/25 15:10
 */
@Schema(title = "权限类型")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorityType {

    /**
     * enum
     */
    API(0, "REST API"),
    MENU(1, "功能菜单"),
    PAGE(2, "Web Page"),
    MINI_PAGE(3, "小程序页面");

    private static final Map<Integer, AuthorityType> indexMap = new HashMap<>();
    private static final List<Map<String, Object>> toJsonStruct = new ArrayList<>();

    static {
        for (AuthorityType authorityType : AuthorityType.values()) {
            indexMap.put(authorityType.getIndex(), authorityType);
            toJsonStruct.add(authorityType.getIndex(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", authorityType.getIndex())
                            .put("key", authorityType.name())
                            .put("text", authorityType.getText())
                            .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer index;
    @Schema(title = "文字")
    private final String text;

    AuthorityType(Integer index, String text) {
        this.index = index;
        this.text = text;
    }

    public static AuthorityType getAuthorityType(Integer index) {
        return indexMap.get(index);
    }

    public static List<Map<String, Object>> getToJsonStruct() {
        return toJsonStruct;
    }

    /**
     * 不加@JsonValue，转换的时候转换出完整的对象。
     * 加了@JsonValue，只会显示相应的属性的值
     * <p>
     * 不使用@JsonValue @JsonDeserializer类里面要做相应的处理
     *
     * @return Enum枚举值
     */
    @JsonValue
    public Integer getIndex() {
        return index;
    }

    public String getText() {
        return this.text;
    }
}
