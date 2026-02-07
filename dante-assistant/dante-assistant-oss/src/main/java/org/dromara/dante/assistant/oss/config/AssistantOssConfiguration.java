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

package org.dromara.dante.assistant.oss.config;

import jakarta.annotation.PostConstruct;
import org.dromara.dante.assistant.oss.pool.AwsConfigurer;
import org.dromara.dante.assistant.oss.pool.S3AsyncClientObjectPool;
import org.dromara.dante.assistant.oss.pool.S3PresignerObjectPool;
import org.dromara.dante.assistant.oss.properties.OssProperties;
import org.dromara.dante.assistant.oss.service.base.S3AsyncClientObjectService;
import org.dromara.dante.assistant.oss.service.base.S3TransferManagerService;
import org.dromara.dante.assistant.oss.support.LocalOssTransformer;
import org.dromara.dante.core.support.file.OssTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: OSS 业务模块配置 </p>
 * <p>
 * 在 Herodotus Cloud Reactive 版本中，将不再使用原有 Dialect 模式适配所有 OSS，工作量大实现繁琐，而改用统一使用 AWS S3 V2 版本进行实现。
 * AWS S3 V2 版本除了 API 变化，对于OSS厂商所谓兼容 S3 协议的部分操作没有变化。比如当前代码，实现 Minio 的基本操作是完全可以的。
 * <p>
 * 理想情况下，未来各个厂商的新版本会逐步适配或像 AWS S3 V2。即使不改动，现在代码也会兼容。
 * <p>
 * 自2024年7月31日起， AWS SDK for Java 1.x已进入维护模式，并将于2025年12月31日end-of-support上线。我们建议您迁移到AWS SDK for Java 2.x以继续接收新功能、可用性改进和安全更新。
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 9:55
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OssProperties.class)
public class AssistantOssConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AssistantOssConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- Module [Assistant Oss] Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public AwsConfigurer awsConfigurer(OssProperties ossProperties) {
        AwsConfigurer configurer = new AwsConfigurer(ossProperties);
        log.trace("[Herodotus] |- Bean [Aws Configurer] Configure.");
        return configurer;
    }

    @Bean
    @ConditionalOnMissingBean
    public S3AsyncClientObjectPool s3AsyncClientObjectPool(AwsConfigurer awsConfigurer) {
        S3AsyncClientObjectPool pool = new S3AsyncClientObjectPool(awsConfigurer);
        log.trace("[Herodotus] |- Bean [Aws S3 Async Client Pool] Configure.");
        return pool;
    }

    @Bean
    @ConditionalOnMissingBean
    public S3PresignerObjectPool s3PresignerObjectPool(AwsConfigurer awsConfigurer) {
        S3PresignerObjectPool pool = new S3PresignerObjectPool(awsConfigurer);
        log.trace("[Herodotus] |- Bean [Aws S3 Presigner Pool] Configure.");
        return pool;
    }

    @Configuration(proxyBeanMethods = false)
    @ComponentScan(basePackages = {
            "org.dromara.dante.assistant.oss.service.base",
            "org.dromara.dante.assistant.oss.service.manager"
    })
    static class AwsServiceConfiguration {

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    @ComponentScan(basePackages = {
            "org.dromara.dante.assistant.oss.service.reactive"
    })
    static class ReactiveOssConfiguration {

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ComponentScan(basePackages = {
            "org.dromara.dante.assistant.oss.service.servlet"
    })
    static class ServletOssConfiguration {

    }

    @Configuration(proxyBeanMethods = false)
    static class FileProcessorConfiguration {

        @Bean
        public OssTransformer ossTransformer(S3AsyncClientObjectService s3AsyncClientObjectService, S3TransferManagerService s3TransferManagerService) {
            LocalOssTransformer transformer = new LocalOssTransformer(s3AsyncClientObjectService, s3TransferManagerService);
            log.debug("[Herodotus] |- Strategy [Local File Transformer] Configure.");
            return transformer;
        }
    }
}
