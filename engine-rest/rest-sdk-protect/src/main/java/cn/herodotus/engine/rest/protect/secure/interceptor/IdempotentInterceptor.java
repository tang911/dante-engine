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

package cn.herodotus.engine.rest.protect.secure.interceptor;

import cn.herodotus.engine.rest.core.annotation.Idempotent;
import cn.herodotus.engine.rest.core.definition.AbstractBaseHandlerInterceptor;
import cn.herodotus.engine.rest.core.exception.RepeatSubmissionException;
import cn.herodotus.engine.rest.protect.secure.stamp.IdempotentStampManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.format.DateTimeParseException;

/**
 * <p>Description: 幂等拦截器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/22 15:31
 */
public class IdempotentInterceptor extends AbstractBaseHandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(IdempotentInterceptor.class);

    private static final String IDEMPOTENT_ATTRIBUTE = "Idempotent";

    private IdempotentStampManager idempotentStampManager;

    public void setIdempotentStampManager(IdempotentStampManager idempotentStampManager) {
        this.idempotentStampManager = idempotentStampManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.trace("[Herodotus] |- IdempotentInterceptor preHandle postProcess.");

        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Method method = handlerMethod.getMethod();

        Idempotent idempotent = method.getAnnotation(Idempotent.class);
        if (idempotent != null) {
            // 幂等性校验, 根据缓存中是否存在Token进行校验。
            // 如果缓存中没有Token，通过放行, 同时在缓存中存入Token。
            // 如果缓存中有Token，意味着同一个操作反复操作，认为失败则抛出异常, 并通过统一异常处理返回友好提示

            String key = generateRequestKey(request);
            if (StringUtils.isNotBlank(key)) {
                String token = idempotentStampManager.get(key);
                if (StringUtils.isBlank(token)) {
                    Duration configuredDuration = Duration.ZERO;
                    String annotationExpire = idempotent.expire();
                    if (StringUtils.isNotBlank(annotationExpire)) {
                        try {
                            configuredDuration = Duration.parse(annotationExpire);
                        } catch (DateTimeParseException e) {
                            log.warn("[Herodotus] |- Idempotent duration value is incorrect, on api [{}].", request.getRequestURI());
                        }
                    }

                    if (!configuredDuration.isZero()) {
                        idempotentStampManager.create(key, configuredDuration);
                    } else {
                        idempotentStampManager.create(key);
                    }

                    request.setAttribute(IDEMPOTENT_ATTRIBUTE, key);

                    return true;
                } else {
                    throw new RepeatSubmissionException("Don't Repeat Submission");
                }
            }
        }

        return true;
    }
}
