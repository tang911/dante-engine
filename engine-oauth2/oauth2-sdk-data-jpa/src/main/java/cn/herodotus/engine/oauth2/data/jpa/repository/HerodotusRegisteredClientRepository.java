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

package cn.herodotus.engine.oauth2.data.jpa.repository;

import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.oauth2.data.jpa.entity.HerodotusRegisteredClient;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

/**
 * <p>Description: HerodotusRegisteredClientRepository </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/2/25 21:05
 */
public interface HerodotusRegisteredClientRepository extends BaseRepository<HerodotusRegisteredClient, String> {

    /**
     * 根据 ClientId 查询 RegisteredClient
     *
     * @param clientId OAuth2 客户端ID
     * @return OAuth2 客户端配置
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    Optional<HerodotusRegisteredClient> findByClientId(String clientId);
}
