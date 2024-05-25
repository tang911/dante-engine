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

package cn.herodotus.engine.data.core.entity;

import cn.herodotus.engine.assistant.definition.constants.DefaultConstants;
import cn.herodotus.engine.assistant.definition.domain.base.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * <p>Description: DISCRIMINATOR 类型多租户实体基础类 </p>
 * <p>
 * Dante Cloud 系统本身如果要改成多租户模式，还是建议使用 DATABASE 模式。
 * 根据需要指定具体是哪些实体（数据表）采用 DISCRIMINATOR 模式多租户。
 * 不要什么实体都加以防产生不必要的干扰。
 *
 * @author : gengwei.zheng
 * @date : 2023/3/29 10:46
 */
@MappedSuperclass
public abstract class BaseTenantEntity extends AbstractEntity {

    @Schema(name = "租户ID", description = "Partitioned 类型租户ID")
    @Column(name = "tenant_id", length = 20)
    private String tenantId = DefaultConstants.TENANT_ID;
}
