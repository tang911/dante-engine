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

import cn.herodotus.stirrup.assistant.oss.entity.business.CreateMultipartUploadBusiness;
import cn.herodotus.stirrup.assistant.oss.service.manager.PresignedMultipartUploadService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.ListPartsResponse;
import software.amazon.awssdk.services.s3.model.Part;

import java.util.List;

/**
 * <p>Description: 响应式预签名方式大文件分片上传操作 Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/31 17:43
 */
@Service
public class ReactivePresignedMultipartUploadService {

    private final PresignedMultipartUploadService multipartUploadSolutionService;

    public ReactivePresignedMultipartUploadService(PresignedMultipartUploadService multipartUploadSolutionService) {
        this.multipartUploadSolutionService = multipartUploadSolutionService;
    }

    private Mono<String> createUploadId(String bucketName, String objectName) {
        return Mono.fromFuture(multipartUploadSolutionService.createUploadId(bucketName, objectName))
                .map(CreateMultipartUploadResponse::uploadId);
    }

    private Mono<List<Part>> listParts(String bucketName, String objectName, String uploadId) {
        return Mono.fromFuture(multipartUploadSolutionService.listParts(bucketName, objectName, uploadId))
                .map(ListPartsResponse::parts);
    }

    /**
     * 创建大文件分片上传
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param totalParts 分片总数
     * @return {@link CreateMultipartUploadBusiness}
     */
    public Mono<CreateMultipartUploadBusiness> createMultipartUpload(String bucketName, String objectName, int totalParts) {
        return createUploadId(bucketName, objectName)
                .map(uploadId -> multipartUploadSolutionService.createMultipartUpload(bucketName, objectName, uploadId, totalParts));
    }
}
