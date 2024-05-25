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

package cn.herodotus.engine.oauth2.management.service;

import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.data.core.service.BaseService;
import cn.herodotus.engine.oauth2.management.entity.OAuth2Permission;
import cn.herodotus.engine.oauth2.management.repository.OAuth2PermissionRepository;
import org.springframework.stereotype.Service;

/**
 * <p>Description: OAuth2PermissionService </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/4/1 13:53
 */
@Service
public class OAuth2PermissionService extends BaseService<OAuth2Permission, String> {

    private final OAuth2PermissionRepository authorityRepository;

    public OAuth2PermissionService(OAuth2PermissionRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public BaseRepository<OAuth2Permission, String> getRepository() {
        return authorityRepository;
    }
}
