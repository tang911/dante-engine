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

package cn.herodotus.stirrup.assistant.oss.entity.domain;

import cn.herodotus.stirrup.assistant.oss.definition.domain.OssDomain;
import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Description: 对象域对象 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 23:46
 */
public class ObjectDomain implements OssDomain {

    private String objectName;

    private LocalDateTime lastModified;

    private String eTag;

    private List<String> checksumAlgorithm;

    private Long size;

    private String storageClass;

    private OwnerDomain owner;

    private RestoreStatusDomain restoreStatus;

    private Boolean isDir = Boolean.FALSE;

    public List<String> getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    public void setChecksumAlgorithm(List<String> checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public Boolean getDir() {
        return isDir;
    }

    public void setDir(Boolean dir) {
        isDir = dir;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public OwnerDomain getOwner() {
        return owner;
    }

    public void setOwner(OwnerDomain owner) {
        this.owner = owner;
    }

    public RestoreStatusDomain getRestoreStatus() {
        return restoreStatus;
    }

    public void setRestoreStatus(RestoreStatusDomain restoreStatus) {
        this.restoreStatus = restoreStatus;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("checksumAlgorithm", checksumAlgorithm)
                .add("objectName", objectName)
                .add("lastModified", lastModified)
                .add("eTag", eTag)
                .add("size", size)
                .add("storageClass", storageClass)
                .add("owner", owner)
                .add("restoreStatus", restoreStatus)
                .add("isDir", isDir)
                .toString();
    }
}
