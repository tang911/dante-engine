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

import cn.herodotus.stirrup.assistant.oss.definition.result.AbstractObjectResult;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PutObjectDomain;
import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;

/**
 * <p>Description: Head方式操作对象响应结果实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 23:35
 */
public class HeadObjectResult extends AbstractObjectResult {

    private String acceptRanges;

    private String expiration;

    private String restore;

    private String archiveStatus;

    private LocalDateTime lastModified;

    private String eTag;

    private Integer missingMeta;

    private String replicationStatus;

    private Integer partsCount;

    private String expiresString;

    private ChecksumDomain checksum = new ChecksumDomain();

    private PutObjectDomain metadata = new PutObjectDomain();

    public String getAcceptRanges() {
        return acceptRanges;
    }

    public void setAcceptRanges(String acceptRanges) {
        this.acceptRanges = acceptRanges;
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public ChecksumDomain getChecksum() {
        return checksum;
    }

    public void setChecksum(ChecksumDomain checksum) {
        this.checksum = checksum;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getExpiresString() {
        return expiresString;
    }

    public void setExpiresString(String expiresString) {
        this.expiresString = expiresString;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public PutObjectDomain getMetadata() {
        return metadata;
    }

    public void setMetadata(PutObjectDomain metadata) {
        this.metadata = metadata;
    }

    public Integer getMissingMeta() {
        return missingMeta;
    }

    public void setMissingMeta(Integer missingMeta) {
        this.missingMeta = missingMeta;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }

    public String getReplicationStatus() {
        return replicationStatus;
    }

    public void setReplicationStatus(String replicationStatus) {
        this.replicationStatus = replicationStatus;
    }

    public String getRestore() {
        return restore;
    }

    public void setRestore(String restore) {
        this.restore = restore;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("acceptRanges", acceptRanges)
                .add("expiration", expiration)
                .add("restore", restore)
                .add("archiveStatus", archiveStatus)
                .add("lastModified", lastModified)
                .add("eTag", eTag)
                .add("missingMeta", missingMeta)
                .add("replicationStatus", replicationStatus)
                .add("partsCount", partsCount)
                .add("expiresString", expiresString)
                .add("checksum", checksum)
                .add("metadata", metadata)
                .toString();
    }
}
