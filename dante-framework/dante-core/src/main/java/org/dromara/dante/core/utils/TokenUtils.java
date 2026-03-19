/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Stirrup.
 *
 * Herodotus Stirrup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Stirrup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package org.dromara.dante.core.utils;

import org.dromara.dante.core.constant.SymbolConstants;
import org.dromara.dante.core.constant.SystemConstants;
import cn.hutool.v7.core.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

/**
 * <p>Description: Token 工具类 </p>
 *
 * @author : gengwei.zheng
 * @date : 2025/6/30 12:42
 */
public class TokenUtils {

    /**
     * 拼装 Bearer Token 标准格式
     *
     * @param accessToken JWT 或 Opaque Token.
     * @return 标准格式 Bearer Token
     */
    public static String bearer(String accessToken) {
        return SystemConstants.BEARER_TOKEN + accessToken;
    }

    /**
     * 拼装 Basic Token 标准格式
     *
     * @param clientId     客户端ID
     * @param clientSecret 客户端密钥
     * @return 标准格式 Basic Token
     */
    public static String basic(String clientId, String clientSecret) {
        return SystemConstants.BASIC_TOKEN + Base64.encode(clientId + SymbolConstants.COLON + clientSecret);
    }

    /**
     * 判断是否为标准格式 Token
     *
     * @param token 以 Bearer 或者 Basic 开头的 Token 内容
     * @return true 格式正确，false 格式错误。
     */
    public static boolean isToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            return Strings.CS.startsWith(token, SystemConstants.BEARER_TOKEN) || Strings.CS.startsWith(token, SystemConstants.BASIC_TOKEN);
        } else {
            return false;
        }
    }

    /**
     * 从以 Bearer 或者 Basic 开头的 Token 中，提取 Token 部分。
     *
     * @param token 以 Bearer 或者 Basic 开头的 Token
     * @return 去除了 Bearer 或者 Basic 的 Token
     */
    public static String extract(String token) {
        if (StringUtils.isNotBlank(token)) {
            if (Strings.CS.startsWith(token, SystemConstants.BEARER_TOKEN)) {
                return StringUtils.substringAfter(token, SystemConstants.BEARER_TOKEN);
            }

            if (Strings.CS.startsWith(token, SystemConstants.BASIC_TOKEN)) {
                return StringUtils.substringAfter(token, SystemConstants.BASIC_TOKEN);
            }
        }

        return null;
    }
}
