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

package cn.herodotus.engine.assistant.autoconfigure.customizer;

import com.fasterxml.jackson.databind.Module;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * <p>Description: 提取公共操作 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/4/29 17:09
 */
public interface BaseObjectMapperBuilderCustomizer extends Jackson2ObjectMapperBuilderCustomizer, Ordered {

    default Module[] toArray(List<Module> modules) {
        if (CollectionUtils.isNotEmpty(modules)) {
            Module[] temps = new Module[modules.size()];
            return modules.toArray(temps);
        } else {
            return new Module[]{};
        }
    }
}
