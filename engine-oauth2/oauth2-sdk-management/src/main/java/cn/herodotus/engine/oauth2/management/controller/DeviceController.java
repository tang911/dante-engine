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

import cn.herodotus.engine.assistant.definition.constants.DefaultConstants;
import cn.herodotus.engine.assistant.definition.constants.SymbolConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Description: 设备激活 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/3/24 17:09
 */
@Controller
public class DeviceController {

    @GetMapping(DefaultConstants.DEVICE_ACTIVATION_URI)
    public String activate(@RequestParam(value = OAuth2ParameterNames.USER_CODE, required = false) String userCode) {
        if (StringUtils.isNotBlank(userCode)) {
            return "redirect:" + DefaultConstants.DEVICE_VERIFICATION_ENDPOINT + SymbolConstants.QUESTION + OAuth2ParameterNames.USER_CODE + SymbolConstants.EQUAL + userCode;
        }
        return "activation";
    }

    @GetMapping(value = DefaultConstants.DEVICE_VERIFICATION_SUCCESS_URI)
    public String activated() {
        return "activation-allowed";
    }
}
