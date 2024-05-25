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

package cn.herodotus.engine.captcha.core.processor;

import cn.herodotus.engine.captcha.core.definition.Renderer;
import cn.herodotus.engine.captcha.core.definition.enums.CaptchaCategory;
import cn.herodotus.engine.captcha.core.dto.Captcha;
import cn.herodotus.engine.captcha.core.dto.Verification;
import cn.herodotus.engine.captcha.core.exception.CaptchaCategoryIsIncorrectException;
import cn.herodotus.engine.captcha.core.exception.CaptchaHandlerNotExistException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: Captcha 工厂 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/12/14 14:38
 */
@Component
public class CaptchaRendererFactory {

    @Autowired
    private final Map<String, Renderer> handlers = new ConcurrentHashMap<>(8);

    public Renderer getRenderer(String category) {
        CaptchaCategory captchaCategory = CaptchaCategory.getCaptchaCategory(category);

        if (ObjectUtils.isEmpty(captchaCategory)) {
            throw new CaptchaCategoryIsIncorrectException("Captcha category is incorrect.");
        }

        Renderer renderer = handlers.get(captchaCategory.getConstant());
        if (ObjectUtils.isEmpty(renderer)) {
            throw new CaptchaHandlerNotExistException();
        }

        return renderer;
    }

    public Captcha getCaptcha(String identity, String category) {
        Renderer renderer = getRenderer(category);
        return renderer.getCapcha(identity);
    }

    public boolean verify(Verification verification) {
        Renderer renderer = getRenderer(verification.getCategory());
        return renderer.verify(verification);
    }
}
