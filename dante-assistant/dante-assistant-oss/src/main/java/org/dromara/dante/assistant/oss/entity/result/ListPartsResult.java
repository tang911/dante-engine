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

import cn.herodotus.stirrup.assistant.oss.definition.result.AbstractUploadResult;
import cn.herodotus.stirrup.assistant.oss.entity.domain.OwnerDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PartDomain;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Description: 分片上传列表响应结果对象实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/24 0:02
 */
@Schema(name = "分片上传列表响应结果对象实体", title = "分片上传列表响应结果对象实体")
public class ListPartsResult extends AbstractUploadResult {

    private LocalDateTime abortDate;

    private String abortRuleId;

    private String uploadId;

    private Integer partNumberMarker;

    private Integer nextPartNumberMarker;

    private Integer maxParts;

    private Boolean isTruncated;

    private List<PartDomain> parts;

    private OwnerDomain initiator;

    private OwnerDomain owner;

    private String storageClass;

    private String checksumAlgorithm;

    public LocalDateTime getAbortDate() {
        return abortDate;
    }

    public void setAbortDate(LocalDateTime abortDate) {
        this.abortDate = abortDate;
    }

    public String getAbortRuleId() {
        return abortRuleId;
    }

    public void setAbortRuleId(String abortRuleId) {
        this.abortRuleId = abortRuleId;
    }

    public String getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    public void setChecksumAlgorithm(String checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    public OwnerDomain getInitiator() {
        return initiator;
    }

    public void setInitiator(OwnerDomain initiator) {
        this.initiator = initiator;
    }

    public Boolean getTruncated() {
        return isTruncated;
    }

    public void setTruncated(Boolean truncated) {
        isTruncated = truncated;
    }

    public Integer getMaxParts() {
        return maxParts;
    }

    public void setMaxParts(Integer maxParts) {
        this.maxParts = maxParts;
    }

    public Integer getNextPartNumberMarker() {
        return nextPartNumberMarker;
    }

    public void setNextPartNumberMarker(Integer nextPartNumberMarker) {
        this.nextPartNumberMarker = nextPartNumberMarker;
    }

    public OwnerDomain getOwner() {
        return owner;
    }

    public void setOwner(OwnerDomain owner) {
        this.owner = owner;
    }

    public Integer getPartNumberMarker() {
        return partNumberMarker;
    }

    public void setPartNumberMarker(Integer partNumberMarker) {
        this.partNumberMarker = partNumberMarker;
    }

    public List<PartDomain> getParts() {
        return parts;
    }

    public void setParts(List<PartDomain> parts) {
        this.parts = parts;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("abortDate", abortDate)
                .add("abortRuleId", abortRuleId)
                .add("uploadId", uploadId)
                .add("partNumberMarker", partNumberMarker)
                .add("nextPartNumberMarker", nextPartNumberMarker)
                .add("maxParts", maxParts)
                .add("isTruncated", isTruncated)
                .add("parts", parts)
                .add("initiator", initiator)
                .add("owner", owner)
                .add("storageClass", storageClass)
                .add("checksumAlgorithm", checksumAlgorithm)
                .toString();
    }
}
