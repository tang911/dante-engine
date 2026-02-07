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

package cn.herodotus.stirrup.assistant.oss.service.reactive;

import cn.herodotus.stirrup.assistant.oss.converter.argument.*;
import cn.herodotus.stirrup.assistant.oss.converter.result.*;
import cn.herodotus.stirrup.assistant.oss.definition.service.AbstractReactiveService;
import cn.herodotus.stirrup.assistant.oss.entity.argument.*;
import cn.herodotus.stirrup.assistant.oss.entity.result.*;
import cn.herodotus.stirrup.assistant.oss.service.base.S3AsyncClientBucketService;
import cn.herodotus.stirrup.assistant.oss.service.base.S3AsyncClientCompositeService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * <p>Description: 响应式对象存储 Bucket 操作 Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 0:43
 */
@Service
public class ReactiveBucketService extends AbstractReactiveService {

    private final S3AsyncClientBucketService bucketService;
    private final S3AsyncClientCompositeService compositeService;

    public ReactiveBucketService(S3AsyncClientBucketService bucketService, S3AsyncClientCompositeService compositeService) {
        this.bucketService = bucketService;
        this.compositeService = compositeService;
    }

    public Mono<CreateBucketResult> createBucket(CreateBucketArgument argument) {
        return process(argument, new ArgumentToCreateBucketRequestConverter(), new ResponseToCreateBucketResultConverter(), bucketService::createBucket);
    }

    public Mono<DeleteBucketResult> deleteBucket(DeleteBucketArgument argument) {
        return process(argument, new ArgumentToDeleteBucketRequestConverter(), new ResponseToDeleteBucketResultConverter(), bucketService::deleteBucket);
    }

    public Mono<HeadBucketResult> headBucket(HeadBucketArgument argument) {
        return process(argument, new ArgumentToHeadBucketRequestConverter(), new ResponseToHeadBucketResultConverter(), bucketService::headBucket);
    }

    public Mono<ListBucketsResult> listBuckets(ListBucketsArgument argument) {
        return process(argument, new ArgumentToListBucketsRequestConverter(), new ResponseToListBucketsResultConverter(), bucketService::listBuckets);
    }

    public Mono<ListBucketDetailsResult> listBucketDetails(ListBucketsArgument argument) {
        return process(argument, new ArgumentToListBucketsRequestConverter(), compositeService::listBucketDetails);
    }

    public Mono<PutBucketPolicyResult> putBucketPolicy(PutBucketPolicyArgument argument) {
        return process(argument, new ArgumentToPutBucketPolicyRequestConverter(), new ResponseToPutBucketPolicyResultConverter(), bucketService::putBucketPolicy);
    }
}
