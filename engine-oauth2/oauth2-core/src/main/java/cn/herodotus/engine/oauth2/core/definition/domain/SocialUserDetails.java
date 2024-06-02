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

package cn.herodotus.engine.oauth2.core.definition.domain;

/**
 * <p>Description: 社交登录用户信息详情 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/5/16 17:47
 */
public interface SocialUserDetails {

    /**
     * 获取社交登录唯一标识
     *
     * @return String
     */
    String getUuid();

    /**
     * 获取社交登录分类标识
     *
     * @return String
     */
    String getSource();

    String getPhoneNumber();

    String getAvatar();

    String getUsername();

    String getNickname();
}
