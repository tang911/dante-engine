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

package cn.herodotus.engine.assistant.core.utils.type;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * <p>Description: List 常用工具类 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/3/28 23:15
 */
public class ListUtils {

    /**
     * 将两个已排序的集合a和b合并到一个单独的已排序列表中，以便保留元素的自然顺序。
     *
     * @param appendResources  自定义配置
     * @param defaultResources 默认配置
     * @return 合并后的List
     */
    public static List<String> merge(List<String> appendResources, List<String> defaultResources) {
        if (CollectionUtils.isEmpty(appendResources)) {
            return defaultResources;
        } else {
            return CollectionUtils.collate(defaultResources, appendResources);
        }
    }

    /**
     * 将 List 转换为 String[]
     *
     * @param resources List
     * @return String[]
     */
    public static String[] toStringArray(List<String> resources) {
        if (CollectionUtils.isNotEmpty(resources)) {
            String[] result = new String[resources.size()];
            return resources.toArray(result);
        } else {
            return new String[]{};
        }
    }
}
