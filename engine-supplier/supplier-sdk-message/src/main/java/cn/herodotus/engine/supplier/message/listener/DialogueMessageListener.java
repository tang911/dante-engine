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

package cn.herodotus.engine.supplier.message.listener;

import cn.herodotus.engine.message.core.logic.domain.DialogueMessage;
import cn.herodotus.engine.message.core.logic.event.SendDialogueMessageEvent;
import cn.herodotus.engine.supplier.message.entity.DialogueDetail;
import cn.herodotus.engine.supplier.message.service.DialogueDetailService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 对话消息监听 </p>
 * <p>
 * 解耦模块之间的依赖关系
 *
 * @author : gengwei.zheng
 * @date : 2023/3/11 18:49
 */
@Component
public class DialogueMessageListener implements ApplicationListener<SendDialogueMessageEvent> {

    private final DialogueDetailService dialogueDetailService;

    public DialogueMessageListener(DialogueDetailService dialogueDetailService) {
        this.dialogueDetailService = dialogueDetailService;
    }

    @Override
    public void onApplicationEvent(SendDialogueMessageEvent event) {
        if (ObjectUtils.isNotEmpty(event)) {
            DialogueMessage dialogueMessage = event.getData();
            if (ObjectUtils.isNotEmpty(dialogueMessage)) {
                DialogueDetail dialogueDetail = convertDialogueMessageToDialogueDetail(dialogueMessage);
                dialogueDetailService.save(dialogueDetail);
            }
        }
    }

    private DialogueDetail convertDialogueMessageToDialogueDetail(DialogueMessage dialogueMessage) {
        DialogueDetail dialogueDetail = new DialogueDetail();
        dialogueDetail.setDetailId(dialogueMessage.getDetailId());
        dialogueDetail.setReceiverId(dialogueMessage.getReceiverId());
        dialogueDetail.setReceiverName(dialogueMessage.getReceiverName());
        dialogueDetail.setReceiverAvatar(dialogueMessage.getReceiverAvatar());
        dialogueDetail.setContent(dialogueMessage.getContent());
        dialogueDetail.setDialogueId(dialogueMessage.getDialogueId());
        dialogueDetail.setSenderId(dialogueMessage.getSenderId());
        dialogueDetail.setSenderName(dialogueMessage.getSenderName());
        dialogueDetail.setSenderAvatar(dialogueMessage.getSenderAvatar());
        return dialogueDetail;
    }
}
