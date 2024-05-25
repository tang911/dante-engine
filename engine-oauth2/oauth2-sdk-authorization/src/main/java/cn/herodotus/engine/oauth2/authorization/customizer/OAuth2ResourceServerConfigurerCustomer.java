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

package cn.herodotus.engine.oauth2.authorization.customizer;

import cn.herodotus.engine.assistant.core.support.BearerTokenResolver;
import cn.herodotus.engine.assistant.core.enums.Target;
import cn.herodotus.engine.oauth2.authorization.converter.HerodotusJwtAuthenticationConverter;
import cn.herodotus.engine.oauth2.authorization.introspector.HerodotusOpaqueTokenIntrospector;
import cn.herodotus.engine.oauth2.authorization.properties.OAuth2AuthorizationProperties;
import cn.herodotus.engine.oauth2.core.response.HerodotusAccessDeniedHandler;
import cn.herodotus.engine.oauth2.core.response.HerodotusAuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

/**
 * <p>Description: OAuth2ResourceServerConfigurer 扩展配置</p>
 *
 * @author : gengwei.zheng
 * @date : 2023/8/31 23:27
 */
public class OAuth2ResourceServerConfigurerCustomer implements Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> {

    private final JwtDecoder jwtDecoder;
    private final OAuth2AuthorizationProperties authorizationProperties;
    private final OpaqueTokenIntrospector opaqueTokenIntrospector;

    public OAuth2ResourceServerConfigurerCustomer(OAuth2AuthorizationProperties authorizationProperties, JwtDecoder jwtDecoder, OAuth2ResourceServerProperties resourceServerProperties) {
        this.jwtDecoder = jwtDecoder;
        this.authorizationProperties = authorizationProperties;
        this.opaqueTokenIntrospector = new HerodotusOpaqueTokenIntrospector(resourceServerProperties);
        ;
    }

    private boolean isRemoteValidate() {
        return this.authorizationProperties.getValidate() == Target.REMOTE;
    }

    @Override
    public void customize(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
        if (isRemoteValidate()) {
            configurer
                    .opaqueToken(opaque -> opaque.introspector(opaqueTokenIntrospector));
        } else {
            configurer
                    .jwt(jwt -> jwt.decoder(this.jwtDecoder).jwtAuthenticationConverter(new HerodotusJwtAuthenticationConverter()))
                    .bearerTokenResolver(new DefaultBearerTokenResolver());
        }

        configurer
                .accessDeniedHandler(new HerodotusAccessDeniedHandler())
                .authenticationEntryPoint(new HerodotusAuthenticationEntryPoint());
    }

    public BearerTokenResolver createBearerTokenResolver() {
        return new HerodotusBearerTokenResolver(this.jwtDecoder, this.opaqueTokenIntrospector, this.isRemoteValidate());
    }
}
