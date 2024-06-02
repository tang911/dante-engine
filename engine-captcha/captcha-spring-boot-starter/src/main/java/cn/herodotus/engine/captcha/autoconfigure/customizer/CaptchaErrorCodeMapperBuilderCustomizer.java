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

package cn.herodotus.engine.captcha.autoconfigure.customizer;

import cn.herodotus.engine.assistant.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.herodotus.engine.assistant.definition.function.ErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.assistant.definition.support.ErrorCodeMapperBuilder;
import cn.herodotus.engine.captcha.core.constants.CaptchaErrorCodes;
import org.springframework.core.Ordered;

/**
 * <p>Description: Captcha 错误代码映射定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/9/25 15:31
 */
public class CaptchaErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.notAcceptable(
                CaptchaErrorCodes.CAPTCHA_CATEGORY_IS_INCORRECT,
                CaptchaErrorCodes.CAPTCHA_HANDLER_NOT_EXIST,
                CaptchaErrorCodes.CAPTCHA_HAS_EXPIRED,
                CaptchaErrorCodes.CAPTCHA_IS_EMPTY,
                CaptchaErrorCodes.CAPTCHA_MISMATCH,
                CaptchaErrorCodes.CAPTCHA_PARAMETER_ILLEGAL
        );
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.CAPTCHA;
    }
}
