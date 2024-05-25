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

package cn.herodotus.engine.oauth2.core.exception;

import cn.herodotus.engine.assistant.definition.constants.ErrorCodes;
import cn.herodotus.engine.assistant.definition.exception.HerodotusException;
import cn.herodotus.engine.assistant.definition.domain.Feedback;
import cn.herodotus.engine.assistant.definition.domain.Result;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.Authentication;

/**
 * <p>Description: OAuth2 验证码基础 Exception </p>
 * <p>
 * 这里没有用基础定义的 PlatformAuthorizationException。主要问题是在自定义表单登录时，如果使用基础的 {@link org.springframework.security.core.AuthenticationException}，
 * 在 Spring Security 标准代码中该Exception将不会抛出，而是进行二次的用户验证，这将导致在验证过程中直接跳过验证码的校验。
 *
 * @author : gengwei.zheng
 * @date : 2022/4/12 22:33
 * @see org.springframework.security.authentication.ProviderManager#authenticate(Authentication)
 */
public class OAuth2CaptchaException extends AccountStatusException implements HerodotusException {

    public OAuth2CaptchaException(String msg) {
        super(msg);
    }

    public OAuth2CaptchaException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.INTERNAL_SERVER_ERROR;
    }

    @Override
    public Result<String> getResult() {
        Result<String> result = Result.failure(getFeedback());
        result.stackTrace(super.getStackTrace());
        result.detail(super.getMessage());
        return result;
    }
}
