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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Description: 数组工具类 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/5/3 23:12
 */
public class ArrayUtils {

    /**
     * 将字符串数组转换成字符串List
     *
     * @param array 字符串数组
     * @return 字符串List
     */
    public static List<String> toStringList(String[] array) {
        if (org.apache.commons.lang3.ArrayUtils.isNotEmpty(array)) {
            List<String> list = new ArrayList<>(array.length);
            Collections.addAll(list, array);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}
