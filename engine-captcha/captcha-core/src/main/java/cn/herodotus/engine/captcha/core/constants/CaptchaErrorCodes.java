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

package cn.herodotus.engine.captcha.core.constants;

import cn.herodotus.engine.assistant.definition.feedback.NotAcceptableFeedback;

/**
 * <p>Description: Captcha 错误代码 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/1 11:32
 */
public interface CaptchaErrorCodes {

    NotAcceptableFeedback CAPTCHA_CATEGORY_IS_INCORRECT = new NotAcceptableFeedback("验证码分类错误");
    NotAcceptableFeedback CAPTCHA_HANDLER_NOT_EXIST = new NotAcceptableFeedback("验证码处理器不存在");
    NotAcceptableFeedback CAPTCHA_HAS_EXPIRED = new NotAcceptableFeedback("验证码已过期");
    NotAcceptableFeedback CAPTCHA_IS_EMPTY = new NotAcceptableFeedback("验证码不能为空");
    NotAcceptableFeedback CAPTCHA_MISMATCH = new NotAcceptableFeedback("验证码不匹配");
    NotAcceptableFeedback CAPTCHA_PARAMETER_ILLEGAL = new NotAcceptableFeedback("验证码参数格式错误");

}
