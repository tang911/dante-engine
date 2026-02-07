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
import com.google.common.base.MoreObjects;

/**
 * <p>Description: Sse 自定义算法 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 11:33
 */
public class SseCustomerDomain implements OssDomain {

    private String sseCustomerAlgorithm;

    private String sseCustomerKey;

    private String sseCustomerKeyMD5;

    public String getSseCustomerAlgorithm() {
        return sseCustomerAlgorithm;
    }

    public void setSseCustomerAlgorithm(String sseCustomerAlgorithm) {
        this.sseCustomerAlgorithm = sseCustomerAlgorithm;
    }

    public String getSseCustomerKey() {
        return sseCustomerKey;
    }

    public void setSseCustomerKey(String sseCustomerKey) {
        this.sseCustomerKey = sseCustomerKey;
    }

    public String getSseCustomerKeyMD5() {
        return sseCustomerKeyMD5;
    }

    public void setSseCustomerKeyMD5(String sseCustomerKeyMD5) {
        this.sseCustomerKeyMD5 = sseCustomerKeyMD5;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sseCustomerAlgorithm", sseCustomerAlgorithm)
                .add("sseCustomerKey", sseCustomerKey)
                .add("sseCustomerKeyMD5", sseCustomerKeyMD5)
                .toString();
    }
}
