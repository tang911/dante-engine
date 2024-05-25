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

package cn.herodotus.engine.captcha.behavior.dto;

import cn.herodotus.engine.captcha.core.dto.Captcha;
import com.google.common.base.MoreObjects;

/**
 * <p>Description: 文字点选验证码返回前台信息 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/12/14 11:35
 */
public class WordClickCaptcha extends Captcha {

    /**
     * 文字点选验证码生成的带文字背景图。
     */
    private String wordClickImageBase64;

    /**
     * 文字点选验证码文字
     */
    private String words;

    /**
     * 需要点击的文字数量
     */
    private Integer wordsCount;

    public String getWordClickImageBase64() {
        return wordClickImageBase64;
    }

    public void setWordClickImageBase64(String wordClickImageBase64) {
        this.wordClickImageBase64 = wordClickImageBase64;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Integer getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(Integer wordsCount) {
        this.wordsCount = wordsCount;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("wordClickImageBase64", wordClickImageBase64)
                .add("words", words)
                .add("wordsCount", wordsCount)
                .toString();
    }
}
