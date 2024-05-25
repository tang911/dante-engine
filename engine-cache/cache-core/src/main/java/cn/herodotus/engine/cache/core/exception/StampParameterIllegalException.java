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

package cn.herodotus.engine.cache.core.exception;

import cn.herodotus.engine.assistant.definition.exception.PlatformException;

/**
 * <p>Description: 请求参数中缺少幂等Token错误 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/23 12:29
 */
public class StampParameterIllegalException extends PlatformException {

    public StampParameterIllegalException() {
    }

    public StampParameterIllegalException(String message) {
        super(message);
    }

    public StampParameterIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampParameterIllegalException(Throwable cause) {
        super(cause);
    }

    public StampParameterIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
