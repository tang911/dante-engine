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

package cn.herodotus.engine.message.websocket.utils;

import cn.herodotus.engine.cache.redis.utils.RedisBitMapUtils;
import cn.herodotus.engine.message.core.constants.MessageConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;

/**
 * <p>Description: WebSocket 通用工具类 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/12/30 12:17
 */
public class WebSocketUtils {

    public static HttpServletRequest getHttpServletRequest(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest request) {
            return request.getServletRequest();
        }

        return null;
    }

    public static HttpServletResponse getHttpServletResponse(ServerHttpResponse serverHttpResponse) {
        if (serverHttpResponse instanceof ServletServerHttpResponse response) {
            return response.getServletResponse();
        }

        return null;
    }

    public static int getOnlineCount() {
        Long count = RedisBitMapUtils.bitCount(MessageConstants.REDIS_CURRENT_ONLINE_USER);
        return count.intValue();
    }
}
