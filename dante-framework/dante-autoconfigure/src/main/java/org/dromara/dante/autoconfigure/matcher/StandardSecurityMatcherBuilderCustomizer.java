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

package org.dromara.dante.autoconfigure.matcher;

import org.dromara.dante.core.builder.SecurityMatcher;
import org.dromara.dante.core.function.SecurityMatcherBuilderCustomizer;

import java.util.List;

/**
 * <p>Description: 默认 SecurityMatcher 配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2025/12/13 18:53
 */
public class StandardSecurityMatcherBuilderCustomizer implements SecurityMatcherBuilderCustomizer {

    @Override
    public void customize(SecurityMatcher.Builder builder) {
        // 每个服务都有 Swagger，所以统一配置 Swagger 静态资源
        builder.staticResources(List.of(
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v3/api-docs",
                "/v3/api-docs/**"));
    }
}
