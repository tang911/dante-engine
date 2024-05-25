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

package cn.herodotus.engine.assistant.ip2region.searcher.test;

import cn.herodotus.engine.assistant.ip2region.configuration.Ip2RegionConfiguration;
import cn.herodotus.engine.assistant.ip2region.definition.Ip2RegionSearcher;
import cn.herodotus.engine.assistant.ip2region.domain.IpLocation;
import cn.herodotus.engine.assistant.ip2region.properties.Ip2RegionProperties;
import cn.herodotus.engine.assistant.ip2region.searcher.DefaultIp2RegionSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: Ip2Region 测试 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/10/24 14:08
 */
class DefaultIp2RegionSearcherTest {

    private Ip2RegionSearcher searcher;

    @BeforeEach
    public void setup() throws Exception {
        Ip2RegionConfiguration configuration = new Ip2RegionConfiguration();
        searcher = configuration.defaultIp2RegionSearcher(new Ip2RegionProperties());
        ((DefaultIp2RegionSearcher) searcher).afterPropertiesSet();
    }

    @Test
    void testIpV4Region() throws Exception {
        Assertions.assertEquals(searcher.memorySearch("220.248.12.158").getLocation(), "中国上海市上海");
        Assertions.assertEquals(searcher.memorySearch("222.240.36.135").getLocation(), "中国长沙市湖南省");
        Assertions.assertEquals(searcher.memorySearch("172.30.13.97").getLocation(), "内网IP");
        Assertions.assertEquals(searcher.memorySearch("223.26.64.0").getLocation(), "中国台北台湾省");
        Assertions.assertEquals(searcher.memorySearch("223.26.128.0").getLocation(), "韩国首尔");
        Assertions.assertEquals(searcher.memorySearch("223.26.67.0").getLocation(), "中国台湾省");
        Assertions.assertEquals(searcher.memorySearch("223.29.220.0").getLocation(), "印度北方邦");
        Assertions.assertEquals(searcher.memorySearch("82.120.124.0").getLocation(), "法国Loire");
    }

    @Test
    void testIpV6Region() throws Exception {
        Assertions.assertNotNull(searcher.memorySearch("::ffff:1111:2222"));
        Assertions.assertNotNull(searcher.memorySearch("2001:db8::ffff:1111:2222"));
        Assertions.assertNotNull(searcher.memorySearch("::1"));
        Assertions.assertNotNull(searcher.memorySearch("2406:840:20::1"));
        Assertions.assertNotNull(searcher.memorySearch("2c0f:feb0:a::"));
        Assertions.assertNotNull(searcher.memorySearch("240e:109:8047::"));
        Assertions.assertNotNull(searcher.memorySearch("1111:1111:1111::1111"));
    }

    @Test
    void test2() {
        IpLocation location = searcher.memorySearch("127.0.0.1");
        Assertions.assertNotNull(location);
    }
}
