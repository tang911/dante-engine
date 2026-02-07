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

package cn.herodotus.stirrup.assistant.oss.properties;

import cn.herodotus.stirrup.assistant.oss.constant.OssConstants;
import cn.herodotus.stirrup.core.domain.Pool;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * <p>Description: 对象存储配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/21 9:55
 */
@ConfigurationProperties(prefix = OssConstants.PROPERTY_ASSISTANT_OSS)
public class OssProperties {

    /**
     * Oss Server endpoint
     */
    private String endpoint;

    /**
     * Oss Server accessKey
     */
    private String accessKey;

    /**
     * Oss Server secretKey
     */
    private String secretKey;

    /**
     * Oss Server region
     */
    private String region;

    /**
     * 对象存储预签名URL有效期，默认：5分钟
     */
    private Duration preSignedExpires = Duration.ofMinutes(5);

    /**
     * 自定义 OSS 对象池参数配置
     */
    private Pool pool = new Pool();

    /**
     * 预签名地址内部代理配置
     */
    private Proxy proxy = new Proxy();

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Duration getPreSignedExpires() {
        return preSignedExpires;
    }

    public void setPreSignedExpires(Duration preSignedExpires) {
        this.preSignedExpires = preSignedExpires;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public static class Proxy {
        /**
         * 是否开启。默认开水器
         */
        private Boolean enabled = true;

        /**
         * 代理请求转发源地址。例如：前端 <a href="http://localhost:3000">...</a>。注意如果有前端有配置代理需要加上
         */
        private String source;

        /**
         * 代理请求转发目的地址
         */
        private String destination;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("destination", destination)
                    .add("enabled", enabled)
                    .add("source", source)
                    .toString();
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("endpoint", endpoint)
                .add("accessKey", accessKey)
                .add("secretKey", secretKey)
                .add("region", region)
                .add("preSignedExpires", preSignedExpires)
                .add("pool", pool)
                .add("proxy", proxy)
                .toString();
    }
}
