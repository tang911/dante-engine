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

package cn.herodotus.engine.rest.core.constants;

import cn.herodotus.engine.assistant.definition.feedback.NotAcceptableFeedback;

/**
 * <p>Description: Cache 相关错误代码 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/5/2 13:25
 */
public interface RestErrorCodes {

    NotAcceptableFeedback SESSION_INVALID = new NotAcceptableFeedback("Session已过期，请刷新再试");
    NotAcceptableFeedback REPEAT_SUBMISSION = new NotAcceptableFeedback("提交进行中，请不要重复提交");
    NotAcceptableFeedback FREQUENT_REQUESTS = new NotAcceptableFeedback("请求频繁，请稍后再试");
    NotAcceptableFeedback FEIGN_DECODER_IO_EXCEPTION = new NotAcceptableFeedback("Feign 解析 Fallback 错误信息出错");

}
