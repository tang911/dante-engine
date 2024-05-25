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

package cn.herodotus.engine.assistant.ip2region.configuration;

import cn.herodotus.engine.assistant.ip2region.definition.Ip2RegionSearcher;
import cn.herodotus.engine.assistant.ip2region.properties.Ip2RegionProperties;
import cn.herodotus.engine.assistant.ip2region.searcher.DefaultIp2RegionSearcher;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Ip2Region 配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/24 11:59
 */
@AutoConfiguration
@EnableConfigurationProperties(Ip2RegionProperties.class)
public class Ip2RegionConfiguration {

    private static final Logger log = LoggerFactory.getLogger(Ip2RegionConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Ip2Region] Auto Configure.");
    }

    @Bean
    public Ip2RegionSearcher defaultIp2RegionSearcher(Ip2RegionProperties ip2RegionProperties) {
        DefaultIp2RegionSearcher searcher = new DefaultIp2RegionSearcher(ip2RegionProperties.getIpV4Resource(), ip2RegionProperties.getIpV6Resource());
        log.trace("[Herodotus] |- Bean [Ip2Region Searcher] Auto Configure.");
        return searcher;
    }

}
