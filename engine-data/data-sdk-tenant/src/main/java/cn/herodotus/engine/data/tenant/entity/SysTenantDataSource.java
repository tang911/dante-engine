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

package cn.herodotus.engine.data.tenant.entity;

import cn.herodotus.engine.data.core.constants.DataConstants;
import cn.herodotus.engine.data.core.entity.BaseSysEntity;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 租户数据源管理 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/3/28 21:45
 */
@Schema(title = "多租户数据源管理")
@Entity
@Table(name = "sys_tenant_datasource",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"tenant_id"})},
        indexes = {@Index(name = "sys_tenant_datasource_id_idx", columnList = "datasource_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = DataConstants.REGION_SYS_TENANT_DATASOURCE)
public class SysTenantDataSource extends BaseSysEntity {

    @Schema(name = "租户数据源主键")
    @Id
    @UuidGenerator
    @Column(name = "datasource_id", length = 64)
    private String datasourceId;

    @Schema(name = "租户ID", description = "租户的唯一标识")
    @Column(name = "tenant_id", length = 64, unique = true)
    private String tenantId;

    @Schema(name = "数据库用户名")
    @Column(name = "user_name", length = 100)
    private String username;

    @Schema(name = "数据库密码")
    @Column(name = "password", length = 100)
    private String password;

    @Schema(name = "数据库驱动")
    @Column(name = "driver_class_name", length = 64)
    private String driverClassName;

    @Schema(name = "数据库连接")
    @Column(name = "url", length = 1000)
    private String url;

    @Schema(name = "是否已经初始化", description = "默认值 false")
    private Boolean initialize = false;

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getInitialize() {
        return initialize;
    }

    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("datasourceId", datasourceId)
                .add("tenantId", tenantId)
                .add("username", username)
                .add("password", password)
                .add("driverClassName", driverClassName)
                .add("url", url)
                .add("initialize", initialize)
                .toString();
    }
}
