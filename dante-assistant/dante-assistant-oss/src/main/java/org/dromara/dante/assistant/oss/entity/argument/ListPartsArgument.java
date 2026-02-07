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

import cn.herodotus.stirrup.assistant.oss.entity.domain.SseCustomerDomain;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 分片列表请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 11:19
 */
@Schema(name = "分片列表请求参数实体", title = "分片列表请求参数实体")
public class ListPartsArgument extends AbortMultipartUploadArgument {

    private Integer maxParts;

    private Integer partNumberMarker;

    private SseCustomerDomain sse = new SseCustomerDomain();

    public Integer getMaxParts() {
        return maxParts;
    }

    public void setMaxParts(Integer maxParts) {
        this.maxParts = maxParts;
    }

    public Integer getPartNumberMarker() {
        return partNumberMarker;
    }

    public void setPartNumberMarker(Integer partNumberMarker) {
        this.partNumberMarker = partNumberMarker;
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
                .add("maxParts", maxParts)
                .add("partNumberMarker", partNumberMarker)
                .add("sse", sse)
                .toString();
    }
}
