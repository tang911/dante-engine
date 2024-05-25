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

package cn.herodotus.engine.supplier.upms.logic.service.hr;

import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.data.core.service.BaseService;
import cn.herodotus.engine.supplier.upms.logic.entity.hr.SysDepartment;
import cn.herodotus.engine.supplier.upms.logic.repository.hr.SysDepartmentRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 部门管理服务 </p>
 *
 * @author : gengwei.zheng
 * @date : 2020/1/20 11:50
 */
@Service
public class SysDepartmentService extends BaseService<SysDepartment, String> {

    private final SysDepartmentRepository sysDepartmentRepository;
    private final SysOwnershipService sysOwnershipService;

    public SysDepartmentService(SysDepartmentRepository sysDepartmentRepository, SysOwnershipService sysOwnershipService) {
        this.sysDepartmentRepository = sysDepartmentRepository;
        this.sysOwnershipService = sysOwnershipService;
    }

    @Override
    public BaseRepository<SysDepartment, String> getRepository() {
        return sysDepartmentRepository;
    }

    public Page<SysDepartment> findByCondition(int pageNumber, int pageSize, String organizationId) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Specification<SysDepartment> specification = (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (ObjectUtils.isNotEmpty(organizationId)) {
                predicates.add(criteriaBuilder.equal(root.get("organizationId"), organizationId));
            }

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(predicateArray)));
            return criteriaQuery.getRestriction();
        };

        return this.findByPage(specification, pageable);
    }

    public List<SysDepartment> findAll(String organizationId) {
        if (ObjectUtils.isNotEmpty(organizationId)) {
            return sysDepartmentRepository.findByOrganizationId(organizationId);
        } else {
            return sysDepartmentRepository.findAll();
        }
    }

    @Override
    public void deleteById(String departmentId) {
        sysOwnershipService.deleteByDepartmentId(departmentId);
        super.deleteById(departmentId);
    }
}
