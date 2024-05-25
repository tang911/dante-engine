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

package cn.herodotus.engine.access.justauth.processor;

import cn.herodotus.engine.access.core.definition.AccessHandler;
import cn.herodotus.engine.access.core.definition.AccessResponse;
import cn.herodotus.engine.access.core.definition.AccessUserDetails;
import cn.herodotus.engine.access.core.exception.AccessIdentityVerificationFailedException;
import cn.herodotus.engine.assistant.definition.domain.oauth2.AccessPrincipal;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.apache.commons.lang3.ObjectUtils;

/**
 * <p>Description: JustAuth 接入处理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/25 17:45
 */
public class JustAuthAccessHandler implements AccessHandler {

    private final JustAuthProcessor justAuthProcessor;

    public JustAuthAccessHandler(JustAuthProcessor justAuthProcessor) {
        this.justAuthProcessor = justAuthProcessor;
    }

    @Override
    public AccessResponse preProcess(String core, String... params) {
        String url = justAuthProcessor.getAuthorizeUrl(core);

        AccessResponse accessResponse = new AccessResponse();
        accessResponse.setAuthorizeUrl(url);
        return accessResponse;
    }

    @Override
    public AccessUserDetails loadUserDetails(String source, AccessPrincipal accessPrincipal) {
        AuthRequest authRequest = justAuthProcessor.getAuthRequest(source);

        AuthCallback authCallback = AuthCallback.builder()
                .code(accessPrincipal.getCode())
                .auth_code(accessPrincipal.getAuth_code())
                .state(accessPrincipal.getState())
                .authorization_code(accessPrincipal.getAuthorization_code())
                .oauth_token(accessPrincipal.getOauth_token())
                .oauth_verifier(accessPrincipal.getOauth_verifier())
                .build();

        AuthResponse<AuthUser> response = authRequest.login(authCallback);
        if (response.ok()) {
            AuthUser authUser = response.getData();
            return convertAuthUserToAccessUserDetails(authUser);
        }

        throw new AccessIdentityVerificationFailedException(response.getMsg());
    }

    private AccessUserDetails convertAuthUserToAccessUserDetails(AuthUser authUser) {
        AccessUserDetails sysSocialUser = new AccessUserDetails();
        sysSocialUser.setUuid(authUser.getUuid());
        sysSocialUser.setUsername(authUser.getUsername());
        sysSocialUser.setNickname(authUser.getNickname());
        sysSocialUser.setAvatar(authUser.getAvatar());
        sysSocialUser.setBlog(authUser.getBlog());
        sysSocialUser.setCompany(authUser.getCompany());
        sysSocialUser.setLocation(authUser.getLocation());
        sysSocialUser.setEmail(authUser.getEmail());
        sysSocialUser.setRemark(authUser.getRemark());
        sysSocialUser.setGender(authUser.getGender());
        sysSocialUser.setSource(authUser.getSource());
        AuthToken authToken = authUser.getToken();
        if (ObjectUtils.isNotEmpty(authToken)) {
            setAccessUserInfo(sysSocialUser, authToken.getAccessToken(), authToken.getExpireIn(), authToken.getRefreshToken(), authToken.getRefreshTokenExpireIn(), authToken.getScope(), authToken.getTokenType(), authToken.getUid(), authToken.getOpenId(), authToken.getAccessCode(), authToken.getUnionId());
        }

        return sysSocialUser;
    }

    private void setAccessUserInfo(AccessUserDetails accessUserDetails, String accessToken, Integer expireIn, String refreshToken, Integer refreshTokenExpireIn, String scope, String tokenType, String uid, String openId, String accessCode, String unionId) {
        accessUserDetails.setAccessToken(accessToken);
        accessUserDetails.setExpireIn(expireIn);
        accessUserDetails.setRefreshToken(refreshToken);
        accessUserDetails.setRefreshTokenExpireIn(refreshTokenExpireIn);
        accessUserDetails.setScope(scope);
        accessUserDetails.setTokenType(tokenType);
        accessUserDetails.setUid(uid);
        accessUserDetails.setOpenId(openId);
        accessUserDetails.setAccessCode(accessCode);
        accessUserDetails.setUnionId(unionId);
    }
}
