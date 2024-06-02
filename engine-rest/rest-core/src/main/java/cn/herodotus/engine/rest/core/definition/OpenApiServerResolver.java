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

package cn.herodotus.engine.rest.core.definition;

import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

/**
 * <p>Description: OpenApi Server 处理器 </p>
 * <p>
 * 单体和分布式架构，提供给OpenAPI展示的地址不同。
 *
 * @author : gengwei.zheng
 * @date : 2022/1/16 18:56
 */
public interface OpenApiServerResolver {

    /**
     * 获取 Open Api 所需的 Server 地址。
     *
     * @return Open Api Servers 值
     */
    List<Server> getServers();
}
