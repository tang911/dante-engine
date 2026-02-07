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

package cn.herodotus.stirrup.assistant.oss.entity.argument;

import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PutObjectDomain;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * <p>Description: writeGetObjectResponse 操作请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 23:57
 */
@Schema(name = "writeGetObjectResponse 操作请求参数实体", title = "writeGetObjectResponse 操作请求参数实体")
public class WriteGetObjectResponseArgument extends PutObjectDomain {

    private String requestRoute;

    private String requestToken;

    private Integer statusCode;

    private String errorCode;

    private String errorMessage;

    private String acceptRanges;

    private Boolean deleteMarker;

    private String eTag;

    private String expiration;

    private LocalDateTime lastModified;

    private Integer missingMeta;

    private Integer partsCount;

    private String replicationStatus;

    private String requestCharged;

    private String restore;

    private String versionId;

    private Integer tagCount;

    private ChecksumDomain checksum = new ChecksumDomain();

    public String getAcceptRanges() {
        return acceptRanges;
    }

    public void setAcceptRanges(String acceptRanges) {
        this.acceptRanges = acceptRanges;
    }

    public ChecksumDomain getChecksum() {
        return checksum;
    }

    public void setChecksum(ChecksumDomain checksum) {
        this.checksum = checksum;
    }

    public Boolean getDeleteMarker() {
        return deleteMarker;
    }

    public void setDeleteMarker(Boolean deleteMarker) {
        this.deleteMarker = deleteMarker;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
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

    public String getRequestCharged() {
        return requestCharged;
    }

    public void setRequestCharged(String requestCharged) {
        this.requestCharged = requestCharged;
    }

    public String getRequestRoute() {
        return requestRoute;
    }

    public void setRequestRoute(String requestRoute) {
        this.requestRoute = requestRoute;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getRestore() {
        return restore;
    }

    public void setRestore(String restore) {
        this.restore = restore;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getTagCount() {
        return tagCount;
    }

    public void setTagCount(Integer tagCount) {
        this.tagCount = tagCount;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("acceptRanges", acceptRanges)
                .add("requestRoute", requestRoute)
                .add("requestToken", requestToken)
                .add("statusCode", statusCode)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("deleteMarker", deleteMarker)
                .add("eTag", eTag)
                .add("expiration", expiration)
                .add("lastModified", lastModified)
                .add("missingMeta", missingMeta)
                .add("partsCount", partsCount)
                .add("replicationStatus", replicationStatus)
                .add("requestCharged", requestCharged)
                .add("restore", restore)
                .add("versionId", versionId)
                .add("tagCount", tagCount)
                .add("checksum", checksum)
                .toString();
    }
}
