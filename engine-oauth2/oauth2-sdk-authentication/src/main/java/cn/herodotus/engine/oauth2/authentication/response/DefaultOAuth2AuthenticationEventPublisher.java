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

package cn.herodotus.engine.oauth2.authentication.response;

import cn.herodotus.engine.oauth2.core.constants.OAuth2ErrorKeys;
import cn.herodotus.engine.oauth2.core.exception.AccountEndpointLimitedException;
import cn.herodotus.engine.oauth2.core.exception.SessionExpiredException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * <p>Description: 扩展的 DefaultAuthenticationEventPublisher </p>
 * <p>
 * 支持 OAuth2AuthenticationException 解析
 *
 * @author : gengwei.zheng
 * @date : 2022/7/9 13:47
 */
public class DefaultOAuth2AuthenticationEventPublisher extends DefaultAuthenticationEventPublisher {

    public DefaultOAuth2AuthenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        super.publishAuthenticationFailure(convert(exception), authentication);
    }

    private AuthenticationException convert(AuthenticationException exception) {
        if (exception instanceof OAuth2AuthenticationException authenticationException) {
            OAuth2Error error = authenticationException.getError();

            return switch (error.getErrorCode()) {
                case OAuth2ErrorKeys.ACCOUNT_EXPIRED ->
                        new AccountExpiredException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorKeys.CREDENTIALS_EXPIRED ->
                        new CredentialsExpiredException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorKeys.ACCOUNT_DISABLED ->
                        new DisabledException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorKeys.ACCOUNT_LOCKED ->
                        new LockedException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorKeys.ACCOUNT_ENDPOINT_LIMITED ->
                        new AccountEndpointLimitedException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorKeys.USERNAME_NOT_FOUND ->
                        new UsernameNotFoundException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorKeys.SESSION_EXPIRED ->
                        new SessionExpiredException(exception.getMessage(), exception.getCause());
                default -> new BadCredentialsException(exception.getMessage(), exception.getCause());
            };
        }

        return exception;
    }
}
