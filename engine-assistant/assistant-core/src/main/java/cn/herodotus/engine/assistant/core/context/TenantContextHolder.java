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

package cn.herodotus.engine.assistant.core.context;

import cn.herodotus.engine.assistant.definition.constants.DefaultConstants;
import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 存储/获取当前线程的租户信息 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/9/4 22:34
 */
public class TenantContextHolder {

    private static final ThreadLocal<String> CURRENT_CONTEXT = new TransmittableThreadLocal<>();

    public static String getTenantId() {
        String tenantId = CURRENT_CONTEXT.get();
        if (StringUtils.isBlank(tenantId)) {
            tenantId = DefaultConstants.TENANT_ID;
        }
        return tenantId;
    }

    public static void setTenantId(final String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            CURRENT_CONTEXT.set(DefaultConstants.TENANT_ID);
        } else {
            CURRENT_CONTEXT.set(tenantId);
        }
    }

    public static void clear() {
        CURRENT_CONTEXT.remove();
    }
}
