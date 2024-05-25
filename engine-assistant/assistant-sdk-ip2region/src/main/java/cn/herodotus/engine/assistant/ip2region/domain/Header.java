/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Dante Engine.
 *
 * Dante Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dante Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.engine.assistant.ip2region.domain;

import cn.herodotus.engine.assistant.ip2region.searcher.IpV4Searcher;
import com.google.common.base.MoreObjects;

public class Header {

    public final int version;
    public final int indexPolicy;
    public final int createdAt;
    public final int startIndexPtr;
    public final int endIndexPtr;
    public final byte[] buffer;

    public Header(byte[] buff) {
        assert buff.length >= 16;
        version = IpV4Searcher.getInt2(buff, 0);
        indexPolicy = IpV4Searcher.getInt2(buff, 2);
        createdAt = IpV4Searcher.getInt(buff, 4);
        startIndexPtr = IpV4Searcher.getInt(buff, 8);
        endIndexPtr = IpV4Searcher.getInt(buff, 12);
        buffer = buff;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("version", version)
                .add("indexPolicy", indexPolicy)
                .add("createdAt", createdAt)
                .add("startIndexPtr", startIndexPtr)
                .add("endIndexPtr", endIndexPtr)
                .add("buffer", buffer)
                .toString();
    }
}
