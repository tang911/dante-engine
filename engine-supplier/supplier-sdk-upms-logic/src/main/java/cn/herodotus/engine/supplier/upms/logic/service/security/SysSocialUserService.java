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

package cn.herodotus.engine.supplier.upms.logic.service.security;

import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.data.core.service.BaseService;
import cn.herodotus.engine.supplier.upms.logic.entity.security.SysSocialUser;
import cn.herodotus.engine.supplier.upms.logic.repository.security.SysSocialUserRepository;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 社会化登录用户服务 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/5/16 16:29
 */
@Service
public class SysSocialUserService extends BaseService<SysSocialUser, String> {

    private final SysSocialUserRepository sysSocialUserRepository;

    public SysSocialUserService(SysSocialUserRepository sysSocialUserRepository) {
        this.sysSocialUserRepository = sysSocialUserRepository;
    }

    @Override
    public BaseRepository<SysSocialUser, String> getRepository() {
        return sysSocialUserRepository;
    }

    public SysSocialUser findByUuidAndSource(String uuid, String source) {
        return sysSocialUserRepository.findSysSocialUserByUuidAndSource(uuid, source);
    }
}
