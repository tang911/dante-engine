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

package cn.herodotus.stirrup.assistant.oss.definition.argument;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 存储桶共性参数 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 23:00
 */
public abstract class AbstractExpectedBucketOwnerArgument extends AbstractBucketArgument {

    @Schema(name = "预期存储桶所有者")
    private String expectedBucketOwner;

    public String getExpectedBucketOwner() {
        return expectedBucketOwner;
    }

    public void setExpectedBucketOwner(String expectedBucketOwner) {
        this.expectedBucketOwner = expectedBucketOwner;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("expectedBucketOwner", expectedBucketOwner)
                .addValue(super.toString())
                .toString();
    }
}
