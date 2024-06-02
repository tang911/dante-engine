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

package cn.herodotus.engine.message.core.definition.strategy;

/**
 * <p>Description: 应用策略事件 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/3/29 7:26
 */
public interface ApplicationStrategyEventManager<T> extends StrategyEventManager<T> {

    /**
     * 目的服务名称
     *
     * @return 服务名称
     */
    String getDestinationServiceName();

    /**
     * 发送事件
     *
     * @param data 事件携带数据
     */
    default void postProcess(T data) {
        postProcess(getDestinationServiceName(), data);
    }
}
