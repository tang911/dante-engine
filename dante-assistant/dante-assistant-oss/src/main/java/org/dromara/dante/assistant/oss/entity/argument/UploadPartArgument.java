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
import cn.herodotus.stirrup.assistant.oss.entity.domain.SseCustomerDomain;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 上传分片请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 11:21
 */
@Schema(name = "上传分片请求参数实体", title = "上传分片请求参数实体")
public class UploadPartArgument extends AbortMultipartUploadArgument {

    private Long contentLength;

    private String contentMD5;

    private String checksumAlgorithm;

    private ChecksumDomain checksum = new ChecksumDomain();

    private Integer partNumber;

    private SseCustomerDomain sse = new SseCustomerDomain();

    private String sdkPartType;

    public ChecksumDomain getChecksum() {
        return checksum;
    }

    public void setChecksum(ChecksumDomain checksum) {
        this.checksum = checksum;
    }

    public String getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    public void setChecksumAlgorithm(String checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentMD5() {
        return contentMD5;
    }

    public void setContentMD5(String contentMD5) {
        this.contentMD5 = contentMD5;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public String getSdkPartType() {
        return sdkPartType;
    }

    public void setSdkPartType(String sdkPartType) {
        this.sdkPartType = sdkPartType;
    }

    public SseCustomerDomain getSse() {
        return sse;
    }

    public void setSse(SseCustomerDomain sse) {
        this.sse = sse;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("checksum", checksum)
                .add("contentLength", contentLength)
                .add("contentMD5", contentMD5)
                .add("checksumAlgorithm", checksumAlgorithm)
                .add("partNumber", partNumber)
                .add("sse", sse)
                .add("sdkPartType", sdkPartType)
                .toString();
    }
}
