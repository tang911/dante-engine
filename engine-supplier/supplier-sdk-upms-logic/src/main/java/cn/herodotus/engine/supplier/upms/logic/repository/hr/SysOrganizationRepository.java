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

package cn.herodotus.engine.supplier.upms.logic.repository.hr;

import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.supplier.upms.logic.entity.hr.SysOrganization;
import cn.herodotus.engine.supplier.upms.logic.enums.OrganizationCategory;

import java.util.List;

/**
 * <p>Description: 单位管理Repository </p>
 *
 * @author : gengwei.zheng
 * @date : 2020/1/20 11:37
 */
public interface SysOrganizationRepository extends BaseRepository<SysOrganization, String> {

    /**
     * 根据组织分类查询组织
     *
     * @param category 组织分类 {@link OrganizationCategory}
     * @return 组织列表
     */
    List<SysOrganization> findByCategory(OrganizationCategory category);

}
