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

/**
 * <p>Description: 存储桶通用实体对象 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 22:55
 */
public class BucketDomain implements OssDomain {

    private String bucketName;

    private LocalDateTime creationDate;
    /**
     * BucketRegion表示存储桶所在的亚马逊网络服务（AWS）区域。如果请求中至少包含一个有效参数，则该参数将包含在响应中。
     */
    private String bucketRegion;
    /**
     * S3存储桶的亚马逊资源名称（ARN）。ARN在所有亚马逊网络服务中唯一标识亚马逊网络服务资源。
     * 此参数仅支持用于S3目录存储桶
     */
    private String bucketArn;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getBucketRegion() {
        return bucketRegion;
    }

    public void setBucketRegion(String bucketRegion) {
        this.bucketRegion = bucketRegion;
    }

    public String getBucketArn() {
        return bucketArn;
    }

    public void setBucketArn(String bucketArn) {
        this.bucketArn = bucketArn;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bucketName", bucketName)
                .add("creationDate", creationDate)
                .add("bucketRegion", bucketRegion)
                .add("bucketArn", bucketArn)
                .toString();
    }
}
