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

package cn.herodotus.engine.captcha.graphic.renderer;

import cn.herodotus.engine.captcha.core.definition.enums.CaptchaCategory;
import cn.herodotus.engine.captcha.graphic.definition.AbstractPngGraphicRenderer;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * <p>Description: 中文类型验证码绘制器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/12/20 22:55
 */
@Component
public class ChineseCaptchaRenderer extends AbstractPngGraphicRenderer {

    @Override
    public String getCategory() {
        return CaptchaCategory.CHINESE.getConstant();
    }

    @Override
    protected String[] getDrawCharacters() {
        return this.getWordCharacters();
    }

    @Override
    protected Font getFont() {
        return this.getResourceProvider().getChineseFont();
    }
}
