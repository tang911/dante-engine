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

package cn.herodotus.engine.oauth2.management.controller;

import cn.herodotus.engine.assistant.definition.domain.Result;
import cn.herodotus.engine.oauth2.management.service.OAuth2ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Description: OAuth2 常量 Controller </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/3/17 15:00
 */
@RestController
@RequestMapping("/authorize/constant")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "常量接口")
})
public class OAuth2ConstantController {

    private final OAuth2ConstantService constantService;

    @Autowired
    public OAuth2ConstantController(OAuth2ConstantService constantService) {
        this.constantService = constantService;
    }

    @Operation(summary = "获取服务常量", description = "获取服务涉及的常量以及信息")
    @GetMapping(value = "/enums")
    public Result<Map<String, Object>> findAllEnums() {
        Map<String, Object> allEnums = constantService.getAllEnums();
        if (MapUtils.isNotEmpty(allEnums)) {
            return Result.success("获取服务常量成功", allEnums);
        } else {
            return Result.failure("获取服务常量失败");
        }
    }
}
