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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * <p> Description : 实体通用属性 </p>
 *
 * @author : gengwei.zheng
 * @date : 2020/4/29 16:09
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends AbstractEntity {

    @Schema(name = "数据创建时间")
    @Column(name = "create_time", updatable = false)
    @CreatedDate
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    private Date createTime = new Date();

    @Schema(name = "数据更新时间")
    @Column(name = "update_time")
    @LastModifiedDate
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    private Date updateTime = new Date();

    @Schema(name = "创建人")
    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    @Schema(name = "最后修改")
    @Column(name = "update_by")
    @LastModifiedBy
    private String updateBy;

    @Schema(name = "排序值")
    @Column(name = "ranking")
    private Integer ranking = 0;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .add("createBy", createBy)
                .add("updateBy", updateBy)
                .add("ranking", ranking)
                .toString();
    }
}
