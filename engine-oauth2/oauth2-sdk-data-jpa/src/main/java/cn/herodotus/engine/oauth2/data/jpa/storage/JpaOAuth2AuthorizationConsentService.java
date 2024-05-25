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

package cn.herodotus.engine.oauth2.data.jpa.storage;

import cn.herodotus.engine.oauth2.data.jpa.converter.HerodotusToOAuth2AuthorizationConsentConverter;
import cn.herodotus.engine.oauth2.data.jpa.converter.OAuth2ToHerodotusAuthorizationConsentConverter;
import cn.herodotus.engine.oauth2.data.jpa.entity.HerodotusAuthorizationConsent;
import cn.herodotus.engine.oauth2.data.jpa.service.HerodotusAuthorizationConsentService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>Description: 基于 JPA 的 OAuth2 认证服务 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/2/25 22:15
 */
public class JpaOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {

    private final HerodotusAuthorizationConsentService herodotusAuthorizationConsentService;
    private final Converter<HerodotusAuthorizationConsent, OAuth2AuthorizationConsent> herodotusToOAuth2Converter;
    private final Converter<OAuth2AuthorizationConsent, HerodotusAuthorizationConsent> oauth2ToherodotusConverter;

    public JpaOAuth2AuthorizationConsentService(HerodotusAuthorizationConsentService herodotusAuthorizationConsentService, RegisteredClientRepository registeredClientRepository) {
        this.herodotusAuthorizationConsentService = herodotusAuthorizationConsentService;
        this.herodotusToOAuth2Converter = new HerodotusToOAuth2AuthorizationConsentConverter(registeredClientRepository);
        this.oauth2ToherodotusConverter = new OAuth2ToHerodotusAuthorizationConsentConverter();
    }

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        this.herodotusAuthorizationConsentService.save(toEntity(authorizationConsent));
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        this.herodotusAuthorizationConsentService.deleteByRegisteredClientIdAndPrincipalName(
                authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return this.herodotusAuthorizationConsentService.findByRegisteredClientIdAndPrincipalName(
                registeredClientId, principalName).map(this::toObject).orElse(null);
    }

    private OAuth2AuthorizationConsent toObject(HerodotusAuthorizationConsent authorizationConsent) {
        return herodotusToOAuth2Converter.convert(authorizationConsent);
    }

    private HerodotusAuthorizationConsent toEntity(OAuth2AuthorizationConsent authorizationConsent) {
        return oauth2ToherodotusConverter.convert(authorizationConsent);
    }
}
