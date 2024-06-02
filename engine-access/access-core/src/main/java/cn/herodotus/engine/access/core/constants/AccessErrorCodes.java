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

package cn.herodotus.engine.access.core.constants;

import cn.herodotus.engine.assistant.definition.feedback.PreconditionFailedFeedback;

/**
 * <p>Description: Access 模块错误代码 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/9/2 17:50
 */
public interface AccessErrorCodes {

    PreconditionFailedFeedback ACCESS_CONFIG_ERROR = new PreconditionFailedFeedback("Access 模块配置错误");
    PreconditionFailedFeedback ACCESS_HANDLER_NOT_FOUND = new PreconditionFailedFeedback("Access 模块接入处理器未找到错误");
    PreconditionFailedFeedback ACCESS_IDENTITY_VERIFICATION_FAILED = new PreconditionFailedFeedback("接入身份认证错误");
    PreconditionFailedFeedback ACCESS_PRE_PROCESS_FAILED_EXCEPTION = new PreconditionFailedFeedback("接入预操作失败错误");

    PreconditionFailedFeedback ILLEGAL_ACCESS_ARGUMENT = new PreconditionFailedFeedback("社交登录参数错误");
    PreconditionFailedFeedback ILLEGAL_ACCESS_SOURCE = new PreconditionFailedFeedback("社交登录Source参数错误");
}
