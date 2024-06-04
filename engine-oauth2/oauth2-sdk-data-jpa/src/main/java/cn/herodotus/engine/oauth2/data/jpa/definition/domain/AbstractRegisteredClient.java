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

package cn.herodotus.engine.oauth2.data.jpa.definition.domain;

import cn.herodotus.engine.assistant.definition.constants.DefaultConstants;
import cn.herodotus.engine.assistant.core.json.jackson2.deserializer.CommaDelimitedStringToSetSerializer;
import cn.herodotus.engine.assistant.core.json.jackson2.deserializer.ArrayToCommaDelimitedStringDeserializer;
import cn.herodotus.engine.data.core.entity.BaseSysEntity;
import cn.herodotus.engine.oauth2.core.definition.domain.RegisteredClientDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * <p>Description: 多实例共用 RegisteredClient属性 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/21 17:30
 */
@MappedSuperclass
public abstract class AbstractRegisteredClient extends BaseSysEntity implements RegisteredClientDetails {

    @Schema(name = "客户端ID发布日期", title = "客户端发布日期")
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT, locale = "GMT+8", shape = JsonFormat.Shape.STRING)
    @Column(name = "client_id_issued_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime clientIdIssuedAt;

    @Schema(name = "客户端秘钥过期时间", title = "客户端秘钥过期时间")
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT, locale = "GMT+8", shape = JsonFormat.Shape.STRING)
    @Column(name = "client_secret_expires_at")
    private LocalDateTime clientSecretExpiresAt;

    @Schema(name = "客户端认证模式", title = "支持多个值，以逗号分隔", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "client_authentication_methods", nullable = false, length = 1000)
    @JsonDeserialize(using = ArrayToCommaDelimitedStringDeserializer.class)
    @JsonSerialize(using = CommaDelimitedStringToSetSerializer.class)
    private String clientAuthenticationMethods;

    @Schema(name = "认证模式", title = "支持多个值，以逗号分隔", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "authorization_grant_types", nullable = false, length = 1000)
    @JsonDeserialize(using = ArrayToCommaDelimitedStringDeserializer.class)
    @JsonSerialize(using = CommaDelimitedStringToSetSerializer.class)
    private String authorizationGrantTypes;

    @Schema(name = "回调地址", title = "支持多个值，以逗号分隔")
    @Column(name = "redirect_uris", length = 1000)
    private String redirectUris;

    @Schema(name = "OIDC Logout 回调地址", title = "支持多个值，以逗号分隔")
    @Column(name = "post_logout_redirect_uris", length = 1000)
    private String postLogoutRedirectUris;

    @Override
    public LocalDateTime getClientIdIssuedAt() {
        return clientIdIssuedAt;
    }

    public void setClientIdIssuedAt(LocalDateTime clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
    }

    @Override
    public LocalDateTime getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public void setClientSecretExpiresAt(LocalDateTime clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    @Override
    public String getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(String clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    @Override
    public String getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(String authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    @Override
    public String getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(String redirectUris) {
        this.redirectUris = redirectUris;
    }

    @Override
    public String getPostLogoutRedirectUris() {
        return postLogoutRedirectUris;
    }

    public void setPostLogoutRedirectUris(String postLogoutRedirectUris) {
        this.postLogoutRedirectUris = postLogoutRedirectUris;
    }
}
