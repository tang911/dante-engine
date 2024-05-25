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

package cn.herodotus.engine.rest.core.controller;

import cn.herodotus.engine.assistant.definition.domain.base.AbstractEntity;
import cn.herodotus.engine.assistant.definition.domain.Result;
import cn.herodotus.engine.data.core.service.ReadableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 只读Controller </p>
 *
 * @author : gengwei.zheng
 * @date : 2020/4/30 11:00
 */
public interface ReadableController<E extends AbstractEntity, ID extends Serializable> extends Controller {

    /**
     * 获取Service
     *
     * @return Service
     */
    ReadableService<E, ID> getReadableService();

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码，起始页码 0
     * @param pageSize   每页显示数据条数
     * @return {@link Result}
     */
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize) {
        Page<E> pages = getReadableService().findByPage(pageNumber, pageSize);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link org.springframework.data.domain.Sort.Direction}
     * @param properties 排序的属性名称
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties) {
        Page<E> pages = getReadableService().findByPage(pageNumber, pageSize, direction, properties);
        return result(pages);
    }

    default Result<List<E>> findAll() {
        List<E> domains = getReadableService().findAll();
        return result(domains);
    }

    default Result<E> findById(ID id) {
        E domain = getReadableService().findById(id);
        return result(domain);
    }
}
