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

package cn.herodotus.engine.access.wxapp.enums;

/**
 * <p>Description: 跳转小程序类型 </p>
 * <p>
 * developer为开发版；trial为体验版；formal为正式版；默认为正式版
 *
 * @author : gengwei.zheng
 * @date : 2021/4/9 16:09
 */
public enum MiniProgramState {

    /**
     * 开发版
     */
    developer,

    /**
     * 体验版
     */
    trial,

    /**
     * 正式版
     */
    formal;
}
