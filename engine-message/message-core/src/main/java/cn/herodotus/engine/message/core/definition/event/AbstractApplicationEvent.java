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

package cn.herodotus.engine.message.core.definition.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * <p>Description: 自定义 Application Event 基础类 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/2/4 15:14
 */
public abstract class AbstractApplicationEvent<T> extends ApplicationEvent {

    private final T data;

    public AbstractApplicationEvent(T data) {
        super(data);
        this.data = data;
    }

    public AbstractApplicationEvent(T data, Clock clock) {
        super(data, clock);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
