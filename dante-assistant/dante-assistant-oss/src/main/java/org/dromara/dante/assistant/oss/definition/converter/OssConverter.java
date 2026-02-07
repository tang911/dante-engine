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

package cn.herodotus.stirrup.assistant.oss.definition.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: 统一转换器定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/8/16 13:00
 */
public interface OssConverter<S, T> extends Converter<S, T> {

    /**
     * 参数准备
     *
     * @param source 源对象
     * @param target 转换后的对象
     */
    void prepare(S source, T target);

    /**
     * 获取最终生成对象实例
     *
     * @param source 源对象。传递源对象，方便参数设置
     * @return 转换后的对象实例
     */
    T getInstance(S source);

    /**
     * 实体转换
     *
     * @param source 统一定义请求参数
     * @return 转换后的对象实例
     */
    @Override
    default T convert(S source) {
        T instance = getInstance(source);
        prepare(source, instance);
        return instance;
    }
}
