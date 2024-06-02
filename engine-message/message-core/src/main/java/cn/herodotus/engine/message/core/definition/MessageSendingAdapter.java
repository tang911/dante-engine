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

package cn.herodotus.engine.message.core.definition;

import cn.herodotus.engine.message.core.definition.domain.Message;
import cn.herodotus.engine.message.core.definition.event.AbstractApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>Description: 消息发送适配器 </p>
 * <p>
 * 各种类型消息发送组件，基于该接口实现各自的消息发送。
 *
 * @author : gengwei.zheng
 * @date : 2023/10/26 16:46
 */
public interface MessageSendingAdapter<D extends Message, E extends AbstractApplicationEvent<D>> extends ApplicationListener<E> {

}
