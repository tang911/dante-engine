/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Engine.
 *
 * Herodotus Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.rest.condition.properties;

import cn.herodotus.engine.rest.condition.constants.RestConstants;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.time.Duration;

/**
 * <p>Description: 跟踪标记配置属性 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/24 15:53
 */
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_SECURE)
public class SecureProperties {

    private Idempotent idempotent = new Idempotent();
    private AccessLimited accessLimited = new AccessLimited();

    public Idempotent getIdempotent() {
        return idempotent;
    }

    public void setIdempotent(Idempotent idempotent) {
        this.idempotent = idempotent;
    }

    public AccessLimited getAccessLimited() {
        return accessLimited;
    }

    public void setAccessLimited(AccessLimited accessLimited) {
        this.accessLimited = accessLimited;
    }

    public static class Idempotent implements Serializable {

        /**
         * 幂等签章缓存默认过期时间，以防Token删除失败后，缓存数据始终存在影响使用，默认：5秒
         */
        private Duration expire = Duration.ofSeconds(5);

        public Duration getExpire() {
            return expire;
        }

        public void setExpire(Duration expire) {
            this.expire = expire;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("expire", expire)
                    .toString();
        }
    }

    public static class AccessLimited implements Serializable {

        /**
         * 单位时间内同一个接口可以访问的次数，默认10次
         */
        private int maxTimes = 10;

        /**
         * 持续时间，即在多长时间内，限制访问多少次。默认为 30秒。
         */
        private Duration expire = Duration.ofSeconds(30);

        public int getMaxTimes() {
            return maxTimes;
        }

        public void setMaxTimes(int maxTimes) {
            this.maxTimes = maxTimes;
        }

        public Duration getExpire() {
            return expire;
        }

        public void setExpire(Duration expire) {
            this.expire = expire;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("maxTimes", maxTimes)
                    .add("expire", expire)
                    .toString();
        }
    }
}
