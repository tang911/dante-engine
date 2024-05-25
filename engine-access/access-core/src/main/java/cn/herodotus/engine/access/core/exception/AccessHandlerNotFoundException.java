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

package cn.herodotus.engine.access.core.exception;

import cn.herodotus.engine.access.core.constants.AccessErrorCodes;
import cn.herodotus.engine.assistant.definition.domain.Feedback;
import cn.herodotus.engine.assistant.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 接入处理器未找到错误 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/26 12:03
 */
public class AccessHandlerNotFoundException extends PlatformRuntimeException {

    public AccessHandlerNotFoundException() {
        super();
    }

    public AccessHandlerNotFoundException(String message) {
        super(message);
    }

    public AccessHandlerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessHandlerNotFoundException(Throwable cause) {
        super(cause);
    }

    public AccessHandlerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_HANDLER_NOT_FOUND;
    }
}
