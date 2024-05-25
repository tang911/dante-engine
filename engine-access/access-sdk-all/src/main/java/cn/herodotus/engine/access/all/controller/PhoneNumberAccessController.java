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

import cn.herodotus.engine.access.all.processor.AccessHandlerStrategyFactory;
import cn.herodotus.engine.access.core.definition.AccessResponse;
import cn.herodotus.engine.assistant.definition.domain.Result;
import cn.herodotus.engine.assistant.core.enums.AccountType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 手机验证码登录 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/5/28 11:39
 */
@RestController
@Tag(name = "手机验证码登录接口")
public class PhoneNumberAccessController {

    @Autowired
    private AccessHandlerStrategyFactory accessHandlerStrategyFactory;

    @Operation(summary = "手机验证码发送地址", description = "接收手机号码，发送验证码，并缓存至Redis")
    @Parameters({
            @Parameter(name = "mobile", required = true, description = "手机号码"),
    })
    @PostMapping("/open/identity/verification-code")
    public Result<String> sendCode(@RequestParam("mobile") String mobile) {
        AccessResponse response = accessHandlerStrategyFactory.preProcess(AccountType.SMS, mobile);
        if (ObjectUtils.isNotEmpty(response)) {
            if (response.getSuccess()) {
                return Result.success("短信发送成功！");
            } else {
                return Result.failure("短信发送失败！");
            }
        }
        return Result.failure("手机号码接收失败！");
    }
}
