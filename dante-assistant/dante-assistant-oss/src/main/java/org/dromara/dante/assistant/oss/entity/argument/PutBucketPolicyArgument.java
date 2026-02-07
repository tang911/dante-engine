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

import cn.herodotus.stirrup.assistant.oss.definition.argument.AbstractExpectedBucketOwnerArgument;
import cn.herodotus.stirrup.assistant.oss.enums.BucketPolicy;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 设置存储桶策略请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/5 17:24
 */
@Schema(name = "设置存储桶策略请求参数实体", title = "设置存储桶策略请求参数实体")
public class PutBucketPolicyArgument extends AbstractExpectedBucketOwnerArgument {

    @Schema(name = "存储桶权限")
    private BucketPolicy bucketPolicy = BucketPolicy.PRIVATE;

    @Schema(name = "请求体的MD5哈希值", description = "该值会自动计算可以不用设置")
    private String contentMD5;

    @Schema(name = "请求校验算法", description = "该值会自动计算可以不用设置")
    private String checksumAlgorithm;

    @Schema(name = "是否删除用户自己的存储桶策略")
    private Boolean confirmRemoveSelfBucketAccess;

    public BucketPolicy getBucketPolicy() {
        return bucketPolicy;
    }

    public void setBucketPolicy(BucketPolicy bucketPolicy) {
        this.bucketPolicy = bucketPolicy;
    }

    public String getContentMD5() {
        return contentMD5;
    }

    public void setContentMD5(String contentMD5) {
        this.contentMD5 = contentMD5;
    }

    public String getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    public void setChecksumAlgorithm(String checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    public Boolean getConfirmRemoveSelfBucketAccess() {
        return confirmRemoveSelfBucketAccess;
    }

    public void setConfirmRemoveSelfBucketAccess(Boolean confirmRemoveSelfBucketAccess) {
        this.confirmRemoveSelfBucketAccess = confirmRemoveSelfBucketAccess;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bucketPolicy", bucketPolicy)
                .add("contentMD5", contentMD5)
                .add("checksumAlgorithm", checksumAlgorithm)
                .add("confirmRemoveSelfBucketAccess", confirmRemoveSelfBucketAccess)
                .toString();
    }
}
