/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Engine.
 *
 * Herodotus Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.assistant.core.exception.properties;

import cn.herodotus.engine.assistant.definition.constants.ErrorCodes;
import cn.herodotus.engine.assistant.definition.domain.Feedback;
import cn.herodotus.engine.assistant.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: Property 属性值没有设置错误 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/3/6 13:57
 */
public class PropertyValueIsNotSetException extends PlatformRuntimeException {

    public PropertyValueIsNotSetException() {
        super();
    }

    public PropertyValueIsNotSetException(String message) {
        super(message);
    }

    public PropertyValueIsNotSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyValueIsNotSetException(Throwable cause) {
        super(cause);
    }

    protected PropertyValueIsNotSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.PROPERTY_VALUE_IS_NOT_SET_EXCEPTION;
    }
}
