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

package cn.herodotus.engine.captcha.autoconfigure;

import cn.herodotus.engine.captcha.behavior.renderer.JigsawCaptchaRenderer;
import cn.herodotus.engine.captcha.behavior.renderer.WordClickCaptchaRenderer;
import cn.herodotus.engine.captcha.core.definition.enums.CaptchaCategory;
import cn.herodotus.engine.captcha.core.provider.ResourceProvider;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: 行为验证码配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/18 20:57
 */
@AutoConfiguration
public class BehaviorCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BehaviorCaptchaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Captcha Behavior] Auto Configure.");
    }

    @Bean(CaptchaCategory.JIGSAW_CAPTCHA)
    public JigsawCaptchaRenderer jigsawCaptchaRenderer(ResourceProvider resourceProvider) {
        JigsawCaptchaRenderer jigsawCaptchaRenderer = new JigsawCaptchaRenderer();
        jigsawCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Herodotus] |- Bean [Jigsaw Captcha Renderer] Auto Configure.");
        return jigsawCaptchaRenderer;
    }

    @Bean(CaptchaCategory.WORD_CLICK_CAPTCHA)
    public WordClickCaptchaRenderer wordClickCaptchaRenderer(ResourceProvider resourceProvider) {
        WordClickCaptchaRenderer wordClickCaptchaRenderer = new WordClickCaptchaRenderer();
        wordClickCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Herodotus] |- Bean [Word Click Captcha Renderer] Auto Configure.");
        return wordClickCaptchaRenderer;
    }
}
