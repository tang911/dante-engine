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

package cn.herodotus.engine.oauth2.resource.autoconfigure;

import cn.herodotus.engine.message.core.logic.strategy.RequestMappingScanEventManager;
import cn.herodotus.engine.oauth2.authorization.configuration.OAuth2AuthorizationConfiguration;
import cn.herodotus.engine.oauth2.authorization.processor.SecurityMetadataSourceAnalyzer;
import cn.herodotus.engine.oauth2.core.exception.SecurityGlobalExceptionHandler;
import cn.herodotus.engine.oauth2.resource.autoconfigure.metadata.RemoteSecurityMetadataSyncListener;
import cn.herodotus.engine.oauth2.resource.autoconfigure.scan.DefaultRequestMappingScanEventManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>Description: OAuth2 资源服务器自动配置模块 </p>
 *
 * 所有提供资源访问的服务（即可以作为 OAuth2 资源服务器的服务），所需的基础配置都在该模块中统一完成配置
 *
 * @author : gengwei.zheng
 * @date : 2023/10/28 14:22
 */
@AutoConfiguration
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Import({
        OAuth2AuthorizationConfiguration.class
})
@ComponentScan(basePackageClasses = SecurityGlobalExceptionHandler.class)
@RemoteApplicationEventScan({
        "cn.herodotus.engine.oauth2.resource.autoconfigure.bus"
})
public class OAuth2ResourceServerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ResourceServerAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [OAuth2 Resource Server Starter] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public RemoteSecurityMetadataSyncListener remoteSecurityMetadataSyncListener(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer, ServiceMatcher serviceMatcher) {
        RemoteSecurityMetadataSyncListener listener = new RemoteSecurityMetadataSyncListener(securityMetadataSourceAnalyzer, serviceMatcher);
        log.trace("[Herodotus] |- Bean [Security Metadata Refresh Listener] Auto Configure.");
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestMappingScanEventManager requestMappingScanEventManager(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer) {
        DefaultRequestMappingScanEventManager manager = new DefaultRequestMappingScanEventManager(securityMetadataSourceAnalyzer);
        log.trace("[Herodotus] |- Bean [Request Mapping Scan Manager] Auto Configure.");
        return manager;
    }
}
