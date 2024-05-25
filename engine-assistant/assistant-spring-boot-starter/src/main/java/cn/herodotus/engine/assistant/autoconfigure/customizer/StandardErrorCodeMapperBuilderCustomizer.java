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

package cn.herodotus.engine.assistant.autoconfigure.customizer;

import cn.herodotus.engine.assistant.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.herodotus.engine.assistant.definition.constants.ErrorCodes;
import cn.herodotus.engine.assistant.definition.function.ErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.assistant.definition.support.ErrorCodeMapperBuilder;
import org.springframework.core.Ordered;

/**
 * <p>Description: 标准内置错误代码 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/9/25 0:00
 */
public class StandardErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder
                .unauthorized(ErrorCodes.ACCESS_DENIED,
                        ErrorCodes.ACCOUNT_DISABLED,
                        ErrorCodes.ACCOUNT_ENDPOINT_LIMITED,
                        ErrorCodes.ACCOUNT_EXPIRED,
                        ErrorCodes.ACCOUNT_LOCKED,
                        ErrorCodes.BAD_CREDENTIALS,
                        ErrorCodes.CREDENTIALS_EXPIRED,
                        ErrorCodes.INVALID_CLIENT,
                        ErrorCodes.INVALID_TOKEN,
                        ErrorCodes.INVALID_GRANT,
                        ErrorCodes.UNAUTHORIZED_CLIENT,
                        ErrorCodes.USERNAME_NOT_FOUND,
                        ErrorCodes.SESSION_EXPIRED)
                .forbidden(ErrorCodes.INSUFFICIENT_SCOPE, ErrorCodes.SQL_INJECTION_REQUEST)
                .methodNotAllowed(ErrorCodes.HTTP_REQUEST_METHOD_NOT_SUPPORTED)
                .notAcceptable(ErrorCodes.UNSUPPORTED_GRANT_TYPE, ErrorCodes.UNSUPPORTED_RESPONSE_TYPE, ErrorCodes.UNSUPPORTED_TOKEN_TYPE)
                .preconditionFailed(ErrorCodes.INVALID_REDIRECT_URI, ErrorCodes.INVALID_REQUEST, ErrorCodes.INVALID_SCOPE, ErrorCodes.METHOD_ARGUMENT_NOT_VALID)
                .unsupportedMediaType(ErrorCodes.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE)
                .internalServerError(ErrorCodes.SERVER_ERROR,
                        ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION,
                        ErrorCodes.ILLEGAL_ARGUMENT_EXCEPTION,
                        ErrorCodes.IO_EXCEPTION,
                        ErrorCodes.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION,
                        ErrorCodes.NULL_POINTER_EXCEPTION,
                        ErrorCodes.TYPE_MISMATCH_EXCEPTION)
                .notImplemented(ErrorCodes.PROPERTY_VALUE_IS_NOT_SET_EXCEPTION, ErrorCodes.URL_FORMAT_INCORRECT_EXCEPTION, ErrorCodes.ILLEGAL_SYMMETRIC_KEY, ErrorCodes.DISCOVERED_UNRECORDED_ERROR_EXCEPTION)
                .serviceUnavailable(ErrorCodes.COOKIE_THEFT, ErrorCodes.INVALID_COOKIE, ErrorCodes.PROVIDER_NOT_FOUND, ErrorCodes.TEMPORARILY_UNAVAILABLE, ErrorCodes.SEARCH_IP_LOCATION)
                .customize(ErrorCodes.TRANSACTION_ROLLBACK,
                        ErrorCodes.BAD_SQL_GRAMMAR,
                        ErrorCodes.DATA_INTEGRITY_VIOLATION,
                        ErrorCodes.PIPELINE_INVALID_COMMANDS);
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.STANDARD;
    }
}
