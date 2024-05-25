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

package cn.herodotus.engine.oauth2.management.dto;

import cn.herodotus.engine.assistant.definition.domain.base.AbstractDto;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Description: 加密创建请求 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/10/1 15:19
 */
@Schema(title = "加密创建请求")
public class SessionCreate extends AbstractDto {

    @NotBlank(message = "客户端ID不能为空")
    @Schema(title = "客户端ID")
    private String clientId;

    @NotBlank(message = "客户端秘钥不能为空")
    @Schema(title = "客户端秘钥")
    private String clientSecret;

    @Schema(title = "未登录前端身份标识")
    private String sessionId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("clientId", clientId)
                .add("clientSecret", clientSecret)
                .add("sessionId", sessionId)
                .toString();
    }
}
