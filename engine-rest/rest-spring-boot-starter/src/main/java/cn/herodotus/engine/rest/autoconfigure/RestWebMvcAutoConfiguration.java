/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Engine.
 *
 * Herodotus Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.rest.autoconfigure;

import cn.herodotus.engine.rest.protect.configuration.SecureConfiguration;
import cn.herodotus.engine.rest.protect.configuration.TenantConfiguration;
import cn.herodotus.engine.rest.protect.secure.interceptor.AccessLimitedInterceptor;
import cn.herodotus.engine.rest.protect.secure.interceptor.IdempotentInterceptor;
import cn.herodotus.engine.rest.protect.tenant.MultiTenantInterceptor;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

/**
 * <p>Description: WebMvcAutoConfiguration </p>
 *
 * @author : gengwei.zheng
 * @date : 2020/3/4 11:00
 */
@AutoConfiguration
@Import({
        SecureConfiguration.class,
        TenantConfiguration.class
})
@EnableWebMvc
public class RestWebMvcAutoConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(RestWebMvcAutoConfiguration.class);

    private final IdempotentInterceptor idempotentInterceptor;
    private final AccessLimitedInterceptor accessLimitedInterceptor;
    private final MultiTenantInterceptor multiTenantInterceptor;

    public RestWebMvcAutoConfiguration(IdempotentInterceptor idempotentInterceptor, AccessLimitedInterceptor accessLimitedInterceptor, MultiTenantInterceptor multiTenantInterceptor) {
        this.idempotentInterceptor = idempotentInterceptor;
        this.accessLimitedInterceptor = accessLimitedInterceptor;
        this.multiTenantInterceptor = multiTenantInterceptor;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Rest WebMvc] Auto Configure.");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitedInterceptor);
        registry.addInterceptor(idempotentInterceptor);
        registry.addInterceptor(multiTenantInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(false)
                .addResolver(new WebJarsResourceResolver());
    }

}
