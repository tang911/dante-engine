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

package cn.herodotus.engine.access.core.definition;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;

/**
 * <p>Description: 外部接入预操作统一返回实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/25 16:35
 */
public class AccessResponse {

    /**
     * JustAuth 认证URL
     */
    private String authorizeUrl;
    /**
     * 手机短信验证码是否发送成功
     */
    private Boolean success;
    /**
     * 微信小程序返回Session信息
     */
    private WxMaJscode2SessionResult session;

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public WxMaJscode2SessionResult getSession() {
        return session;
    }

    public void setSession(WxMaJscode2SessionResult session) {
        this.session = session;
    }
}
