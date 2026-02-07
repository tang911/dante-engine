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

package cn.herodotus.stirrup.assistant.oss.definition.argument;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 对象操作需要支付请求参数定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 23:26
 */
public class AbstractObjectRequestPayerArgument extends AbstractExpectedBucketOwnerArgument {

    @Schema(name = "需要支付")
    private String requestPayer;

    public String getRequestPayer() {
        return requestPayer;
    }

    public void setRequestPayer(String requestPayer) {
        this.requestPayer = requestPayer;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("requestPayer", requestPayer)
                .addValue(super.toString())
                .toString();
    }
}
