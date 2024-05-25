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

package cn.herodotus.engine.captcha.core.definition.enums;

/**
 * <p>Description: 验证码资源 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/12/11 15:27
 */
public enum CaptchaResource {

    /**
     * 验证码资源类型
     */
    JIGSAW_ORIGINAL("Jigsaw original image", "滑动拼图底图"),
    JIGSAW_TEMPLATE("Jigsaw template image", "滑动拼图滑块底图"),
    WORD_CLICK("Word click image", "文字点选底图");

    private final String content;
    private final String description;

    CaptchaResource(String type, String description) {
        this.content = type;
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }
}
