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

package cn.herodotus.engine.assistant.core.utils.type;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>Description: 特殊日期处理 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/2 22:54
 */
public class DateTimeUtils {

    private static final String DEFAULT_DATA_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_TIME_ZONE_NAME = "Asia/Shanghai";

    public static String zonedDateTimeToString(ZonedDateTime zonedDateTime) {
        return zonedDateTimeToString(zonedDateTime, DEFAULT_DATA_TIME_FORMAT);
    }

    public static String zonedDateTimeToString(ZonedDateTime zonedDateTime, String format) {
        if (ObjectUtils.isNotEmpty(zonedDateTime) && StringUtils.isNotBlank(format)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.of(DEFAULT_TIME_ZONE_NAME));
            return zonedDateTime.format(formatter);
        }
        return null;
    }

    public static ZonedDateTime stringToZonedDateTime(String dateString) {
        return stringToZonedDateTime(dateString, DEFAULT_DATA_TIME_FORMAT);
    }

    public static ZonedDateTime stringToZonedDateTime(String dateString, String format) {
        if (StringUtils.isNotBlank(dateString) && StringUtils.isNotBlank(format)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.of(DEFAULT_TIME_ZONE_NAME));
            return ZonedDateTime.parse(dateString, formatter);
        }
        return null;
    }

    /**
     * ZonedDateTime 转换成 Date
     *
     * @param zonedDateTime {@link ZonedDateTime}
     * @return 如果 ZonedDateTime 有值则返回对应的 Date，如果为空则返回 当前日期
     */
    public static Date zonedDateTimeToDate(ZonedDateTime zonedDateTime) {
        if (ObjectUtils.isNotEmpty(zonedDateTime)) {
            return Date.from(zonedDateTime.toInstant());
        }
        return new Date();
    }

    /**
     * Date 转换成  ZonedDateTime
     *
     * @param date {@link Date}
     * @return 如果 Date 有值则返回对应的 ZonedDateTime，如果为空则返回 当前日期
     */
    public static ZonedDateTime dateToZonedDateTime(Date date) {
        if (ObjectUtils.isNotEmpty(date)) {
            return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }
        return ZonedDateTime.now();
    }
}
