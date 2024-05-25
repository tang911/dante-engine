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

package cn.herodotus.engine.data.core.repository;

import cn.herodotus.engine.assistant.definition.domain.base.Entity;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * <p> Description : 基础Repository </p>
 *
 * @author : gengwei.zheng
 * @date : 2020/4/29 15:21
 */
@NoRepositoryBean
public interface BaseRepository<E extends Entity, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    List<E> findAll();

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    List<E> findAll(Sort sort);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    Optional<E> findOne(Specification<E> specification);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    List<E> findAll(Specification<E> specification);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    Page<E> findAll(Specification<E> specification, Pageable pageable);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    List<E> findAll(Specification<E> specification, Sort sort);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    long count(Specification<E> specification);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    Page<E> findAll(Pageable pageable);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    Optional<E> findById(ID id);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @Override
    long count();

    @Transactional
    @Override
    void deleteById(ID id);
}
