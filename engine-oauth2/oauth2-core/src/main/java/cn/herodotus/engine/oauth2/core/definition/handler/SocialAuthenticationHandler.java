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

package cn.herodotus.engine.oauth2.core.definition.handler;

import cn.herodotus.engine.assistant.definition.domain.oauth2.AccessPrincipal;
import cn.herodotus.engine.oauth2.core.definition.domain.HerodotusUser;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 社交登录处理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/4/4 17:34
 */
public interface SocialAuthenticationHandler {

    /**
     * 社交登录
     * <p>
     * 1. 首先在第三方系统进行认证，或者手机号码、扫码认证。返回认证后的信息
     * 2. 根据认证返回的信息，在系统中查询是否有对应的用户信息。
     * 2.1. 如果有对应的信息，根据需要更新社交用户的信息，然后返回系统用户信息，进行登录。
     * 2.2. 如果没有对应信息，就先进行用户的注册，然后进行社交用户和系统用户的绑定。
     *
     * @param source          社交登录提供者分类
     * @param accessPrincipal 社交登录所需要的信息 {@link AccessPrincipal}
     * @return 系统用户
     * @throws AuthenticationException {@link AuthenticationException} 认证错误
     */
    HerodotusUser authentication(String source, AccessPrincipal accessPrincipal) throws AuthenticationException;

}
