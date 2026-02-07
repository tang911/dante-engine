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

import cn.herodotus.stirrup.assistant.oss.definition.argument.AbstractBucketArgument;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 创建存储桶请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 22:27
 */
@Schema(name = "创建存储桶请求参数实体", title = "创建存储桶请求参数实体")
public class CreateBucketArgument extends AbstractBucketArgument {

    private String acl;

    private String grantFullControl;

    private String grantRead;

    private String grantReadACP;

    private String grantWrite;

    private String grantWriteACP;

    private Boolean objectLockEnabledForBucket;

    private String objectOwnership;

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getGrantFullControl() {
        return grantFullControl;
    }

    public void setGrantFullControl(String grantFullControl) {
        this.grantFullControl = grantFullControl;
    }

    public String getGrantRead() {
        return grantRead;
    }

    public void setGrantRead(String grantRead) {
        this.grantRead = grantRead;
    }

    public String getGrantReadACP() {
        return grantReadACP;
    }

    public void setGrantReadACP(String grantReadACP) {
        this.grantReadACP = grantReadACP;
    }

    public String getGrantWrite() {
        return grantWrite;
    }

    public void setGrantWrite(String grantWrite) {
        this.grantWrite = grantWrite;
    }

    public String getGrantWriteACP() {
        return grantWriteACP;
    }

    public void setGrantWriteACP(String grantWriteACP) {
        this.grantWriteACP = grantWriteACP;
    }

    public Boolean getObjectLockEnabledForBucket() {
        return objectLockEnabledForBucket;
    }

    public void setObjectLockEnabledForBucket(Boolean objectLockEnabledForBucket) {
        this.objectLockEnabledForBucket = objectLockEnabledForBucket;
    }

    public String getObjectOwnership() {
        return objectOwnership;
    }

    public void setObjectOwnership(String objectOwnership) {
        this.objectOwnership = objectOwnership;
    }
}
