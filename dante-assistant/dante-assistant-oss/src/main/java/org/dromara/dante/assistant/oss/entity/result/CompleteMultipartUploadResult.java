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
import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 完成分片上传响应结果对象实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/24 0:00
 */
@Schema(name = "完成分片上传响应结果对象实体", title = "完成分片上传响应结果对象实体")
public class CompleteMultipartUploadResult extends AbstractUploadResult {

    private String location;

    private String expiration;

    private String eTag;

    private ChecksumDomain checksum = new ChecksumDomain();

    private String serverSideEncryption;

    private String versionId;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServerSideEncryption() {
        return serverSideEncryption;
    }

    public void setServerSideEncryption(String serverSideEncryption) {
        this.serverSideEncryption = serverSideEncryption;
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
                .add("checksum", checksum)
                .add("location", location)
                .add("expiration", expiration)
                .add("eTag", eTag)
                .add("serverSideEncryption", serverSideEncryption)
                .add("versionId", versionId)
                .toString();
    }
}
