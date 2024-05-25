/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Dante Engine.
 *
 * Dante Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dante Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.rest.protect.configuration;

import cn.herodotus.engine.rest.condition.properties.CryptoProperties;
import cn.herodotus.engine.rest.core.definition.crypto.AsymmetricCryptoProcessor;
import cn.herodotus.engine.rest.core.definition.crypto.SymmetricCryptoProcessor;
import cn.herodotus.engine.rest.protect.crypto.enhance.DecryptRequestBodyAdvice;
import cn.herodotus.engine.rest.protect.crypto.enhance.DecryptRequestParamMapResolver;
import cn.herodotus.engine.rest.protect.crypto.enhance.DecryptRequestParamResolver;
import cn.herodotus.engine.rest.protect.crypto.enhance.EncryptResponseBodyAdvice;
import cn.herodotus.engine.rest.protect.crypto.processor.HttpCryptoProcessor;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Rest 加密配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/14 21:11
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CryptoProperties.class)
@Import({
        CryptoStrategyConfiguration.class,
})
public class HttpCryptoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HttpCryptoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Protect Http Crypto] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpCryptoProcessor httpCryptoProcessor(AsymmetricCryptoProcessor asymmetricCryptoProcessor, SymmetricCryptoProcessor symmetricCryptoProcessor) {
        HttpCryptoProcessor httpCryptoProcessor = new HttpCryptoProcessor(asymmetricCryptoProcessor, symmetricCryptoProcessor);
        log.trace("[Herodotus] |- Bean [Interface Crypto Processor] Auto Configure.");
        return httpCryptoProcessor;
    }

    @Bean
    @ConditionalOnClass(HttpCryptoProcessor.class)
    @ConditionalOnMissingBean
    public DecryptRequestBodyAdvice decryptRequestBodyAdvice(HttpCryptoProcessor httpCryptoProcessor) {
        DecryptRequestBodyAdvice decryptRequestBodyAdvice = new DecryptRequestBodyAdvice();
        decryptRequestBodyAdvice.setInterfaceCryptoProcessor(httpCryptoProcessor);
        log.trace("[Herodotus] |- Bean [Decrypt Request Body Advice] Auto Configure.");
        return decryptRequestBodyAdvice;
    }

    @Bean
    @ConditionalOnClass(HttpCryptoProcessor.class)
    @ConditionalOnMissingBean
    public EncryptResponseBodyAdvice encryptResponseBodyAdvice(HttpCryptoProcessor httpCryptoProcessor) {
        EncryptResponseBodyAdvice encryptResponseBodyAdvice = new EncryptResponseBodyAdvice();
        encryptResponseBodyAdvice.setInterfaceCryptoProcessor(httpCryptoProcessor);
        log.trace("[Herodotus] |- Bean [Encrypt Response Body Advice] Auto Configure.");
        return encryptResponseBodyAdvice;
    }

    @Bean
    @ConditionalOnClass(HttpCryptoProcessor.class)
    @ConditionalOnMissingBean
    public DecryptRequestParamMapResolver decryptRequestParamStringResolver(HttpCryptoProcessor httpCryptoProcessor) {
        DecryptRequestParamMapResolver decryptRequestParamMapResolver = new DecryptRequestParamMapResolver();
        decryptRequestParamMapResolver.setInterfaceCryptoProcessor(httpCryptoProcessor);
        log.trace("[Herodotus] |- Bean [Decrypt Request Param Map Resolver] Auto Configure.");
        return decryptRequestParamMapResolver;
    }

    @Bean
    @ConditionalOnClass(HttpCryptoProcessor.class)
    @ConditionalOnMissingBean
    public DecryptRequestParamResolver decryptRequestParamResolver(HttpCryptoProcessor httpCryptoProcessor) {
        DecryptRequestParamResolver decryptRequestParamResolver = new DecryptRequestParamResolver();
        decryptRequestParamResolver.setInterfaceCryptoProcessor(httpCryptoProcessor);
        log.trace("[Herodotus] |- Bean [Decrypt Request Param Resolver] Auto Configure.");
        return decryptRequestParamResolver;
    }
}
