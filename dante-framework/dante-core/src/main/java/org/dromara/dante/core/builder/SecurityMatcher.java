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

package org.dromara.dante.core.builder;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: 静态 Security 权限构建器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2025/12/13 17:37
 */
public class SecurityMatcher {

    private List<String> staticResources;
    private List<String> permitAllResources;
    private List<String> hasAuthenticatedResources;

    public List<String> getHasAuthenticatedResources() {
        return hasAuthenticatedResources;
    }

    private void setHasAuthenticatedResources(List<String> hasAuthenticatedResources) {
        this.hasAuthenticatedResources = hasAuthenticatedResources;
    }

    public List<String> getPermitAllResources() {
        return permitAllResources;
    }

    private void setPermitAllResources(List<String> permitAllResources) {
        this.permitAllResources = permitAllResources;
    }

    public List<String> getStaticResources() {
        return staticResources;
    }

    private void setStaticResources(List<String> staticResources) {
        this.staticResources = staticResources;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<String> staticResources;
        private List<String> permitAllResources;
        private List<String> hasAuthenticatedResources;

        protected Builder() {
            this.staticResources = new ArrayList<>();
            this.permitAllResources = new ArrayList<>();
            this.hasAuthenticatedResources = new ArrayList<>();
        }

        public Builder staticResources(List<String> staticResources) {
            this.staticResources = merge(this.staticResources, staticResources);
            return this;
        }

        public Builder permitAll(List<String> permitAllResources) {
            this.permitAllResources = merge(this.permitAllResources, permitAllResources);
            return this;
        }

        public Builder hasAuthenticated(List<String> hasAuthenticatedResources) {
            this.hasAuthenticatedResources = merge(this.hasAuthenticatedResources, hasAuthenticatedResources);
            return this;
        }

        private List<String> merge(List<String> target, List<String> source) {
            if (CollectionUtils.isEmpty(target)) {
                return source;
            }

            if (CollectionUtils.isEmpty(source)) {
                return target;
            }

            Set<String> result = new HashSet<>(target.size() + source.size());
            result.addAll(target);
            result.addAll(source);
            return new ArrayList<>(result);
        }

        public SecurityMatcher build() {
            SecurityMatcher securityMatcher = new SecurityMatcher();
            securityMatcher.setStaticResources(this.staticResources);
            securityMatcher.setPermitAllResources(this.permitAllResources);
            securityMatcher.setHasAuthenticatedResources(this.hasAuthenticatedResources);
            return securityMatcher;
        }
    }
}
