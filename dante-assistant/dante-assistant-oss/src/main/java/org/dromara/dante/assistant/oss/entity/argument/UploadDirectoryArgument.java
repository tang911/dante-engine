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

import cn.herodotus.stirrup.assistant.oss.definition.argument.AbstractArgument;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 上传目录请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 17:25
 */
@Schema(name = "上传目录请求参数实体", title = "上传目录请求参数实体")
public class UploadDirectoryArgument extends AbstractArgument {

    private String source;
    private String bucketName;
    private String prefix;
    private String delimiter;
    private Boolean followSymbolicLinks;
    private Integer maxDepth;

    private UploadFileArgument argument;

    public UploadFileArgument getArgument() {
        return argument;
    }

    public void setArgument(UploadFileArgument argument) {
        this.argument = argument;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public Boolean getFollowSymbolicLinks() {
        return followSymbolicLinks;
    }

    public void setFollowSymbolicLinks(Boolean followSymbolicLinks) {
        this.followSymbolicLinks = followSymbolicLinks;
    }

    public Integer getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(Integer maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
