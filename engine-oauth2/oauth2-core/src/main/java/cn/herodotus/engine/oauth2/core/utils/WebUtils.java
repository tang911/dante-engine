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

package cn.herodotus.engine.oauth2.core.utils;

import cn.herodotus.engine.assistant.core.json.jackson2.utils.Jackson2Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>Description:  Http与Servlet工具类. </p>
 *
 * @author : gengwei.zheng
 * @date : 2020/3/4 11:39
 */
public class WebUtils extends org.springframework.web.util.WebUtils {

    private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

    /**
     * 将配置路径转换为 {@link RequestMatcher} 数组
     *
     * @param paths 静态请求路径
     * @return {@link RequestMatcher} 数组
     */
    public static RequestMatcher[] toRequestMatchers(List<String> paths) {
        if (CollectionUtils.isNotEmpty(paths)) {
            List<AntPathRequestMatcher> matchers = paths.stream().map(item -> new AntPathRequestMatcher(item, null, false)).toList();
            RequestMatcher[] result = new RequestMatcher[matchers.size()];
            return matchers.toArray(result);
        } else {
            return new RequestMatcher[]{};
        }
    }

    /**
     * 判断请求是否与设定的模式匹配
     *
     * @param matchers 路径匹配模式
     * @param request  请求
     * @return 是否匹配
     */
    public static boolean isRequestMatched(RequestMatcher[] matchers, HttpServletRequest request) {
        for (RequestMatcher matcher : matchers) {
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断请求是否与设定的模式匹配
     *
     * @param paths   路径
     * @param request 请求
     * @return 是否匹配
     */
    public static boolean isRequestMatched(List<String> paths, HttpServletRequest request) {
        RequestMatcher[] matchers = toRequestMatchers(paths);
        return isRequestMatched(matchers, request);
    }

    /**
     * 客户端返回JSON字符串
     *
     * @param response HttpServletResponse
     * @param object   需要转换的对象
     */
    public static void renderJson(HttpServletResponse response, Object object) {
        renderJson(response, Jackson2Utils.toJson(object), MediaType.APPLICATION_JSON.toString());
    }

    /**
     * 客户端返回字符串
     *
     * @param response HttpServletResponse
     * @param string   需要绘制的信息
     */
    public static void renderJson(HttpServletResponse response, String string, String type) {
        try {
            response.setContentType(type);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().print(string);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            log.error("[Herodotus] |- Render response to Json error!");
        }
    }
}
