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

package cn.herodotus.engine.supplier.upms.logic.domain.listener;

import cn.herodotus.engine.rest.core.definition.context.AbstractApplicationContextAware;
import cn.herodotus.engine.supplier.upms.logic.domain.event.SysAttributeChangeEvent;
import cn.herodotus.engine.supplier.upms.logic.entity.security.SysAttribute;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: SysAttribute实体数据变更监听 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/4 16:54
 */
public class SysAttributeEntityListener extends AbstractApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SysAttributeEntityListener.class);

    @PostUpdate
    protected void postUpdate(SysAttribute entity) {
        log.debug("[Herodotus] |- [1] SysAttribute entity @PostUpdate activated, value is : [{}]. Trigger SysAttribute change event.", entity.toString());
        publishEvent(new SysAttributeChangeEvent(entity));
    }
}
