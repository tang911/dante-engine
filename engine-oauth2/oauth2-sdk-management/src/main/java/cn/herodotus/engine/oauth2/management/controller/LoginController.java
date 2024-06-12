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

package cn.herodotus.engine.oauth2.management.controller;

import cn.herodotus.engine.assistant.core.utils.http.SessionUtils;
import cn.herodotus.engine.assistant.definition.domain.oauth2.SecretKey;
import cn.herodotus.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.herodotus.engine.rest.protect.crypto.processor.HttpCryptoProcessor;
import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.codec.binary.Base64;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.http.ResponseCookie;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.util.Map;

/**
 * <p>Description: Security 登录控制器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/3/21 19:52
 * @see org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer
 * @see org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter
 */
@Controller
public class LoginController {

    private final OAuth2AuthenticationProperties authenticationProperties;
    private final SessionProperties sessionProperties;
    private final HttpCryptoProcessor httpCryptoProcessor;

    public LoginController(OAuth2AuthenticationProperties authenticationProperties, SessionProperties sessionProperties, HttpCryptoProcessor httpCryptoProcessor) {
        this.authenticationProperties = authenticationProperties;
        this.sessionProperties = sessionProperties;
        this.httpCryptoProcessor = httpCryptoProcessor;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("login");

        boolean loginError = isErrorPage(request);
        boolean logoutSuccess = isLogoutSuccess(request);
        String errorMessage = getErrorMessage(request);

        // 登录可配置密码参数
        modelAndView.addObject("vulgar_tycoon", getFormLogin().getUsernameParameter());
        modelAndView.addObject("beast", getFormLogin().getPasswordParameter());
        modelAndView.addObject("anubis", getFormLogin().getRememberMeParameter());
        modelAndView.addObject("graphic", getFormLogin().getCaptchaParameter());
        modelAndView.addObject("show_verification_code", getFormLogin().getCaptchaEnabled());
        modelAndView.addObject("forgot_password_url", getFormLogin().getForgotPasswordUrl());
        modelAndView.addObject("registration_ur", getFormLogin().getRegistrationUrl());
        modelAndView.addObject("show_registration_url", getFormLogin().isRegistrationEnabled());
        modelAndView.addObject("show_forgot_password_url", getFormLogin().isForgotPasswordEnabled());
        // 验证码类别
        modelAndView.addObject("verification_category", getFormLogin().getCategory());
        modelAndView.addObject("login_error", loginError);
        modelAndView.addObject("logout_success", logoutSuccess);
        modelAndView.addObject("message", StringUtils.isNotBlank(errorMessage) ? HtmlUtils.htmlEscape(errorMessage) : null);
        modelAndView.addObject("contextPath", request.getContextPath());

        // AES加密key
        processSecretKey(modelAndView, request, response);

        return modelAndView;
    }

    public void processSecretKey(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        String sessionId = SessionUtils.analyseSessionId(request);
        SecretKey secretKey;
        if (StringUtils.isBlank(sessionId)) {
            secretKey = httpCryptoProcessor.createSecretKey(null, sessionProperties.getTimeout());
            sessionId = secretKey.getIdentity();

            ResponseCookie cookie = ResponseCookie.from("SESSION", Base64.encode(secretKey.getIdentity()))
                    .path(request.getContextPath() + "/")
                    .httpOnly(true)
                    .secure("https".equalsIgnoreCase(request.getScheme()))
                    .sameSite("Lax")
                    .maxAge(authenticationProperties.getFormLogin().getCookieMaxAge())
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        } else {
            secretKey = httpCryptoProcessor.createSecretKey(sessionId, sessionProperties.getTimeout());
            sessionId = secretKey.getIdentity();
        }

        // AES加密key
        modelAndView.addObject("soup_spoon", secretKey.getPublicKey());
        modelAndView.addObject("sessionId", sessionId);
    }

    private OAuth2AuthenticationProperties.FormLogin getFormLogin() {
        return authenticationProperties.getFormLogin();
    }

    private boolean isLogoutSuccess(HttpServletRequest request) {
        String logoutSuccessUrl = getFormLogin().getLogoutSuccessUrl();
        return StringUtils.isNotBlank(logoutSuccessUrl) && matches(request, logoutSuccessUrl);
    }

    private boolean isErrorPage(HttpServletRequest request) {
        return matches(request, getFormLogin().getFailureUrl());
    }

    private String getErrorMessage(HttpServletRequest request) {
        HttpSession session = SessionUtils.getSession(request);
        if (ObjectUtils.isNotEmpty(session)) {
            String message = (String) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ObjectUtils.isNotEmpty(message)) {
                return message;
            }
        }

        return null;
    }

    private boolean matches(HttpServletRequest request, String url) {
        if (!"GET".equals(request.getMethod()) || url == null) {
            return false;
        }
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');
        if (pathParamIndex > 0) {
            // strip everything after the first semi-colon
            uri = uri.substring(0, pathParamIndex);
        }
        if (request.getQueryString() != null) {
            uri += "?" + request.getQueryString();
        }
        if ("".equals(request.getContextPath())) {
            return uri.equals(url);
        }
        return uri.equals(request.getContextPath() + url);
    }
}
