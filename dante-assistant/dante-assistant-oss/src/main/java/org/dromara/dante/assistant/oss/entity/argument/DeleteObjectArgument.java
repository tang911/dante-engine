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

package cn.herodotus.stirrup.assistant.oss.entity.argument;

import cn.herodotus.stirrup.assistant.oss.definition.argument.AbstractObjectVersionArgument;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 删除对象请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 23:37
 */
@Schema(name = "删除对象请求参数实体", title = "删除对象请求参数实体")
public class DeleteObjectArgument extends AbstractObjectVersionArgument {

    private String mfa;

    @Schema(name = "使用治理模式进行删除", description = "治理模式用户不能覆盖或删除对象版本或更改其锁定设置，可通过设置该参数进行强制操作")
    private Boolean bypassGovernanceRetention;

    public Boolean getBypassGovernanceRetention() {
        return bypassGovernanceRetention;
    }

    public void setBypassGovernanceRetention(Boolean bypassGovernanceRetention) {
        this.bypassGovernanceRetention = bypassGovernanceRetention;
    }

    public String getMfa() {
        return mfa;
    }

    public void setMfa(String mfa) {
        this.mfa = mfa;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bypassGovernanceRetention", bypassGovernanceRetention)
                .add("mfa", mfa)
                .toString();
    }
}
