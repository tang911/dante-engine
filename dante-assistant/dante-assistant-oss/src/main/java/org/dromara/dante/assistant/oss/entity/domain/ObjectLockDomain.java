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

import java.time.LocalDateTime;

/**
 * <p>Description: Object 锁定通用参数 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/23 23:30
 */
public class ObjectLockDomain implements OssDomain {

    private String objectLockMode;

    private LocalDateTime objectLockRetainUntilDate;

    private String objectLockLegalHoldStatus;

    public String getObjectLockLegalHoldStatus() {
        return objectLockLegalHoldStatus;
    }

    public void setObjectLockLegalHoldStatus(String objectLockLegalHoldStatus) {
        this.objectLockLegalHoldStatus = objectLockLegalHoldStatus;
    }

    public String getObjectLockMode() {
        return objectLockMode;
    }

    public void setObjectLockMode(String objectLockMode) {
        this.objectLockMode = objectLockMode;
    }

    public LocalDateTime getObjectLockRetainUntilDate() {
        return objectLockRetainUntilDate;
    }

    public void setObjectLockRetainUntilDate(LocalDateTime objectLockRetainUntilDate) {
        this.objectLockRetainUntilDate = objectLockRetainUntilDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("objectLockLegalHoldStatus", objectLockLegalHoldStatus)
                .add("objectLockMode", objectLockMode)
                .add("objectLockRetainUntilDate", objectLockRetainUntilDate)
                .toString();
    }
}
