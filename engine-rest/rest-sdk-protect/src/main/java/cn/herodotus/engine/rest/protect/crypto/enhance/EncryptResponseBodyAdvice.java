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

package cn.herodotus.engine.rest.protect.crypto.enhance;

import cn.herodotus.engine.assistant.core.json.jackson2.utils.Jackson2Utils;
import cn.herodotus.engine.assistant.core.utils.http.SessionUtils;
import cn.herodotus.engine.rest.core.annotation.Crypto;
import cn.herodotus.engine.rest.core.exception.SessionInvalidException;
import cn.herodotus.engine.rest.protect.crypto.processor.HttpCryptoProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>Description: 响应体加密Advice </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/10/4 14:30
 */
@RestControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(EncryptResponseBodyAdvice.class);

    private HttpCryptoProcessor httpCryptoProcessor;

    public void setInterfaceCryptoProcessor(HttpCryptoProcessor httpCryptoProcessor) {
        this.httpCryptoProcessor = httpCryptoProcessor;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {

        String methodName = methodParameter.getMethod().getName();
        Crypto crypto = methodParameter.getMethodAnnotation(Crypto.class);

        boolean isSupports = ObjectUtils.isNotEmpty(crypto) && crypto.responseEncrypt();

        log.trace("[Herodotus] |- Is EncryptResponseBodyAdvice supports method [{}] ? Status is [{}].", methodName, isSupports);
        return isSupports;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        String sessionId = SessionUtils.analyseSessionId(request);
        if (SessionUtils.isCryptoEnabled(request, sessionId)) {

            log.info("[Herodotus] |- EncryptResponseBodyAdvice begin encrypt data.");

            String methodName = methodParameter.getMethod().getName();
            String className = methodParameter.getDeclaringClass().getName();

            try {
                String bodyString = Jackson2Utils.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body);
                String result = httpCryptoProcessor.encrypt(sessionId, bodyString);
                if (StringUtils.isNotBlank(result)) {
                    log.debug("[Herodotus] |- Encrypt response body for rest method [{}] in [{}] finished.", methodName, className);
                    return result;
                } else {
                    return body;
                }
            } catch (JsonProcessingException e) {
                log.debug("[Herodotus] |- Encrypt response body for rest method [{}] in [{}] catch error, skip encrypt operation.", methodName, className, e);
                return body;
            } catch (SessionInvalidException e) {
                log.error("[Herodotus] |- Session is expired for encrypt response body for rest method [{}] in [{}], skip encrypt operation.", methodName, className, e);
                return body;
            }
        } else {
            log.warn("[Herodotus] |- Cannot find Herodotus Cloud custom session header. Use interface crypto function need add X_HERODOTUS_SESSION_ID to request header.");
            return body;
        }
    }
}
