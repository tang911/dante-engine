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

package cn.herodotus.engine.supplier.upms.logic.constants;

import cn.herodotus.engine.assistant.definition.constants.BaseConstants;

/**
 * <p>Description: Upms 模块常量 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/2/16 18:19
 */
public interface UpmsConstants extends BaseConstants {

    String UPMS_AREA_PREFIX = AREA_PREFIX + "upms:";

    String REGION_SYS_USER = UPMS_AREA_PREFIX + "sys:user";
    String REGION_SYS_ROLE = UPMS_AREA_PREFIX + "sys:role";
    String REGION_SYS_DEFAULT_ROLE = UPMS_AREA_PREFIX + "sys:defaults:role";
    String REGION_SYS_PERMISSION = UPMS_AREA_PREFIX + "sys:permission";
    String REGION_SYS_OWNERSHIP = UPMS_AREA_PREFIX + "sys:ownership";
    String REGION_SYS_ELEMENT = UPMS_AREA_PREFIX + "sys:element";
    String REGION_SYS_SOCIAL_USER = UPMS_AREA_PREFIX + "sys:social:user";
    String REGION_SYS_DEPARTMENT = UPMS_AREA_PREFIX + "sys:department";
    String REGION_SYS_EMPLOYEE = UPMS_AREA_PREFIX + "sys:employee";
    String REGION_SYS_ORGANIZATION = UPMS_AREA_PREFIX + "sys:organization";
}
