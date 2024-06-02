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

package cn.herodotus.engine.rest.condition.constants;

import cn.herodotus.engine.assistant.definition.constants.BaseConstants;

/**
 * <p>Description: Rest 模块常量 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/19 23:13
 */
public interface RestConstants extends BaseConstants {


    String PROPERTY_OPENFEIGN_HTTP2CLIENT = PROPERTY_SPRING_CLOUD_OPENFEIGN + ".http2client";
    String PROPERTY_OPENFEIGN_HTTPCLIENT5 = PROPERTY_SPRING_CLOUD_OPENFEIGN + ".httpclient";
    String PROPERTY_REST_SCAN = PROPERTY_PREFIX_REST + ".scan";
    String ITEM_PLATFORM_DATA_ACCESS_STRATEGY = PROPERTY_PREFIX_PLATFORM + ".data-access-strategy";
    String ITEM_PLATFORM_ARCHITECTURE = PROPERTY_PREFIX_PLATFORM + ".architecture";

    String ITEM_SCAN_ENABLED = PROPERTY_REST_SCAN + PROPERTY_ENABLED;
    String ITEM_OPENFEIGN_HTTP2CLIENT_ENABLED = PROPERTY_OPENFEIGN_HTTP2CLIENT + PROPERTY_ENABLED;
    String ITEM_OPENFEIGN_HTTPCLIENT5_ENABLED = PROPERTY_OPENFEIGN_HTTPCLIENT5 + ".hc5" + PROPERTY_ENABLED;
    String ITEM_PROTECT_CRYPTO_STRATEGY = PROPERTY_PREFIX_CRYPTO + ".crypto-strategy";

    String CACHE_NAME_TOKEN_IDEMPOTENT = CACHE_TOKEN_BASE_PREFIX + "idempotent:";
    String CACHE_NAME_TOKEN_ACCESS_LIMITED = CACHE_TOKEN_BASE_PREFIX + "access_limited:";
    String CACHE_NAME_TOKEN_SECURE_KEY = CACHE_TOKEN_BASE_PREFIX + "secure_key:";
}
