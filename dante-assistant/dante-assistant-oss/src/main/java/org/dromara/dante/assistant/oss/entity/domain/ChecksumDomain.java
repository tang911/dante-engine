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
 * <p>Description: Checksum 通用属性 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 11:24
 */
public class ChecksumDomain implements OssDomain {

    private String checksumCRC32;

    private String checksumCRC32C;

    private String checksumSHA1;

    private String checksumSHA256;

    public String getChecksumCRC32() {
        return checksumCRC32;
    }

    public void setChecksumCRC32(String checksumCRC32) {
        this.checksumCRC32 = checksumCRC32;
    }

    public String getChecksumCRC32C() {
        return checksumCRC32C;
    }

    public void setChecksumCRC32C(String checksumCRC32C) {
        this.checksumCRC32C = checksumCRC32C;
    }

    public String getChecksumSHA1() {
        return checksumSHA1;
    }

    public void setChecksumSHA1(String checksumSHA1) {
        this.checksumSHA1 = checksumSHA1;
    }

    public String getChecksumSHA256() {
        return checksumSHA256;
    }

    public void setChecksumSHA256(String checksumSHA256) {
        this.checksumSHA256 = checksumSHA256;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("checksumCRC32", checksumCRC32)
                .add("checksumCRC32C", checksumCRC32C)
                .add("checksumSHA1", checksumSHA1)
                .add("checksumSHA256", checksumSHA256)
                .toString();
    }
}
