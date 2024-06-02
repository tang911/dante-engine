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

package cn.herodotus.engine.access.core.definition;

import cn.herodotus.engine.assistant.definition.domain.oauth2.AccessPrincipal;

/**
 * <p>Description: 外部应用接入处理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/25 16:20
 */
public interface AccessHandler {

    /**
     * 外部应用接入预处理
     * 比如 微信小程序需要传入Code 和 AppId
     * 比如 手机登录需要传入手机号码等
     *
     * @param core   对于只需要一个参数就可以进行预处理操作的核心值。
     * @param params 核心值以外的其它参数
     * @return {@link  AccessResponse}
     */
    AccessResponse preProcess(String core, String... params);

    /**
     * 获取接入系统中的用户信息，并转换为系统可以识别的 {@link AccessUserDetails} 类型
     *
     * @param source          类别
     * @param accessPrincipal 外部系统接入所需信息
     * @return 外部系统用户信息 {@link AccessUserDetails}
     */
    AccessUserDetails loadUserDetails(String source, AccessPrincipal accessPrincipal);
}
