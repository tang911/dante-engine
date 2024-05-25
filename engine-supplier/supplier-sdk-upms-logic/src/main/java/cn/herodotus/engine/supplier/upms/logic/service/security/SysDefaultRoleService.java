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

import cn.herodotus.engine.assistant.core.enums.AccountType;
import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.data.core.service.BaseService;
import cn.herodotus.engine.supplier.upms.logic.entity.security.SysDefaultRole;
import cn.herodotus.engine.supplier.upms.logic.entity.security.SysRole;
import cn.herodotus.engine.supplier.upms.logic.repository.security.SysDefaultRoleRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 系统默认角色Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/7/21 16:09
 */
@Service
public class SysDefaultRoleService extends BaseService<SysDefaultRole, String> {

    private final SysDefaultRoleRepository sysDefaultRoleRepository;
    private final SysRoleService sysRoleService;

    public SysDefaultRoleService(SysDefaultRoleRepository sysDefaultRoleRepository, SysRoleService sysRoleService) {
        this.sysDefaultRoleRepository = sysDefaultRoleRepository;
        this.sysRoleService = sysRoleService;
    }

    @Override
    public BaseRepository<SysDefaultRole, String> getRepository() {
        return this.sysDefaultRoleRepository;
    }

    public SysDefaultRole findByScene(AccountType scene) {
        return this.sysDefaultRoleRepository.findByScene(scene);
    }


    public SysDefaultRole assign(String defaultId, String roleId) {
        SysRole sysRole = sysRoleService.findByRoleId(roleId);
        SysDefaultRole sysDefaultRole = sysDefaultRoleRepository.findByDefaultId(defaultId);
        if (ObjectUtils.isNotEmpty(sysDefaultRole) && ObjectUtils.isNotEmpty(sysRole)) {
            sysDefaultRole.setRole(sysRole);
            return sysDefaultRoleRepository.saveAndFlush(sysDefaultRole);
        }

        return null;
    }
}
