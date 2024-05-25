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

package cn.herodotus.engine.rest.core.annotation;

import java.lang.annotation.*;
import java.time.Duration;

/**
 * <p>Description: 接口防刷注解 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/25 21:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AccessLimited {

    /**
     * 单位时间内同一个接口可以访问的次数
     *
     * @return int
     */
    int maxTimes() default 0;

    /**
     * 持续时间，即在多长时间内，限制访问多少次。具体单位根据TimeUnit的设置而定。
     * <p>
     * 使用Duration格式{@link Duration}
     * <p>
     * 默认为：0，即不设置该属性。那么就使用StampProperies中的配置进行设置。
     * 如果设置了该值，就以该值进行设置。
     */
    String duration() default "";
}
