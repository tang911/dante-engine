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

package org.dromara.dante.assistant.oss.utils;

import cn.hutool.v7.http.html.HtmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.dromara.dante.core.constant.SymbolConstants;

/**
 * <p>Description: 对象存储工具类 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/27 18:53
 */
public class OssUtils {

    public static String unwrapETag(String source) {

        String target = source;

        if (StringUtils.isEmpty(target)) {
            return null;
        }

        if (Strings.CS.contains(target, SymbolConstants.AMPERSAND)) {
            target = HtmlUtil.unescape(source);
        }

        if (Strings.CS.contains(target, SymbolConstants.QUOTE)) {
            target = StringUtils.unwrap(target, SymbolConstants.QUOTE);
        }

        return target;
    }
}
