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

package cn.herodotus.engine.access.all.dto;

import cn.herodotus.engine.assistant.definition.domain.base.AbstractDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Description: 微信小程序登录请求实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/26 14:44
 */
@Schema(name = "微信小程序登录请求实体", title = "根据code和appid返回微信小程序session信息")
public class WxappProfile extends AbstractDto {

    @Schema(name = "code", title = "前端调用小程序自己的方法返回的code")
    @NotBlank(message = "微信小程序code参数不能为空")
    private String code;

    @Schema(name = "appId", title = "需要前端返回给后端appId，以支持多个小程序")
    @NotBlank(message = "微信小程序appId参数不能为空")
    private String appId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
