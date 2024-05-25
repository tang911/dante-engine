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

import cn.herodotus.engine.captcha.core.definition.enums.CaptchaCategory;
import cn.herodotus.engine.captcha.core.provider.ResourceProvider;
import cn.herodotus.engine.captcha.hutool.renderer.CircleCaptchaRenderer;
import cn.herodotus.engine.captcha.hutool.renderer.GifCaptchaRenderer;
import cn.herodotus.engine.captcha.hutool.renderer.LineCaptchaRenderer;
import cn.herodotus.engine.captcha.hutool.renderer.ShearCaptchaRenderer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Hutool 验证码配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/18 20:57
 */
@AutoConfiguration
public class HutoolCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HutoolCaptchaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Captcha Hutool] Auto Configure.");
    }

    @Bean(CaptchaCategory.HUTOOL_LINE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public LineCaptchaRenderer lineCaptchaRenderer(ResourceProvider resourceProvider) {
        LineCaptchaRenderer lineCaptchaRenderer = new LineCaptchaRenderer();
        lineCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Herodotus] |- Bean [Hutool Line Captcha Renderer] Auto Configure.");
        return lineCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_CIRCLE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public CircleCaptchaRenderer circleCaptchaRenderer(ResourceProvider resourceProvider) {
        CircleCaptchaRenderer circleCaptchaRenderer = new CircleCaptchaRenderer();
        circleCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Herodotus] |- Bean [Hutool Circle Captcha Renderer] Auto Configure.");
        return circleCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_SHEAR_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ShearCaptchaRenderer shearCaptchaRenderer(ResourceProvider resourceProvider) {
        ShearCaptchaRenderer shearCaptchaRenderer = new ShearCaptchaRenderer();
        shearCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Herodotus] |- Bean [Hutool Shear Captcha Renderer] Auto Configure.");
        return shearCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public GifCaptchaRenderer gifCaptchaRenderer(ResourceProvider resourceProvider) {
        GifCaptchaRenderer gifCaptchaRenderer = new GifCaptchaRenderer();
        gifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Herodotus] |- Bean [Hutool Gif Captcha Renderer] Auto Configure.");
        return gifCaptchaRenderer;
    }
}
