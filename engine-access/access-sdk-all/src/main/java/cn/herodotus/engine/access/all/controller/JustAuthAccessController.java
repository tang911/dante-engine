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

package cn.herodotus.engine.access.all.controller;

import cn.herodotus.engine.access.all.event.AutomaticSignInEvent;
import cn.herodotus.engine.access.justauth.processor.JustAuthProcessor;
import cn.herodotus.engine.assistant.definition.domain.Result;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.zhyd.oauth.model.AuthCallback;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 社交登录第三方系统返回的Redirect Url </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/5/28 11:35
 */
@RestController
@Tag(name = "社交登录Redirect Url")
public class JustAuthAccessController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private JustAuthProcessor justAuthProcessor;

    @Operation(summary = "社交登录redirect url地址", description = "社交登录标准模式的redirect url地址，获取第三方登录返回的code")
    @Parameters({
            @Parameter(name = "source", required = true, description = "社交登录的类型，具体指定是哪一个第三方系统", in = ParameterIn.PATH),
    })
    @RequestMapping("/open/identity/social/{source}")
    public void callback(@PathVariable("source") String source, AuthCallback callback) {
        if (StringUtils.isNotBlank(source) && BeanUtil.isNotEmpty(callback)) {
            Map<String, Object> params = ImmutableMap.of("source", source, "callback", callback);
            applicationContext.publishEvent(new AutomaticSignInEvent(params));
        }
    }

    @Operation(summary = "获取社交登录列表", description = "根据后台已配置社交登录信息，返回可用的社交登录控制列表")
    @GetMapping("/open/identity/sources")
    public Result<Map<String, String>> list() {
        Map<String, String> list = justAuthProcessor.getAuthorizeUrls();
        if (MapUtils.isNotEmpty(list)) {
            return Result.success("获取社交登录列表成功", list);
        } else {
            return Result.success("社交登录没有配置", new HashMap<>());
        }
    }
}
