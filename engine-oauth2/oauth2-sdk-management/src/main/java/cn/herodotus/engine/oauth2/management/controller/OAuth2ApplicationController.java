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
import cn.herodotus.engine.data.core.service.WriteableService;
import cn.herodotus.engine.oauth2.management.entity.OAuth2Application;
import cn.herodotus.engine.oauth2.management.service.OAuth2ApplicationService;
import cn.herodotus.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2应用管理接口 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/3/1 18:52
 */
@RestController
@RequestMapping("/authorize/application")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 应用管理接口")
})
public class OAuth2ApplicationController extends BaseWriteableRestController<OAuth2Application, String> {

    private final OAuth2ApplicationService applicationService;

    public OAuth2ApplicationController(OAuth2ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public WriteableService<OAuth2Application, String> getWriteableService() {
        return this.applicationService;
    }

    @Operation(summary = "给应用分配Scope", description = "给应用分配Scope")
    @Parameters({
            @Parameter(name = "appKey", required = true, description = "appKey"),
            @Parameter(name = "scopes[]", required = true, description = "Scope对象组成的数组")
    })
    @PutMapping
    public Result<OAuth2Application> authorize(@RequestParam(name = "applicationId") String scopeId, @RequestParam(name = "scopes[]") String[] scopes) {
        OAuth2Application application = applicationService.authorize(scopeId, scopes);
        return result(application);
    }
}
