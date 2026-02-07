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

package cn.herodotus.stirrup.assistant.oss.definition.result;

import com.google.common.base.MoreObjects;

/**
 * <p>Description: 对象存储响应结果抽象定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 22:44
 */
public abstract class AbstractResult implements OssResult {

    private String cloudFrontId;

    private String extendedRequestId;

    private String requestId;

    private String statusText;

    private Integer statusCode;

    private Boolean successful;

    public String getCloudFrontId() {
        return cloudFrontId;
    }

    public void setCloudFrontId(String cloudFrontId) {
        this.cloudFrontId = cloudFrontId;
    }

    public String getExtendedRequestId() {
        return extendedRequestId;
    }

    public void setExtendedRequestId(String extendedRequestId) {
        this.extendedRequestId = extendedRequestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cloudFrontId", cloudFrontId)
                .add("extendedRequestId", extendedRequestId)
                .add("requestId", requestId)
                .add("statusText", statusText)
                .add("statusCode", statusCode)
                .add("successful", successful)
                .toString();
    }
}
