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

package cn.herodotus.engine.oauth2.core.definition.strategy;

import cn.herodotus.engine.assistant.definition.domain.oauth2.AccessPrincipal;
import cn.herodotus.engine.oauth2.core.definition.domain.HerodotusUser;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 系统用户服务策略定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/2/17 10:52
 */
public interface StrategyUserDetailsService {

    HerodotusUser findUserDetailsByUsername(String username) throws AuthenticationException;

    HerodotusUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException;
}
