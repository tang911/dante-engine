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

package cn.herodotus.stirrup.assistant.oss.converter.argument;

import cn.herodotus.stirrup.assistant.oss.entity.argument.PutBucketPolicyArgument;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PolicyDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.StatementDomain;
import cn.herodotus.stirrup.assistant.oss.enums.BucketPolicy;
import cn.herodotus.stirrup.core.jackson.JacksonUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.PutBucketPolicyRequest;

import java.util.List;

/**
 * <p>Description: {@link PutBucketPolicyArgument} 转 {@link PutBucketPolicyRequest} 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/5 17:42
 */
public class ArgumentToPutBucketPolicyRequestConverter implements Converter<PutBucketPolicyArgument, PutBucketPolicyRequest> {

    private static final String DEFAULT_RESOURCE_PREFIX = "arn:aws:s3:::";
    private static final List<String> DEFAULT_ACTION_FOR_BUCKET = Lists.newArrayList("s3:GetBucketLocation", "s3:ListBucket", "s3:ListBucketMultipartUploads");
    private static final List<String> DEFAULT_ACTION_FOR_OBJECT = Lists.newArrayList("s3:DeleteObject", "s3:GetObject", "s3:ListMultipartUploadParts", "s3:PutObject", "s3:AbortMultipartUpload");

    @Override
    public PutBucketPolicyRequest convert(PutBucketPolicyArgument source) {
        PutBucketPolicyRequest.Builder builder = PutBucketPolicyRequest.builder();

        builder.bucket(source.getBucketName());
        builder.expectedBucketOwner(source.getExpectedBucketOwner());

        if (StringUtils.isNotBlank(source.getChecksumAlgorithm())) {
            builder.checksumAlgorithm(source.getChecksumAlgorithm());
        }

        if (ObjectUtils.isNotEmpty(source.getConfirmRemoveSelfBucketAccess())) {
            builder.confirmRemoveSelfBucketAccess(source.getConfirmRemoveSelfBucketAccess());
        }

        builder.policy(getPolicy(source.getBucketName(), source.getBucketPolicy()));

        return builder.build();
    }

    private String getPolicy(String bucketName, BucketPolicy bucketPolicy) {
        PolicyDomain policyDomain = bucketPolicy == BucketPolicy.PRIVATE ? getPrivatePolicy(bucketName) : getPublicPolicy();
        return JacksonUtils.toJson(policyDomain);
    }

    private PolicyDomain getPublicPolicy() {
        return new PolicyDomain();
    }

    private PolicyDomain getPrivatePolicy(String bucketName) {
        StatementDomain bucketStatement = new StatementDomain();
        bucketStatement.setActions(DEFAULT_ACTION_FOR_BUCKET);
        bucketStatement.setResources(getDefaultResource(bucketName, true));

        StatementDomain objectStatement = new StatementDomain();
        objectStatement.setActions(DEFAULT_ACTION_FOR_OBJECT);
        objectStatement.setResources(getDefaultResource(bucketName, false));

        PolicyDomain policy = new PolicyDomain();
        policy.setStatements(Lists.newArrayList(bucketStatement, objectStatement));
        return policy;
    }

    private List<String> getDefaultResource(String bucketName, boolean isForBucket) {
        String suffix = isForBucket ? "" : "/*";
        return Lists.newArrayList(DEFAULT_RESOURCE_PREFIX + bucketName + suffix);
    }
}
