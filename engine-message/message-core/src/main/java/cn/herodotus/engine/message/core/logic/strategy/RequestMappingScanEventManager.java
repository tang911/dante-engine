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

package cn.herodotus.engine.message.core.logic.strategy;

import cn.herodotus.engine.assistant.core.context.ServiceContextHolder;
import cn.herodotus.engine.message.core.definition.strategy.ApplicationStrategyEventManager;
import cn.herodotus.engine.message.core.logic.domain.RequestMapping;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: RequestMapping 扫描管理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/1/16 18:42
 */
public interface RequestMappingScanEventManager extends ApplicationStrategyEventManager<List<RequestMapping>> {

    /**
     * 获取是否执行扫描的标记注解。
     *
     * @return 标记注解
     */
    Class<? extends Annotation> getScanAnnotationClass();

    /**
     * 执行本地数据存储
     *
     * @param requestMappings 扫描到的RequestMapping
     */
    void postLocalStorage(List<RequestMapping> requestMappings);

    /**
     * 发布远程事件，传送RequestMapping
     *
     * @param requestMappings 扫描到的RequestMapping
     */
    @Override
    default void postProcess(List<RequestMapping> requestMappings) {
        postLocalStorage(requestMappings);
        ApplicationStrategyEventManager.super.postProcess(requestMappings);
    }

    /**
     * 是否满足执行扫描的条件。
     * 根据扫描标记注解 {@link #getScanAnnotationClass()} 以及 是否是分布式架构 决定是否执行接口的扫描。
     * <p>
     * 分布式架构根据注解判断是否扫描，单体架构直接扫描即可无须判断
     *
     * @return true 执行， false 不执行
     */
    default boolean isPerformScan() {
        if (ServiceContextHolder.getInstance().isDistributedArchitecture()) {
            if (ObjectUtils.isEmpty(getScanAnnotationClass())) {
                return false;
            }

            Map<String, Object> content = ServiceContextHolder.getInstance().getApplicationContext().getBeansWithAnnotation(getScanAnnotationClass());
            return !MapUtils.isEmpty(content);
        }

        return true;
    }
}
