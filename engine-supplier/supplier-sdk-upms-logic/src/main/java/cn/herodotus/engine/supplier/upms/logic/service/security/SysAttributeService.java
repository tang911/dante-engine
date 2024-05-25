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
import cn.herodotus.engine.supplier.upms.logic.entity.security.SysAttribute;
import cn.herodotus.engine.supplier.upms.logic.entity.security.SysPermission;
import cn.herodotus.engine.supplier.upms.logic.repository.security.SysAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: SysAttributeService </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/4 6:48
 */
@Service
public class SysAttributeService extends BaseService<SysAttribute, String> {

    private final SysAttributeRepository sysAttributeRepository;

    public SysAttributeService(SysAttributeRepository sysAttributeRepository) {
        this.sysAttributeRepository = sysAttributeRepository;
    }

    @Override
    public BaseRepository<SysAttribute, String> getRepository() {
        return this.sysAttributeRepository;
    }

    public SysAttribute assign(String attributeId, String[] permissionIds) {

        Set<SysPermission> sysPermissions = new HashSet<>();
        for (String permissionId : permissionIds) {
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(permissionId);
            sysPermissions.add(sysPermission);
        }

        SysAttribute sysAttribute = findById(attributeId);
        sysAttribute.setPermissions(sysPermissions);

        return saveAndFlush(sysAttribute);
    }

    public List<SysAttribute> findAllByServiceId(String serviceId) {
        return sysAttributeRepository.findAllByServiceId(serviceId);
    }

    public List<SysAttribute> findByAttributeIdIn(List<String> ids) {
        return sysAttributeRepository.findByAttributeIdIn(ids);
    }
}
