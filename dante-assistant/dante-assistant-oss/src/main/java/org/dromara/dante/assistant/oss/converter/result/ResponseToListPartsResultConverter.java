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

package cn.herodotus.stirrup.assistant.oss.converter.result;

import cn.herodotus.stirrup.assistant.oss.converter.domain.InitiatorToDomainConverter;
import cn.herodotus.stirrup.assistant.oss.converter.domain.OwnerToDomainConverter;
import cn.herodotus.stirrup.assistant.oss.converter.domain.PartToDomainConverter;
import cn.herodotus.stirrup.assistant.oss.definition.converter.ResponseConverter;
import cn.herodotus.stirrup.assistant.oss.entity.domain.OwnerDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PartDomain;
import cn.herodotus.stirrup.assistant.oss.entity.result.ListPartsResult;
import cn.herodotus.stirrup.spring.founction.ListConverter;
import cn.hutool.v7.core.date.DateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.Initiator;
import software.amazon.awssdk.services.s3.model.ListPartsResponse;
import software.amazon.awssdk.services.s3.model.Owner;
import software.amazon.awssdk.services.s3.model.Part;

/**
 * <p>Description: Response 转 Result 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 0:17
 */
public class ResponseToListPartsResultConverter implements ResponseConverter<ListPartsResponse, ListPartsResult> {

    private final Converter<Initiator, OwnerDomain> toInitiator;
    private final Converter<Owner, OwnerDomain> toOwner;
    private final ListConverter<Part, PartDomain> toParts;

    public ResponseToListPartsResultConverter() {
        this.toInitiator = new InitiatorToDomainConverter();
        this.toOwner = new OwnerToDomainConverter();
        this.toParts = new PartToDomainConverter();
    }


    @Override
    public ListPartsResult getInstance(ListPartsResponse source) {
        return new ListPartsResult();
    }

    @Override
    public void prepare(ListPartsResponse source, ListPartsResult target) {

        target.setAbortDate(DateUtil.toLocalDateTime(source.abortDate()));
        target.setAbortRuleId(source.abortRuleId());
        target.setBucketName(source.bucket());
        target.setObjectName(source.key());
        target.setRequestCharged(source.requestChargedAsString());
        target.setUploadId(source.uploadId());
        target.setChecksumAlgorithm(source.checksumAlgorithmAsString());
        target.setPartNumberMarker(source.partNumberMarker());
        target.setNextPartNumberMarker(source.nextPartNumberMarker());
        target.setMaxParts(source.maxParts());
        target.setTruncated(source.isTruncated());
        target.setStorageClass(source.storageClassAsString());
        target.setInitiator(ObjectUtils.isNotEmpty(source.initiator()) ? toInitiator.convert(source.initiator()) : null);
        target.setOwner(ObjectUtils.isNotEmpty(source.owner()) ? toOwner.convert(source.owner()) : null);
        target.setParts(toParts.convert(source.parts()));

        ResponseConverter.super.prepare(source, target);
    }
}
