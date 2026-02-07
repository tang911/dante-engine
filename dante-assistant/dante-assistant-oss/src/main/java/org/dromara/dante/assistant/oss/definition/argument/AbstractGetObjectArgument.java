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

import cn.herodotus.stirrup.assistant.oss.entity.domain.SseCustomerDomain;
import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;

/**
 * <p>Description: 获取对象通用参数 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 23:47
 */
public abstract class AbstractGetObjectArgument extends AbstractObjectVersionArgument {

    private String ifMatch;

    private LocalDateTime ifModifiedSince;

    private String ifNoneMatch;

    private LocalDateTime ifUnmodifiedSince;

    private String range;

    private String responseCacheControl;

    private String responseContentDisposition;

    private String responseContentEncoding;

    private String responseContentLanguage;

    private String responseContentType;

    private LocalDateTime responseExpires;

    private SseCustomerDomain sseCustomer = new SseCustomerDomain();

    private Integer partNumber;

    private String checksumMode;

    public String getChecksumMode() {
        return checksumMode;
    }

    public void setChecksumMode(String checksumMode) {
        this.checksumMode = checksumMode;
    }

    public String getIfMatch() {
        return ifMatch;
    }

    public void setIfMatch(String ifMatch) {
        this.ifMatch = ifMatch;
    }

    public LocalDateTime getIfModifiedSince() {
        return ifModifiedSince;
    }

    public void setIfModifiedSince(LocalDateTime ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
    }

    public String getIfNoneMatch() {
        return ifNoneMatch;
    }

    public void setIfNoneMatch(String ifNoneMatch) {
        this.ifNoneMatch = ifNoneMatch;
    }

    public LocalDateTime getIfUnmodifiedSince() {
        return ifUnmodifiedSince;
    }

    public void setIfUnmodifiedSince(LocalDateTime ifUnmodifiedSince) {
        this.ifUnmodifiedSince = ifUnmodifiedSince;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getResponseCacheControl() {
        return responseCacheControl;
    }

    public void setResponseCacheControl(String responseCacheControl) {
        this.responseCacheControl = responseCacheControl;
    }

    public String getResponseContentDisposition() {
        return responseContentDisposition;
    }

    public void setResponseContentDisposition(String responseContentDisposition) {
        this.responseContentDisposition = responseContentDisposition;
    }

    public String getResponseContentEncoding() {
        return responseContentEncoding;
    }

    public void setResponseContentEncoding(String responseContentEncoding) {
        this.responseContentEncoding = responseContentEncoding;
    }

    public String getResponseContentLanguage() {
        return responseContentLanguage;
    }

    public void setResponseContentLanguage(String responseContentLanguage) {
        this.responseContentLanguage = responseContentLanguage;
    }

    public String getResponseContentType() {
        return responseContentType;
    }

    public void setResponseContentType(String responseContentType) {
        this.responseContentType = responseContentType;
    }

    public LocalDateTime getResponseExpires() {
        return responseExpires;
    }

    public void setResponseExpires(LocalDateTime responseExpires) {
        this.responseExpires = responseExpires;
    }

    public SseCustomerDomain getSseCustomer() {
        return sseCustomer;
    }

    public void setSseCustomer(SseCustomerDomain sseCustomer) {
        this.sseCustomer = sseCustomer;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("checksumMode", checksumMode)
                .add("ifMatch", ifMatch)
                .add("ifModifiedSince", ifModifiedSince)
                .add("ifNoneMatch", ifNoneMatch)
                .add("ifUnmodifiedSince", ifUnmodifiedSince)
                .add("range", range)
                .add("responseCacheControl", responseCacheControl)
                .add("responseContentDisposition", responseContentDisposition)
                .add("responseContentEncoding", responseContentEncoding)
                .add("responseContentLanguage", responseContentLanguage)
                .add("responseContentType", responseContentType)
                .add("responseExpires", responseExpires)
                .add("sseCustomer", sseCustomer)
                .add("partNumber", partNumber)
                .toString();
    }
}
