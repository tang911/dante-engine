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
import cn.herodotus.stirrup.assistant.oss.service.base.S3AsyncClientMultipartUploadService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncRequestBody;

/**
 * <p>Description: 响应式对象存储分片上传操作 Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 16:20
 */
@Service
public class ReactiveMultipartUploadService extends AbstractReactiveService {

    private final S3AsyncClientMultipartUploadService s3AsyncClientMultipartUploadService;

    public ReactiveMultipartUploadService(S3AsyncClientMultipartUploadService s3AsyncClientMultipartUploadService) {
        this.s3AsyncClientMultipartUploadService = s3AsyncClientMultipartUploadService;
    }

    public Mono<AbortMultipartUploadResult> abortMultipartUpload(AbortMultipartUploadArgument argument) {
        return process(argument, new ArgumentToAbortMultipartUploadRequestConverter(), new ResponseToAbortMultipartUploadResultConverter(), s3AsyncClientMultipartUploadService::abortMultipartUpload);
    }

    public Mono<CompleteMultipartUploadResult> completeMultipartUpload(CompleteMultipartUploadArgument argument) {
        return process(argument, new ArgumentToCompleteMultipartUploadRequestConverter(), new ResponseToCompleteMultipartUploadResultConverter(), s3AsyncClientMultipartUploadService::completeMultipartUpload);
    }

    public Mono<CreateMultipartUploadResult> createMultipartUpload(CreateMultipartUploadArgument argument) {
        return process(argument, new ArgumentToCreateMultipartUploadRequestConverter(), new ResponseToCreateMultipartUploadResultConverter(), s3AsyncClientMultipartUploadService::createMultipartUpload);
    }

    public Mono<ListPartsResult> listParts(ListPartsArgument argument) {
        return process(argument, new ArgumentToListPartsRequestConverter(), new ResponseToListPartsResultConverter(), s3AsyncClientMultipartUploadService::listParts);
    }

    public Mono<UploadPartResult> uploadPart(UploadPartArgument argument, AsyncRequestBody requestBody) {
        return process(argument, new ArgumentToUploadPartRequestConverter(), new ResponseToUploadPartResultConverter(), request -> s3AsyncClientMultipartUploadService.uploadPart(request, requestBody));
    }


}
