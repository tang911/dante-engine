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
 * <p>Description: 高阶可恢复下载文件请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 17:15
 */
@Schema(name = "高阶可恢复下载文件请求参数实体", title = "高阶可恢复下载文件请求参数实体")
public class ResumableFileDownloadArgument extends DownloadFileArgument {

    private Long transferred;
    private LocalDateTime objectLastModified;
    private Long totalSize;
    private LocalDateTime fileLastModified;

    public LocalDateTime getFileLastModified() {
        return fileLastModified;
    }

    public void setFileLastModified(LocalDateTime fileLastModified) {
        this.fileLastModified = fileLastModified;
    }

    public LocalDateTime getObjectLastModified() {
        return objectLastModified;
    }

    public void setObjectLastModified(LocalDateTime objectLastModified) {
        this.objectLastModified = objectLastModified;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public Long getTransferred() {
        return transferred;
    }

    public void setTransferred(Long transferred) {
        this.transferred = transferred;
    }
}
