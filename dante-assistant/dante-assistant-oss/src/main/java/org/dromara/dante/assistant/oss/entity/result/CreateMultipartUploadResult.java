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
import cn.herodotus.stirrup.assistant.oss.entity.domain.SsekmsDomain;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * <p>Description: 创建分片上传响应结果对象实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 23:59
 */
@Schema(name = "创建分片上传响应结果对象实体", title = "创建分片上传响应结果对象实体")
public class CreateMultipartUploadResult extends AbstractUploadResult {

    private LocalDateTime abortDate;

    private String abortRuleId;

    private String uploadId;

    private String serverSideEncryption;

    private SsekmsDomain ssekms = new SsekmsDomain();

    private String checksumAlgorithm;

    public LocalDateTime getAbortDate() {
        return abortDate;
    }

    public void setAbortDate(LocalDateTime abortDate) {
        this.abortDate = abortDate;
    }

    public String getAbortRuleId() {
        return abortRuleId;
    }

    public void setAbortRuleId(String abortRuleId) {
        this.abortRuleId = abortRuleId;
    }

    public String getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    public void setChecksumAlgorithm(String checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    public String getServerSideEncryption() {
        return serverSideEncryption;
    }

    public void setServerSideEncryption(String serverSideEncryption) {
        this.serverSideEncryption = serverSideEncryption;
    }

    public SsekmsDomain getSsekms() {
        return ssekms;
    }

    public void setSsekms(SsekmsDomain ssekms) {
        this.ssekms = ssekms;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("abortDate", abortDate)
                .add("abortRuleId", abortRuleId)
                .add("uploadId", uploadId)
                .add("serverSideEncryption", serverSideEncryption)
                .add("ssekms", ssekms)
                .add("checksumAlgorithm", checksumAlgorithm)
                .toString();
    }
}
