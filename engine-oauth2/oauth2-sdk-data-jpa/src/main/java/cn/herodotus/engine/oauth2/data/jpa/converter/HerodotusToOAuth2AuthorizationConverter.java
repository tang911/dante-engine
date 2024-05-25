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

package cn.herodotus.engine.oauth2.data.jpa.converter;

import cn.herodotus.engine.oauth2.core.utils.OAuth2AuthorizationUtils;
import cn.herodotus.engine.oauth2.data.jpa.definition.converter.AbstractOAuth2EntityConverter;
import cn.herodotus.engine.oauth2.data.jpa.entity.HerodotusAuthorization;
import cn.herodotus.engine.oauth2.data.jpa.jackson2.OAuth2JacksonProcessor;
import org.dromara.hutool.core.date.DateUtil;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2DeviceCode;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2UserCode;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.StringUtils;

/**
 * <p>Description: HerodotusAuthorization 转 OAuth2Authorization 转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/21 20:53
 */
public class HerodotusToOAuth2AuthorizationConverter extends AbstractOAuth2EntityConverter<HerodotusAuthorization, OAuth2Authorization> {

    private final RegisteredClientRepository registeredClientRepository;

    public HerodotusToOAuth2AuthorizationConverter(OAuth2JacksonProcessor jacksonProcessor, RegisteredClientRepository registeredClientRepository) {
        super(jacksonProcessor);
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public OAuth2Authorization convert(HerodotusAuthorization entity) {
        RegisteredClient registeredClient = this.registeredClientRepository.findById(entity.getRegisteredClientId());
        if (registeredClient == null) {
            throw new DataRetrievalFailureException(
                    "The RegisteredClient with id '" + entity.getRegisteredClientId() + "' was not found in the RegisteredClientRepository.");
        }

        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .id(entity.getId())
                .principalName(entity.getPrincipalName())
                .authorizationGrantType(OAuth2AuthorizationUtils.resolveAuthorizationGrantType(entity.getAuthorizationGrantType()))
                .authorizedScopes(StringUtils.commaDelimitedListToSet(entity.getAuthorizedScopes()))
                .attributes(attributes -> attributes.putAll(parseMap(entity.getAttributes())));
        if (entity.getState() != null) {
            builder.attribute(OAuth2ParameterNames.STATE, entity.getState());
        }

        if (entity.getAuthorizationCodeValue() != null) {
            OAuth2AuthorizationCode authorizationCode = new OAuth2AuthorizationCode(
                    entity.getAuthorizationCodeValue(),
                    DateUtil.toInstant(entity.getAuthorizationCodeIssuedAt()),
                    DateUtil.toInstant(entity.getAuthorizationCodeExpiresAt()));
            builder.token(authorizationCode, metadata -> metadata.putAll(parseMap(entity.getAuthorizationCodeMetadata())));
        }

        if (entity.getAccessTokenValue() != null) {
            OAuth2AccessToken accessToken = new OAuth2AccessToken(
                    OAuth2AccessToken.TokenType.BEARER,
                    entity.getAccessTokenValue(),
                    DateUtil.toInstant(entity.getAccessTokenIssuedAt()),
                    DateUtil.toInstant(entity.getAccessTokenExpiresAt()),
                    StringUtils.commaDelimitedListToSet(entity.getAccessTokenScopes()));
            builder.token(accessToken, metadata -> metadata.putAll(parseMap(entity.getAccessTokenMetadata())));
        }

        if (entity.getRefreshTokenValue() != null) {
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                    entity.getRefreshTokenValue(),
                    DateUtil.toInstant(entity.getRefreshTokenIssuedAt()),
                    DateUtil.toInstant(entity.getRefreshTokenExpiresAt()));
            builder.token(refreshToken, metadata -> metadata.putAll(parseMap(entity.getRefreshTokenMetadata())));
        }

        if (entity.getOidcIdTokenValue() != null) {
            OidcIdToken idToken = new OidcIdToken(
                    entity.getOidcIdTokenValue(),
                    DateUtil.toInstant(entity.getOidcIdTokenIssuedAt()),
                    DateUtil.toInstant(entity.getOidcIdTokenExpiresAt()),
                    parseMap(entity.getOidcIdTokenClaims()));
            builder.token(idToken, metadata -> metadata.putAll(parseMap(entity.getOidcIdTokenMetadata())));
        }

        if (entity.getUserCodeValue() != null) {
            OAuth2UserCode userCode = new OAuth2UserCode(
                    entity.getUserCodeValue(),
                    DateUtil.toInstant(entity.getUserCodeIssuedAt()),
                    DateUtil.toInstant(entity.getUserCodeExpiresAt()));
            builder.token(userCode, metadata -> metadata.putAll(parseMap(entity.getUserCodeMetadata())));
        }

        if (entity.getDeviceCodeValue() != null) {
            OAuth2DeviceCode deviceCode = new OAuth2DeviceCode(
                    entity.getDeviceCodeValue(),
                    DateUtil.toInstant(entity.getDeviceCodeIssuedAt()),
                    DateUtil.toInstant(entity.getDeviceCodeExpiresAt()));
            builder.token(deviceCode, metadata -> metadata.putAll(parseMap(entity.getDeviceCodeMetadata())));
        }

        return builder.build();
    }
}
