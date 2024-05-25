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

package cn.herodotus.engine.rest.protect.tenant;

import cn.herodotus.engine.assistant.definition.constants.DefaultConstants;
import cn.herodotus.engine.assistant.core.context.TenantContextHolder;
import cn.herodotus.engine.assistant.core.utils.http.HeaderUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * <p>Description: 多租户过滤器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/9/11 15:03
 */
public class MultiTenantFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String tenantId = HeaderUtils.getHerodotusTenantId(request);
        TenantContextHolder.setTenantId(StringUtils.isBlank(tenantId) ? DefaultConstants.TENANT_ID : tenantId);

        filterChain.doFilter(servletRequest, servletResponse);
        TenantContextHolder.clear();
    }

    @Override
    public void destroy() {
        TenantContextHolder.clear();
    }
}
