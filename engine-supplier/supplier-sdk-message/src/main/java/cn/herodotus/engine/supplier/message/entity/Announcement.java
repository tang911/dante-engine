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

package cn.herodotus.engine.supplier.message.entity;

import cn.herodotus.engine.message.core.constants.MessageConstants;
import cn.herodotus.engine.supplier.message.domain.BaseSenderEntity;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 站内通知 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/12/6 21:33
 */
@Schema(name = "系统公告")
@Entity
@Table(name = "msg_announcement", indexes = {@Index(name = "msg_announcement_id_idx", columnList = "announcement_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = MessageConstants.REGION_MESSAGE_ANNOUNCEMENT)
public class Announcement extends BaseSenderEntity {

    @Schema(name = "公告ID")
    @Id
    @UuidGenerator
    @Column(name = "announcement_id", length = 64)
    private String announcementId;

    @Schema(name = "公告标题")
    @Column(name = "title", length = 128)
    private String title;

    @Schema(name = "公告内容")
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("announcementId", announcementId)
                .add("title", title)
                .add("content", content)
                .toString();
    }
}
