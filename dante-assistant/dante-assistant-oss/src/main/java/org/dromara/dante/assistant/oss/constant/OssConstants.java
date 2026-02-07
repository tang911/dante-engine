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

package cn.herodotus.stirrup.assistant.oss.constant;

import cn.herodotus.stirrup.core.constant.BaseConstants;

/**
 * <p>Description: Oss 模块常量 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 16:44
 */
public interface OssConstants extends BaseConstants {

    String PROPERTY_ASSISTANT_OSS = PROPERTY_PREFIX_ASSISTANT + ".oss";

    String PROPERTY_OSS_PROXY = PROPERTY_ASSISTANT_OSS + ".proxy";

    String OSS_MULTIPART_UPLOAD_REQUEST_MAPPING = "/oss/multipart-upload";
    String OSS_PRESIGNED_OBJECT_REQUEST_MAPPING = "/presigned";
    String OSS_PRESIGNED_OBJECT_PROXY_REQUEST_MAPPING = OSS_PRESIGNED_OBJECT_REQUEST_MAPPING + "/*/*";
    String PRESIGNED_OBJECT_URL_PROXY = OSS_MULTIPART_UPLOAD_REQUEST_MAPPING + OSS_PRESIGNED_OBJECT_REQUEST_MAPPING;

    String ITEM_OSS_DIALECT = PROPERTY_PREFIX_OSS + ".dialect";

    /**
     * 允许的对象大小，最大为 5T
     */
    long MAX_OBJECT_SIZE = 5L * 1024 * 1024 * 1024 * 1024;
    /**
     * 分片上传中，允许的分片大小最小为 5M。与 Minio 统一
     */
    long MIN_PART_SIZE = 5L * 1024 * 1024;
    /**
     * 分片上传中，允许的分片大小最大为 5G。与 Minio 统一
     */
    long MAX_PART_SIZE = 5L * 1024 * 1024 * 1024;
    /**
     * 分片上传中，允许的最大分片数量为 1000。
     * <p>
     * 按照分片最大 5G 计算，最大的对象允许5T，约等于 1000 个分片
     */
    int MAX_MULTIPART_COUNT = 1000;
}
