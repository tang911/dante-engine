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

package cn.herodotus.stirrup.assistant.oss.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import software.amazon.awssdk.services.s3.S3AsyncClient;

/**
 * <p>Description: Amazon SDK V2 Async Client 池化工厂 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/7/14 16:34
 */
public class S3AsyncClientPooledObjectFactory extends BasePooledObjectFactory<S3AsyncClient> {

    private final AwsConfigurer awsConfigurer;

    public S3AsyncClientPooledObjectFactory(AwsConfigurer awsConfigurer) {
        this.awsConfigurer = awsConfigurer;
    }

    @Override
    public S3AsyncClient create() throws Exception {
        return awsConfigurer.createS3AsyncClient();
    }

    @Override
    public PooledObject<S3AsyncClient> wrap(S3AsyncClient s3AsyncClient) {
        return new DefaultPooledObject<>(s3AsyncClient);
    }
}
