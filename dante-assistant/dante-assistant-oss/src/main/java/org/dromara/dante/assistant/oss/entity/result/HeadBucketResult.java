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
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: Head方式操作存储桶返回响应实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 23:03
 */
@Schema(name = "Head方式操作存储桶返回响应实体", title = "Head方式操作存储桶返回响应实体")
public class HeadBucketResult extends AbstractResult {

    private String bucketLocationType;

    private String bucketLocationName;

    private String bucketRegion;

    private Boolean accessPointAlias;

    public Boolean getAccessPointAlias() {
        return accessPointAlias;
    }

    public void setAccessPointAlias(Boolean accessPointAlias) {
        this.accessPointAlias = accessPointAlias;
    }

    public String getBucketLocationName() {
        return bucketLocationName;
    }

    public void setBucketLocationName(String bucketLocationName) {
        this.bucketLocationName = bucketLocationName;
    }

    public String getBucketLocationType() {
        return bucketLocationType;
    }

    public void setBucketLocationType(String bucketLocationType) {
        this.bucketLocationType = bucketLocationType;
    }

    public String getBucketRegion() {
        return bucketRegion;
    }

    public void setBucketRegion(String bucketRegion) {
        this.bucketRegion = bucketRegion;
    }
}
