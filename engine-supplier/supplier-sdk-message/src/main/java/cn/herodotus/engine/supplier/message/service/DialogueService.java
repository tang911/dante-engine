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

package cn.herodotus.engine.supplier.message.service;

import cn.herodotus.engine.data.core.repository.BaseRepository;
import cn.herodotus.engine.data.core.service.BaseService;
import cn.herodotus.engine.supplier.message.entity.Dialogue;
import cn.herodotus.engine.supplier.message.repository.DialogueRepository;
import org.springframework.stereotype.Service;

/**
 * <p>Description: PersonalDialogueService </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/12/7 22:09
 */
@Service
public class DialogueService extends BaseService<Dialogue, String> {

    private final DialogueRepository dialogueRepository;

    public DialogueService(DialogueRepository dialogueRepository) {
        this.dialogueRepository = dialogueRepository;
    }

    @Override
    public BaseRepository<Dialogue, String> getRepository() {
        return dialogueRepository;
    }

    public Dialogue createDialogue(String content) {
        Dialogue dialogue = new Dialogue();
        dialogue.setLatestNews(content);
        return this.save(dialogue);
    }

    public Dialogue updateDialogue(String dialogueId, String content) {
        Dialogue dialogue = this.findById(dialogueId);
        dialogue.setLatestNews(content);
        return this.save(dialogue);
    }
}
