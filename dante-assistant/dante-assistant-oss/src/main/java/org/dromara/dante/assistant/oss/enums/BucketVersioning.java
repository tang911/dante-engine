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
 * <p>Description: 存储桶版本状态枚举 </p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/6 12:28
 */
@Schema(name = "存储桶版本状态枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BucketVersioning implements DictionaryEnum {

    ENABLED("Enabled", "启用"),
    SUSPENDED("Suspended", "暂停");

    private static final Map<String, BucketVersioning> INDEX_MAP = new HashMap<>();
    private static final List<Dictionary> DICTIONARIES = new ArrayList<>();

    static {
        for (BucketVersioning policy : BucketVersioning.values()) {
            INDEX_MAP.put(policy.name(), policy);
            DICTIONARIES.add(policy.getDictionary(policy.name(), policy.ordinal()));
        }
    }

    @Schema(name = "枚举值")
    private final String value;
    @Schema(name = "说明")
    private final String label;

    BucketVersioning(String value, String label) {
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

    public static BucketVersioning get(String index) {
        return INDEX_MAP.get(index);
    }

    public static List<Dictionary> getDictionaries() {
        return DICTIONARIES;
    }
}
