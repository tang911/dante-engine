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

package cn.herodotus.stirrup.assistant.oss.entity.business;

import cn.herodotus.stirrup.assistant.oss.definition.domain.OssDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 创建分片上传返回结果业务对象 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/8/19 11:57
 */
@Schema(name = "创建分片上传返回结果业务对象", title = "创建分片上传返回结果业务对象")
public class CreateMultipartUploadBusiness implements OssDomain {

    @Schema(name = "上传ID")
    private String uploadId;

    @Schema(name = "分片上传URL", description = "分片上传所有分片对相应的预签名地址")
    private List<String> uploadUrls;

    public CreateMultipartUploadBusiness(String uploadId) {
        this.uploadId = uploadId;
        this.uploadUrls = new ArrayList<>();
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public List<String> getUploadUrls() {
        return uploadUrls;
    }

    public void setUploadUrls(List<String> uploadUrls) {
        this.uploadUrls = uploadUrls;
    }

    public void append(String uploadUrl) {
        uploadUrls.add(uploadUrls.size(), uploadUrl);
    }
}
