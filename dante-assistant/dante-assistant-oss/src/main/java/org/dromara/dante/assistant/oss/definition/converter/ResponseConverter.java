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

package cn.herodotus.stirrup.assistant.oss.definition.converter;

import cn.herodotus.stirrup.assistant.oss.definition.result.AbstractResult;
import software.amazon.awssdk.services.s3.model.S3Response;

import java.util.Optional;

/**
 * <p>Description: Response 基础转换器 </p>
 * <p>
 * AWS 响应通用参数转换器
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 0:24
 */
public interface ResponseConverter<S extends S3Response, T extends AbstractResult> extends OssConverter<S, T> {

    @Override
    default void prepare(S source, T target) {
        Optional.ofNullable(source.sdkHttpResponse())
                .ifPresent(response -> {
                    target.setStatusCode(response.statusCode());
                    target.setSuccessful(response.isSuccessful());
                    response.statusText().ifPresent(target::setStatusText);
                });

        Optional.ofNullable(source.responseMetadata())
                .ifPresent(metadata -> {
                    target.setCloudFrontId(metadata.cloudFrontId());
                    target.setRequestId(metadata.requestId());
                    target.setExtendedRequestId(metadata.extendedRequestId());
                });
    }
}
