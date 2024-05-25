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

package cn.herodotus.engine.supplier.upms.logic.repository.hr;

import cn.herodotus.engine.assistant.core.exception.transaction.TransactionalRollbackException;
import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.supplier.upms.logic.entity.hr.SysOwnership;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Description: 人事归属Repository </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/7/15 16:28
 */
public interface SysOwnershipRepository extends BaseRepository<SysOwnership, String> {

    /**
     * 根据单位ID删除人事归属
     * <p>
     * 从操作的完整性上应该包含该操作，但是这个操作风险很大，会删除较多内容
     *
     * @param organizationId 单位ID
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query(value = "delete from SysOwnership o where o.organizationId = :organizationId")
    void deleteByOrganizationId(String organizationId);

    /**
     * 根据单位ID删除人事归属
     * <p>
     * 从操作的完整性上应该包含该操作，但是这个操作风险很大，会删除较多内容
     *
     * @param departmentId 部门ID
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query(value = "delete from SysOwnership o where o.departmentId = :departmentId")
    void deleteByDepartmentId(String departmentId);

    /**
     * 根据单位ID删除人事归属
     * <p>
     * 从操作的完整性上应该包含该操作，但是这个操作风险很大，会删除较多内容
     *
     * @param employeeId 人员ID
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query(value = "delete from SysOwnership o where o.employeeId = :employeeId")
    void deleteByEmployeeId(String employeeId);

    /**
     * 删除人事归属
     *
     * @param organizationId 单位ID
     * @param departmentId   部门ID
     * @param employeeId     人员ID
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query(value = "delete from SysOwnership o where o.organizationId = :organizationId and o.departmentId = :departmentId and o.employeeId = :employeeId")
    void deleteByOrganizationIdAndDepartmentIdAndEmployeeId(String organizationId, String departmentId, String employeeId);

}
