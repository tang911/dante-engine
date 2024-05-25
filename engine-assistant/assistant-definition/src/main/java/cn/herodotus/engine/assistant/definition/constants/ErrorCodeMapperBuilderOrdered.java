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

package cn.herodotus.engine.assistant.definition.constants;

/**
 * <p>Description: 错误码构建顺序 </p>
 * <p>
 * 注解@Order或者接口Ordered的作用是定义Spring IOC容器中Bean的执行顺序的优先级，而不是定义Bean的加载顺序，Bean的加载顺序不受@Order或Ordered接口的影响
 *
 * @author : gengwei.zheng
 * @date : 2023/9/26 21:20
 */
public interface ErrorCodeMapperBuilderOrdered {

    int STEP = 10;

    int STANDARD = 0;
    int CACHE = STANDARD + STEP;
    int CAPTCHA = CACHE + STEP;
    int OAUTH2 = CAPTCHA + STEP;
    int REST = OAUTH2 + STEP;
    int MESSAGE = REST + STEP;
    int ACCESS = MESSAGE + STEP;
    int OSS = ACCESS + STEP;
}
