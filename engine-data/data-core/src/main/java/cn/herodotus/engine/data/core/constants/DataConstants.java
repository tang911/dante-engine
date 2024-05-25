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

package cn.herodotus.engine.data.core.constants;

import cn.herodotus.engine.assistant.definition.constants.BaseConstants;

/**
 * <p>Description: 数据常量 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/19 18:10
 */
public interface DataConstants extends BaseConstants {

    String ITEM_SPRING_SQL_INIT_PLATFORM = "spring.sql.init.platform";
    String PROPERTY_PREFIX_MULTI_TENANT = PROPERTY_PREFIX_DATA + ".multi-tenant";
    String ITEM_DATA_DATA_SOURCE = PROPERTY_PREFIX_DATA + ".data-source";
    String ITEM_MULTI_TENANT_APPROACH = PROPERTY_PREFIX_MULTI_TENANT + ".approach";

    String ANNOTATION_SQL_INIT_PLATFORM = ANNOTATION_PREFIX + ITEM_SPRING_SQL_INIT_PLATFORM + ANNOTATION_SUFFIX;

    String CORE_AREA_PREFIX = AREA_PREFIX + "core:";
    String REGION_SYS_TENANT_DATASOURCE = CORE_AREA_PREFIX + "sys:tenant:datasource";
}
