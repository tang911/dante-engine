/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Stirrup.
 *
 * Herodotus Stirrup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Stirrup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.stirrup.assistant.oss.entity.result;

import cn.herodotus.stirrup.assistant.oss.definition.result.AbstractResult;
import cn.herodotus.stirrup.assistant.oss.enums.ObjectRetentionMode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * <p>Description: 对象属性信息响应结果实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 23:46
 */
@Schema(name = "对象属性信息响应结果实体", title = "对象属性信息响应结果实体")
public class GetObjectAttributesResult extends AbstractResult {

    @Schema(name = "存储桶名称")
    private String bucketName;

    @Schema(name = "文件名")
    private String objectName;

    @Schema(name = "文件锁定是否开启")
    private Boolean lockEnabled;

    @Schema(name = "文件留存是否开启")
    private Boolean lockLegalHold;

    @Schema(name = "保留至什么日期")
    private LocalDateTime retainUntilDate;

    @Schema(name = "保留至什么日期")
    private ObjectRetentionMode retentionMode;

    @Schema(name = "是否为删除标记", description = "对象位于已启用版本控制的存储桶不能物理删除，只能逻辑删除，该标记标记是否逻辑删除")
    private Boolean deleteMarker;

    @Schema(name = "最新修改时间")
    private LocalDateTime lastModified;

    @Schema(name = "版本 ID")
    private String versionId;

    @Schema(name = "ETag")
    @JsonProperty(value = "eTag")
    private String tag;

    @Schema(name = "文件大小")
    private Long size;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Boolean getLockEnabled() {
        return lockEnabled;
    }

    public void setLockEnabled(Boolean lockEnabled) {
        this.lockEnabled = lockEnabled;
    }

    public Boolean getLockLegalHold() {
        return lockLegalHold;
    }

    public void setLockLegalHold(Boolean lockLegalHold) {
        this.lockLegalHold = lockLegalHold;
    }

    public LocalDateTime getRetainUntilDate() {
        return retainUntilDate;
    }

    public void setRetainUntilDate(LocalDateTime retainUntilDate) {
        this.retainUntilDate = retainUntilDate;
    }

    public ObjectRetentionMode getRetentionMode() {
        return retentionMode;
    }

    public void setRetentionMode(ObjectRetentionMode retentionMode) {
        this.retentionMode = retentionMode;
    }

    public Boolean getDeleteMarker() {
        return deleteMarker;
    }

    public void setDeleteMarker(Boolean deleteMarker) {
        this.deleteMarker = deleteMarker;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bucketName", bucketName)
                .add("objectName", objectName)
                .add("lockEnabled", lockEnabled)
                .add("lockLegalHold", lockLegalHold)
                .add("retainUntilDate", retainUntilDate)
                .add("retentionMode", retentionMode)
                .add("deleteMarker", deleteMarker)
                .add("lastModified", lastModified)
                .add("versionId", versionId)
                .add("tag", tag)
                .add("size", size)
                .addValue(super.toString())
                .toString();
    }
}
