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

package cn.herodotus.stirrup.assistant.oss.entity.domain;

import cn.herodotus.stirrup.assistant.oss.definition.domain.OssDomain;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 策略实体 </p>
 * <p>
 * 当前策略实体的实现是基于 Minio 数据构建。是否适用于所有 OSS 有待测试
 *
 * @author : gengwei.zheng
 * @date : 2026/2/4 23:32
 */
public class PolicyDomain implements OssDomain {

    @JsonProperty("Version")
    private String version = "2012-10-17";

    @JsonProperty("Statement")
    private List<StatementDomain> statements = new ArrayList<>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<StatementDomain> getStatements() {
        return statements;
    }

    public void setStatements(List<StatementDomain> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("version", version)
                .add("statements", statements)
                .toString();
    }
}
