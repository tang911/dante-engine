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

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * <p>Description: 高阶可恢复上传文件请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 17:19
 */
@Schema(name = "高阶可恢复上传文件请求参数实体", title = "高阶可恢复上传文件请求参数实体")
public class ResumableFileUploadArgument extends UploadFileArgument {

    private LocalDateTime lastModified;
    private String uploadId;
    private Long partSize;
    private Long totalParts;
    private Long fileLength;
    private Long transferredParts;

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }

    public Long getPartSize() {
        return partSize;
    }

    public void setPartSize(Long partSize) {
        this.partSize = partSize;
    }

    public Long getTotalParts() {
        return totalParts;
    }

    public void setTotalParts(Long totalParts) {
        this.totalParts = totalParts;
    }

    public Long getTransferredParts() {
        return transferredParts;
    }

    public void setTransferredParts(Long transferredParts) {
        this.transferredParts = transferredParts;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
