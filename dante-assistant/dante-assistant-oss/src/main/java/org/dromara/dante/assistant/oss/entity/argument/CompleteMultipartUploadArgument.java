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
import cn.herodotus.stirrup.assistant.oss.entity.domain.CompletedPartDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.SseCustomerDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * <p>Description: 完成分片上传请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 11:14
 */
@Schema(name = "完成分片上传请求参数实体", title = "完成分片上传请求参数实体")
public class CompleteMultipartUploadArgument extends AbortMultipartUploadArgument {

    private List<CompletedPartDomain> multipartUpload;

    private ChecksumDomain checksum = new ChecksumDomain();

    private SseCustomerDomain sse = new SseCustomerDomain();

    public ChecksumDomain getChecksum() {
        return checksum;
    }

    public void setChecksum(ChecksumDomain checksum) {
        this.checksum = checksum;
    }

    public List<CompletedPartDomain> getMultipartUpload() {
        return multipartUpload;
    }

    public void setMultipartUpload(List<CompletedPartDomain> multipartUpload) {
        this.multipartUpload = multipartUpload;
    }

    public SseCustomerDomain getSse() {
        return sse;
    }

    public void setSse(SseCustomerDomain sse) {
        this.sse = sse;
    }
}
