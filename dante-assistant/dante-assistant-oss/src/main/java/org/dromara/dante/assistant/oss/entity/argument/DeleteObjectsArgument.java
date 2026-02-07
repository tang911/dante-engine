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

import cn.herodotus.stirrup.assistant.oss.definition.argument.AbstractObjectRequestPayerArgument;
import cn.herodotus.stirrup.assistant.oss.entity.domain.DeletedDomain;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * <p>Description: 批量删除对象请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/26 0:38
 */
@Schema(name = "批量删除对象请求参数实体", title = "批量删除对象请求参数实体")
public class DeleteObjectsArgument extends AbstractObjectRequestPayerArgument {

    private List<DeletedDomain> delete;

    private Boolean quiet;

    private String mfa;

    @Schema(name = "使用治理模式进行删除", description = "治理模式用户不能覆盖或删除对象版本或更改其锁定设置，可通过设置该参数进行强制操作")
    private Boolean bypassGovernanceRetention;

    private String checksumAlgorithm;

    public Boolean getBypassGovernanceRetention() {
        return bypassGovernanceRetention;
    }

    public void setBypassGovernanceRetention(Boolean bypassGovernanceRetention) {
        this.bypassGovernanceRetention = bypassGovernanceRetention;
    }

    public String getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    public void setChecksumAlgorithm(String checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    public List<DeletedDomain> getDelete() {
        return delete;
    }

    public void setDelete(List<DeletedDomain> delete) {
        this.delete = delete;
    }

    public String getMfa() {
        return mfa;
    }

    public void setMfa(String mfa) {
        this.mfa = mfa;
    }

    public Boolean getQuiet() {
        return quiet;
    }

    public void setQuiet(Boolean quiet) {
        this.quiet = quiet;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bypassGovernanceRetention", bypassGovernanceRetention)
                .add("delete", delete)
                .add("quiet", quiet)
                .add("mfa", mfa)
                .add("checksumAlgorithm", checksumAlgorithm)
                .toString();
    }
}
