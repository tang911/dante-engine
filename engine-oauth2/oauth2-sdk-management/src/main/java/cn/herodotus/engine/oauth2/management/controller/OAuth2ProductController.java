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

import cn.herodotus.engine.data.core.service.WriteableService;
import cn.herodotus.engine.oauth2.management.entity.OAuth2Product;
import cn.herodotus.engine.oauth2.management.service.OAuth2ProductService;
import cn.herodotus.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2ProductController </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/15 16:37
 */
@RestController
@RequestMapping("/authorize/product")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "物联网管理接口"),
        @Tag(name = "物联网产品接口")
})
public class OAuth2ProductController extends BaseWriteableRestController<OAuth2Product, String> {

    private final OAuth2ProductService iotProductService;

    public OAuth2ProductController(OAuth2ProductService iotProductService) {
        this.iotProductService = iotProductService;
    }

    @Override
    public WriteableService<OAuth2Product, String> getWriteableService() {
        return iotProductService;
    }
}
