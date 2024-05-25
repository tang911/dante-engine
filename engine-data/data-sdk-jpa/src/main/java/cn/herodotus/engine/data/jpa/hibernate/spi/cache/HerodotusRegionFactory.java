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

package cn.herodotus.engine.data.jpa.hibernate.spi.cache;

import org.dromara.hutool.extra.spring.SpringUtil;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.cfg.spi.DomainDataRegionBuildingContext;
import org.hibernate.cache.cfg.spi.DomainDataRegionConfig;
import org.hibernate.cache.spi.support.DomainDataStorageAccess;
import org.hibernate.cache.spi.support.RegionFactoryTemplate;
import org.hibernate.cache.spi.support.StorageAccess;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.cache.CacheManager;

import java.util.Map;

/**
 * <p>Description: 自定义Hibernate二级缓存RegionFactory </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/7/12 22:04
 */
public class HerodotusRegionFactory extends RegionFactoryTemplate {

    private CacheManager cacheManager;

    @Override
    protected StorageAccess createQueryResultsRegionStorageAccess(String regionName, SessionFactoryImplementor sessionFactory) {
        return new HerodotusDomainDataStorageAccess(cacheManager.getCache(regionName));
    }

    @Override
    protected StorageAccess createTimestampsRegionStorageAccess(String regionName, SessionFactoryImplementor sessionFactory) {
        return new HerodotusDomainDataStorageAccess(cacheManager.getCache(regionName));
    }

    @Override
    protected DomainDataStorageAccess createDomainDataStorageAccess(DomainDataRegionConfig regionConfig, DomainDataRegionBuildingContext buildingContext) {
        return new HerodotusDomainDataStorageAccess(cacheManager.getCache(regionConfig.getRegionName()));
    }

    @Override
    protected void prepareForUse(SessionFactoryOptions settings, Map configValues) {
        this.cacheManager = SpringUtil.getBean("herodotusCacheManager");
    }

    @Override
    protected void releaseFromUse() {
        cacheManager = null;
    }
}
