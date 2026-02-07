/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Stirrup.
 *
 * Herodotus Stirrup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Stirrup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.stirrup.assistant.oss.definition.service;

import org.springframework.core.convert.converter.Converter;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * <p>Description: 阻塞式操作通用方法 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 18:04
 */
public abstract class AbstractServletService {
    /**
     * 响应式对象存储操作通用处理方法
     *
     * @param argument    实际应用请求参数
     * @param toRequest   实际应用请求参数转换为AWS SDK V2 XXXRequest 实体转换器
     * @param toResult    AWS SDK V2 XXXResponse 转换为实际应用响应结果实体转换器
     * @param handler     AWS SDK V2 操作API
     * @param <ArgumentA> 实际应用请求参数类型
     * @param <RequestR>  AWS SDK V2 XXXRequest 实体类型
     * @param <ResponseR> AWS SDK V2 XXXResponse 实体类型
     * @param <ResultR>   实际应用响应结果类型
     * @return 阻塞式对象存储操作返回数据
     */
    protected <ArgumentA, RequestR, ResponseR, ResultR> ResultR process(ArgumentA argument, Converter<ArgumentA, RequestR> toRequest, Converter<ResponseR, ResultR> toResult, Function<RequestR, CompletableFuture<ResponseR>> handler) {
        CompletableFuture<ResponseR> future = handler.apply(toRequest.convert(argument));
        return toResult.convert(future.join());
    }

    /**
     * 响应式对象存储操作通用处理方法
     *
     * @param argument    实际应用请求参数
     * @param toRequest   实际应用请求参数转换为AWS SDK V2 XXXRequest 实体转换器
     * @param handler     AWS SDK V2 操作API
     * @param <ArgumentA> 实际应用请求参数类型
     * @param <RequestR>  AWS SDK V2 XXXRequest 实体类型
     * @param <ResultR>   实际应用响应结果类型
     * @return 阻塞式对象存储操作返回数据
     */
    protected <ArgumentA, RequestR, ResultR> ResultR process(ArgumentA argument, Converter<ArgumentA, RequestR> toRequest, Function<RequestR, CompletableFuture<ResultR>> handler) {
        CompletableFuture<ResultR> future = handler.apply(toRequest.convert(argument));
        return future.join();
    }
}
