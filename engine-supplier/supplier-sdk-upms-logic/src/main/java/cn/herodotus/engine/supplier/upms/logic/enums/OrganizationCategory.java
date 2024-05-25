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

package cn.herodotus.engine.supplier.upms.logic.enums;

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
 * <p>Description: 机构类别 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/9/21 11:39
 */
@Schema(title = "机构类别")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrganizationCategory implements BaseUiEnum<Integer> {

    /**
     * enum
     */
    ENTERPRISE(0, "企业机构"),
    PARTY(1, "党组机构"),
    LEAGUE(2, "团青机构");

    private static final Map<Integer, OrganizationCategory> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (OrganizationCategory organizationCategory : OrganizationCategory.values()) {
            INDEX_MAP.put(organizationCategory.getValue(), organizationCategory);
            JSON_STRUCTURE.add(organizationCategory.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", organizationCategory.getValue())
                            .put("key", organizationCategory.name())
                            .put("text", organizationCategory.getDescription())
                            .put("index", organizationCategory.getValue())
                            .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(title = "文字")
    private final String description;

    OrganizationCategory(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static OrganizationCategory get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }

    /**
     * 不加@JsonValue，转换的时候转换出完整的对象。
     * 加了@JsonValue，只会显示相应的属性的值
     * <p>
     * 不使用@JsonValue @JsonDeserializer类里面要做相应的处理
     *
     * @return Enum索引
     */
    @JsonValue
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
