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

import java.util.List;

/**
 * <p>Description: 策略 Statement 实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2026/2/4 23:31
 */
public class StatementDomain implements OssDomain {

    @JsonProperty("Effect")
    private String effect = "Allow";

    @JsonProperty("Action")
    private List<String> actions;

    @JsonProperty("Resource")
    private List<String> resources;

    @JsonProperty("Principal")
    private PrincipalDomain principal = new PrincipalDomain();

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public PrincipalDomain getPrincipal() {
        return principal;
    }

    public void setPrincipal(PrincipalDomain principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("effect", effect)
                .add("actions", actions)
                .add("resources", resources)
                .add("principal", principal)
                .toString();
    }
}
