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

package cn.herodotus.engine.assistant.definition.function;

import cn.herodotus.engine.assistant.definition.support.ErrorCodeMapperBuilder;

/**
 * <p>Description: ErrorCodeMapperBuilder 回调接口</p>
 * <p>
 * 实现了该接口的Bean，可以在自动配置阶段，通过ErrorCodeMapperBuilder进一步扩展错误码
 *
 * @author : gengwei.zheng
 * @date : 2023/9/24 23:06
 */
@FunctionalInterface
public interface ErrorCodeMapperBuilderCustomizer {

    /**
     * 自定义 ErrorCodeMapperBuilder
     *
     * @param builder 被扩展的 {@link ErrorCodeMapperBuilder}
     */
    void customize(ErrorCodeMapperBuilder builder);
}
