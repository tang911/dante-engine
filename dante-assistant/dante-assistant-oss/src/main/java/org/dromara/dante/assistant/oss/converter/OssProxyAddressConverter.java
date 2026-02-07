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

package cn.herodotus.stirrup.assistant.oss.converter;

import cn.herodotus.stirrup.assistant.oss.constant.OssConstants;
import cn.herodotus.stirrup.assistant.oss.properties.OssProperties;
import org.apache.commons.lang3.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: 默认代理地址转换器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/8/19 18:21
 */
public class OssProxyAddressConverter implements Converter<String, String> {

    private static final Logger log = LoggerFactory.getLogger(OssProxyAddressConverter.class);

    private final OssProperties ossProperties;

    public OssProxyAddressConverter(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Override
    public String convert(String source) {
        if (ossProperties.getProxy().getEnabled()) {
            String endpoint = ossProperties.getProxy().getSource() + OssConstants.PRESIGNED_OBJECT_URL_PROXY;
            String target = Strings.CS.replace(source, ossProperties.getProxy().getDestination(), endpoint);
            log.debug("[Herodotus] |- Convert preSignedObjectUrl [{}] to [{}].", endpoint, target);
            return target;
        }

        return source;
    }
}
