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

import cn.herodotus.stirrup.assistant.oss.definition.argument.AbstractObjectRequestPayerArgument;
import cn.herodotus.stirrup.core.constant.SymbolConstants;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 存储桶列表V2请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 23:23
 */
@Schema(name = "存储桶列表V2请求参数实体", title = "存储桶列表V2请求参数实体")
public class ListObjectsV2Argument extends AbstractObjectRequestPayerArgument {

    private String delimiter = SymbolConstants.FORWARD_SLASH;

    private String encodingType;

    private Integer maxKeys;

    private String prefix;

    private String continuationToken;

    private Boolean fetchOwner;

    private String startAfter;

    public String getContinuationToken() {
        return continuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public Boolean getFetchOwner() {
        return fetchOwner;
    }

    public void setFetchOwner(Boolean fetchOwner) {
        this.fetchOwner = fetchOwner;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


    public String getStartAfter() {
        return startAfter;
    }

    public void setStartAfter(String startAfter) {
        this.startAfter = startAfter;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("continuationToken", continuationToken)
                .add("delimiter", delimiter)
                .add("encodingType", encodingType)
                .add("maxKeys", maxKeys)
                .add("prefix", prefix)
                .add("fetchOwner", fetchOwner)
                .add("startAfter", startAfter)
                .toString();
    }
}
