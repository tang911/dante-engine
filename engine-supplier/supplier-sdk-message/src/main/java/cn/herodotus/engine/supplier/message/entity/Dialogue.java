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

package cn.herodotus.engine.supplier.message.entity;

import cn.herodotus.engine.data.core.entity.BaseEntity;
import cn.herodotus.engine.message.core.constants.MessageConstants;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 私信对话 </p>
 * <p>
 * 本质是一张冗余表，作为中间桥梁连接私信联系和私信对话详情。同时保存对话的最新一条信息，方便展示。
 *
 * @author : gengwei.zheng
 * @date : 2022/12/7 11:01
 */
@Schema(name = "私信对话")
@Entity
@Table(name = "msg_dialogue", indexes = {@Index(name = "msg_dialogue_id_idx", columnList = "dialogue_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = MessageConstants.REGION_MESSAGE_DIALOGUE)
public class Dialogue extends BaseEntity {

    @Schema(name = "对话ID")
    @Id
    @UuidGenerator
    @Column(name = "dialogue_id", length = 64)
    private String dialogueId;

    @Schema(name = "最新内容")
    @Column(name = "latest_news", columnDefinition = "TEXT")
    private String latestNews;

    public String getDialogueId() {
        return dialogueId;
    }

    public void setDialogueId(String dialogueId) {
        this.dialogueId = dialogueId;
    }

    public String getLatestNews() {
        return latestNews;
    }

    public void setLatestNews(String latestNews) {
        this.latestNews = latestNews;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dialogueId", dialogueId)
                .add("latestNews", latestNews)
                .toString();
    }
}
