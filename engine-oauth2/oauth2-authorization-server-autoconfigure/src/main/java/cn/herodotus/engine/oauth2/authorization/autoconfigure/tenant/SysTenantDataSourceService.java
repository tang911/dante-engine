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

package cn.herodotus.engine.oauth2.authorization.autoconfigure.tenant;

import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.data.core.service.BaseService;
import cn.herodotus.engine.data.tenant.entity.SysTenantDataSource;
import cn.herodotus.engine.data.tenant.repository.SysTenantDataSourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 多租户数据源 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/3/29 21:20
 */
@Service
public class SysTenantDataSourceService extends BaseService<SysTenantDataSource, String> {

    private static final Logger log = LoggerFactory.getLogger(SysTenantDataSourceService.class);

    private final SysTenantDataSourceRepository sysTenantDataSourceRepository;

    public SysTenantDataSourceService(SysTenantDataSourceRepository sysTenantDataSourceRepository) {
        this.sysTenantDataSourceRepository = sysTenantDataSourceRepository;
    }

    @Override
    public BaseRepository<SysTenantDataSource, String> getRepository() {
        return sysTenantDataSourceRepository;
    }

    public SysTenantDataSource findByTenantId(String tenantId) {
        SysTenantDataSource sysRole = sysTenantDataSourceRepository.findByTenantId(tenantId);
        log.debug("[Herodotus] |- SysTenantDataSource Service findByTenantId.");
        return sysRole;
    }
}
