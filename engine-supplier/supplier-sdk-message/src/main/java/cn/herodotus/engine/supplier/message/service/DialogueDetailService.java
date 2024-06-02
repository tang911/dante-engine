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
import cn.herodotus.engine.supplier.message.enums.NotificationCategory;
import cn.herodotus.engine.supplier.message.entity.Dialogue;
import cn.herodotus.engine.supplier.message.entity.DialogueContact;
import cn.herodotus.engine.supplier.message.entity.DialogueDetail;
import cn.herodotus.engine.supplier.message.entity.Notification;
import cn.herodotus.engine.supplier.message.repository.DialogueDetailRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: PersonalDialogueDetailService </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/12/7 22:10
 */
@Service
public class DialogueDetailService extends BaseService<DialogueDetail, String> {

    private final DialogueDetailRepository dialogueDetailRepository;
    private final DialogueContactService dialogueContactService;
    private final DialogueService dialogueService;
    private final NotificationService notificationService;

    public DialogueDetailService(DialogueDetailRepository dialogueDetailRepository, DialogueContactService dialogueContactService, DialogueService dialogueService, NotificationService notificationService) {
        this.dialogueDetailRepository = dialogueDetailRepository;
        this.dialogueContactService = dialogueContactService;
        this.dialogueService = dialogueService;
        this.notificationService = notificationService;
    }

    @Override
    public BaseRepository<DialogueDetail, String> getRepository() {
        return dialogueDetailRepository;
    }

    private Notification convertDialogueDetailToNotification(DialogueDetail dialogueDetail) {
        Notification notification = new Notification();
        notification.setUserId(dialogueDetail.getReceiverId());
        notification.setContent(dialogueDetail.getContent());
        notification.setSenderId(dialogueDetail.getSenderId());
        notification.setSenderName(dialogueDetail.getSenderName());
        notification.setSenderAvatar(dialogueDetail.getSenderAvatar());
        notification.setCategory(NotificationCategory.DIALOGUE);
        return notification;
    }

    /**
     * 借鉴 Gitee 的私信设计
     * 1. 每个人都可以查看与自己有过私信往来的用户列表。自己可以查看与自己有过联系的人，对方也可以查看与自己有过联系的人
     * 2. 私信往来用户列表中，显示最新一条对话的内容
     * 3. 点开某一个用户，可以查看具体的对话详情。自己和私信对话用户看到的内容一致。
     * <p>
     * PersonalContact 存储私信双方的关系，存储两条。以及和对话的关联
     * PersonalDialogue 是一个桥梁连接 PersonalContact 和 PersonalDialogueDetail，同时存储一份最新对话副本
     * <p>
     * 本处的逻辑：
     * 发送私信时，首先要判断是否已经创建了 Dialogue
     * 1. 如果没有创建 Dialogue，就是私信双方第一对话，那么要先创建 Dialogue，同时要建立私信双方的联系 Contact。保存的私信与将生成好的 DialogueId进行关联。
     * 2. 如果已经有Dialogue，那么就直接保存私信对话，同时更新 Dialogue 中的最新信息。
     *
     * @param domain 数据对应实体
     * @return {@link DialogueDetail}
     */
    @Transactional
    @Override
    public DialogueDetail save(DialogueDetail domain) {

        if (StringUtils.isBlank(domain.getDialogueId())) {
            DialogueContact dialogueContact = dialogueContactService.findBySenderIdAndReceiverId(domain.getSenderId(), domain.getReceiverId());
            if (ObjectUtils.isNotEmpty(dialogueContact) && ObjectUtils.isNotEmpty(dialogueContact.getDialogue())) {
                String dialogueId = dialogueContact.getDialogue().getDialogueId();
                domain.setDialogueId(dialogueId);
                dialogueService.updateDialogue(dialogueId, domain.getContent());
            } else {
                Dialogue dialogue = dialogueService.createDialogue(domain.getContent());
                domain.setDialogueId(dialogue.getDialogueId());
                dialogueContactService.createContact(dialogue, domain);
            }
        } else {
            dialogueService.updateDialogue(domain.getDialogueId(), domain.getContent());
        }

        notificationService.save(convertDialogueDetailToNotification(domain));

        return super.save(domain);
    }

    @Transactional
    public void deleteDialogueById(String dialogueId) {
        dialogueContactService.deleteByDialogueId(dialogueId);
        dialogueService.deleteById(dialogueId);
        dialogueDetailRepository.deleteAllByDialogueId(dialogueId);
    }

    public Page<DialogueDetail> findByCondition(int pageNumber, int pageSize, String dialogueId) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Specification<DialogueDetail> specification = (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("dialogueId"), dialogueId));

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(predicateArray)));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime")));
            return criteriaQuery.getRestriction();
        };

        return this.findByPage(specification, pageable);
    }
}
