/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Stirrup.
 *
 * Herodotus Stirrup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Stirrup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.stirrup.assistant.oss.enums;

import cn.herodotus.stirrup.core.domain.Dictionary;
import cn.herodotus.stirrup.core.domain.DictionaryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 对象锁定保存模式 </p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/6 21:03
 */
@Schema(name = "对象锁定保存模式")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ObjectRetentionMode implements DictionaryEnum {

    GOVERNANCE("GOVERNANCE", "GOVERNANCE"),
    COMPLIANCE("COMPLIANCE", "COMPLIANCE");

    private static final Map<String, ObjectRetentionMode> INDEX_MAP = new HashMap<>();
    private static final List<Dictionary> DICTIONARIES = new ArrayList<>();

    static {
        for (ObjectRetentionMode policy : ObjectRetentionMode.values()) {
            INDEX_MAP.put(policy.name(), policy);
            DICTIONARIES.add(policy.getDictionary(policy.name(), policy.ordinal()));
        }
    }

    @Schema(name = "枚举值")
    private final String value;
    @Schema(name = "说明")
    private final String label;

    ObjectRetentionMode(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static ObjectRetentionMode get(String index) {
        return INDEX_MAP.get(index);
    }

    public static List<Dictionary> getDictionaries() {
        return DICTIONARIES;
    }
}
