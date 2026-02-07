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

package cn.herodotus.stirrup.assistant.oss.service.servlet;

import cn.herodotus.stirrup.assistant.oss.converter.argument.*;
import cn.herodotus.stirrup.assistant.oss.converter.result.*;
import cn.herodotus.stirrup.assistant.oss.definition.service.AbstractServletService;
import cn.herodotus.stirrup.assistant.oss.entity.argument.*;
import cn.herodotus.stirrup.assistant.oss.entity.result.*;
import cn.herodotus.stirrup.assistant.oss.service.base.S3AsyncClientCompositeService;
import cn.herodotus.stirrup.assistant.oss.service.base.S3AsyncClientObjectService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;

/**
 * <p>Description: 阻塞式对象存储 Object 操作 Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 16:19
 */
@Service
public class ServletObjectService extends AbstractServletService {

    private final S3AsyncClientObjectService objectService;
    private final S3AsyncClientCompositeService compositeService;

    public ServletObjectService(S3AsyncClientObjectService objectService, S3AsyncClientCompositeService compositeService) {
        this.objectService = objectService;
        this.compositeService = compositeService;
    }

    public HeadObjectResult headObject(HeadObjectArgument argument) {
        return process(argument, new ArgumentToHeadObjectRequestConverter(), new ResponseToHeadObjectResultConverter(), objectService::headObject);
    }

    public DeleteObjectResult deleteObject(DeleteObjectArgument argument) {
        return process(argument, new ArgumentToDeleteObjectRequestConverter(), new ResponseToDeleteObjectResultConverter(), objectService::deleteObject);
    }

    public DeleteObjectsResult deleteObjects(DeleteObjectsArgument argument) {
        return process(argument, new ArgumentToDeleteObjectsRequestConverter(), new ResponseToDeleteObjectsResultConverter(), objectService::deleteObjects);
    }

    public GetObjectResult getObject(GetObjectArgument argument) {
        return process(argument, new ArgumentToGetObjectRequestConverter(), new InputStreamToGetObjectResultConverter(), getObjectRequest -> objectService.getObject(getObjectRequest, AsyncResponseTransformer.toBlockingInputStream()));
    }

    public ListObjectsV2Result listObjectsV2(ListObjectsV2Argument argument) {
        return process(argument, new ArgumentToListObjectsV2RequestConverter(), new ResponseToListObjectsV2ResultConverter(), objectService::listObjectsV2);
    }

    public PutObjectResult putObject(PutObjectArgument argument, AsyncRequestBody requestBody) {
        return process(argument, new ArgumentToPutObjectRequestConverter(), new ResponseToPutObjectResultConverter(), request -> objectService.putObject(request, requestBody));
    }

    public WriteGetObjectResponseResult writeGetObjectResponse(WriteGetObjectResponseArgument argument, AsyncRequestBody requestBody) {
        return process(argument, new ArgumentToWriteGetObjectResponseRequestConverter(), new ResponseToWriteGetObjectResponseResultConverter(), request -> objectService.writeGetObjectResponse(request, requestBody));
    }

    public GetObjectAttributesResult getObjectAttributes(GetObjectAttributesArgument argument) {
        return process(argument, new ArgumentToGetObjectAttributesRequestConverter(), compositeService::getObjectAttributes);
    }

    public PutObjectLegalHoldResult putObjectLegalHold(PutObjectLegalHoldArgument argument) {
        return process(argument, new ArgumentToPutObjectLegalHoldRequestConverter(), new ResponseToPutObjectLegalHoldResultConverter(), objectService::putObjectLegalHold);
    }

    public PutObjectRetentionResult putObjectRetention(PutObjectRetentionArgument argument) {
        return process(argument, new ArgumentToPutObjectRetentionRequestConverter(), new ResponseToPutObjectRetentionResultConverter(), objectService::putObjectRetention);
    }
}
