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

import cn.herodotus.stirrup.assistant.oss.definition.argument.AbstractArgument;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 存储桶列表请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 22:35
 */
@Schema(name = "存储桶列表请求参数实体", title = "存储桶列表请求参数实体")
public class ListBucketsArgument extends AbstractArgument {

    private Integer maxBuckets;

    private String continuationToken;

    private String prefix;

    private String bucketRegion;

    public Integer getMaxBuckets() {
        return maxBuckets;
    }

    public void setMaxBuckets(Integer maxBuckets) {
        this.maxBuckets = maxBuckets;
    }

    public String getContinuationToken() {
        return continuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getBucketRegion() {
        return bucketRegion;
    }

    public void setBucketRegion(String bucketRegion) {
        this.bucketRegion = bucketRegion;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("maxBuckets", maxBuckets)
                .add("continuationToken", continuationToken)
                .add("prefix", prefix)
                .add("bucketRegion", bucketRegion)
                .toString();
    }
}
