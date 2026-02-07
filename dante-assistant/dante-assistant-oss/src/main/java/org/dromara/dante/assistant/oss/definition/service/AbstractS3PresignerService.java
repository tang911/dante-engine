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

package cn.herodotus.stirrup.assistant.oss.definition.service;

import cn.herodotus.stirrup.assistant.oss.pool.S3PresignerObjectPool;
import cn.herodotus.stirrup.core.support.pool.AbstractPooledObjectService;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.util.function.Function;

/**
 * <p>Description: AsyncClient 基础操作定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 17:26
 */
public abstract class AbstractS3PresignerService extends AbstractPooledObjectService<S3Presigner> {

    public AbstractS3PresignerService(S3PresignerObjectPool objectPool) {
        super(objectPool);
    }

    protected <T> T process(Function<S3Presigner, T> operate) {
        S3Presigner s3Presigner = getClient();
        T result = operate.apply(s3Presigner);
        s3Presigner.close();
        return result;
    }
}
