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

package cn.herodotus.engine.assistant.ip2region.definition;

import cn.herodotus.engine.assistant.ip2region.domain.IpLocation;
import org.apache.commons.lang3.ObjectUtils;

import java.util.function.Function;

/**
 * <p>Description: IP 定位离线搜索定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/23 12:40
 */
public interface Ip2RegionSearcher {

    default String read(IpLocation ip, Function<IpLocation, String> function) {
        if (ObjectUtils.isNotEmpty(ip)) {
            return function.apply(ip);
        } else {
            return null;
        }
    }


    /**
     * ip 位置查询
     *
     * @param ip ip
     * @return Ip 信息详情 {@link IpLocation}
     */
    IpLocation memorySearch(long ip);

    /**
     * ip 位置查询
     *
     * @param ip ip
     * @return Ip 信息详情 {@link IpLocation}
     */
    IpLocation memorySearch(String ip);

    /**
     * 搜索并读取IP信息
     *
     * @param ip       ip
     * @param function {@link Function}
     * @return 地址
     */
    default String get(long ip, Function<IpLocation, String> function) {
        return read(memorySearch(ip), function);
    }

    /**
     * 搜索并读取IP信息
     *
     * @param ip       ip
     * @param function {@link Function}
     * @return 地址
     */
    default String get(String ip, Function<IpLocation, String> function) {
        return read(memorySearch(ip), function);
    }

    /**
     * 获取地址信息
     *
     * @param ip ip
     * @return 地址
     */
    default String getLocation(long ip) {
        return get(ip, IpLocation::getLocation);
    }

    /**
     * 获取地址信息
     *
     * @param ip ip
     * @return 地址
     */
    default String getLocation(String ip) {
        return get(ip, IpLocation::getLocation);
    }

    /**
     * 获取包含 isp 的地址信息
     *
     * @param ip ip
     * @return 地址信息
     */
    default String getLocationWithIsp(long ip) {
        return get(ip, IpLocation::getLocationWithIsp);
    }

    /**
     * 获取包含 isp 的地址信息
     *
     * @param ip ip
     * @return 地址信息
     */
    default String getLocationWithIsp(String ip) {
        return get(ip, IpLocation::getLocationWithIsp);
    }
}
