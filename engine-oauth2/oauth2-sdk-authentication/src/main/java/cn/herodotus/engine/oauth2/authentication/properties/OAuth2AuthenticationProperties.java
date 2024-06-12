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

package cn.herodotus.engine.oauth2.authentication.properties;

import cn.herodotus.engine.assistant.definition.constants.SymbolConstants;
import cn.herodotus.engine.oauth2.core.constants.OAuth2Constants;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import java.time.Duration;

/**
 * <p>Description: OAuth2 合规性配置参数 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/7 0:16
 */
@ConfigurationProperties(prefix = OAuth2Constants.PROPERTY_OAUTH2_AUTHENTICATION)
public class OAuth2AuthenticationProperties {

    /**
     * 开启登录失败限制
     */
    private SignInFailureLimited signInFailureLimited = new SignInFailureLimited();

    /**
     * 同一终端登录限制
     */
    private SignInEndpointLimited signInEndpointLimited = new SignInEndpointLimited();

    /**
     * 账户踢出限制
     */
    private SignInKickOutLimited signInKickOutLimited = new SignInKickOutLimited();

    private FormLogin formLogin = new FormLogin();

    public SignInEndpointLimited getSignInEndpointLimited() {
        return signInEndpointLimited;
    }

    public void setSignInEndpointLimited(SignInEndpointLimited signInEndpointLimited) {
        this.signInEndpointLimited = signInEndpointLimited;
    }

    public SignInFailureLimited getSignInFailureLimited() {
        return signInFailureLimited;
    }

    public void setSignInFailureLimited(SignInFailureLimited signInFailureLimited) {
        this.signInFailureLimited = signInFailureLimited;
    }

    public SignInKickOutLimited getSignInKickOutLimited() {
        return signInKickOutLimited;
    }

    public void setSignInKickOutLimited(SignInKickOutLimited signInKickOutLimited) {
        this.signInKickOutLimited = signInKickOutLimited;
    }

    public FormLogin getFormLogin() {
        return formLogin;
    }

    public void setFormLogin(FormLogin formLogin) {
        this.formLogin = formLogin;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("signInEndpointLimited", signInEndpointLimited)
                .add("signInFailureLimited", signInFailureLimited)
                .add("signInKickOutLimited", signInKickOutLimited)
                .toString();
    }

    public static class SignInFailureLimited {
        /**
         * 是否开启登录失败检测，默认开启
         */
        private Boolean enabled = true;

        /**
         * 允许允许最大失败次数
         */
        private Integer maxTimes = 5;

        /**
         * 是否自动解锁被锁定用户，默认开启
         */
        private Boolean autoUnlock = true;

        /**
         * 记录失败次数的缓存过期时间，默认：2小时。
         */
        private Duration expire = Duration.ofHours(2);

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getMaxTimes() {
            return maxTimes;
        }

        public void setMaxTimes(Integer maxTimes) {
            this.maxTimes = maxTimes;
        }

        public Duration getExpire() {
            return expire;
        }

        public void setExpire(Duration expire) {
            this.expire = expire;
        }

        public Boolean getAutoUnlock() {
            return autoUnlock;
        }

        public void setAutoUnlock(Boolean autoUnlock) {
            this.autoUnlock = autoUnlock;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("enabled", enabled)
                    .add("maxTimes", maxTimes)
                    .add("autoUnlock", autoUnlock)
                    .add("expire", expire)
                    .toString();
        }
    }

    public static class SignInEndpointLimited {
        /**
         * 同一终端登录限制是否开启，默认开启。
         */
        private Boolean enabled = false;

        /**
         * 统一终端，允许同时登录的最大数量
         */
        private Integer maximum = 1;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getMaximum() {
            return maximum;
        }

        public void setMaximum(Integer maximum) {
            this.maximum = maximum;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("enabled", enabled)
                    .add("maximum", maximum)
                    .toString();
        }
    }

    public static class SignInKickOutLimited {
        /**
         * 是否开启 Session 踢出功能，默认开启
         */
        private Boolean enabled = true;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("enabled", enabled)
                    .toString();
        }
    }

    public static class FormLogin {
        /**
         * UI 界面用户名标输入框 name 属性值
         */
        private String usernameParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
        /**
         * UI 界面密码标输入框 name 属性值
         */
        private String passwordParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
        /**
         * UI 界面Remember Me name 属性值
         */
        private String rememberMeParameter = AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY;
        /**
         * UI 界面验证码 name 属性值
         */
        private String captchaParameter = "captcha";
        /**
         * 登录页面地址
         */
        private String loginPageUrl = DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL;
        /**
         * 登录失败重定向地址
         */
        private String failureUrl = loginPageUrl + SymbolConstants.QUESTION + DefaultLoginPageGeneratingFilter.ERROR_PARAMETER_NAME;
        /**
         * 表单登录认证地址
         */
        private String authenticationUrl;
        /**
         * 注销成功地址
         */
        private String logoutSuccessUrl = DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL + "?logout";
        /**
         * 自定义用户注册页面地址
         */
        private String registrationUrl;
        /**
         * 自定义忘记密码页面地址
         */
        private String forgotPasswordUrl;

        /**
         * Cookie 有效期，默认：30天
         */
        private Duration cookieMaxAge = Duration.ofDays(30);
        /**
         * 验证码是否开启，默认 true，显示
         */
        private Boolean captchaEnabled = true;
        /**
         * 验证码类别，默认为 Hutool Gif 类型
         */
        private String category = "HUTOOL_GIF";

        public String getAuthenticationUrl() {
            return authenticationUrl;
        }

        public void setAuthenticationUrl(String authenticationUrl) {
            this.authenticationUrl = authenticationUrl;
        }

        public Boolean getCaptchaEnabled() {
            return captchaEnabled;
        }

        public void setCaptchaEnabled(Boolean captchaEnabled) {
            this.captchaEnabled = captchaEnabled;
        }

        public String getCaptchaParameter() {
            return captchaParameter;
        }

        public void setCaptchaParameter(String captchaParameter) {
            this.captchaParameter = captchaParameter;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Duration getCookieMaxAge() {
            return cookieMaxAge;
        }

        public void setCookieMaxAge(Duration cookieMaxAge) {
            this.cookieMaxAge = cookieMaxAge;
        }

        public String getFailureUrl() {
            return failureUrl;
        }

        public void setFailureUrl(String failureUrl) {
            this.failureUrl = failureUrl;
        }

        public String getForgotPasswordUrl() {
            return forgotPasswordUrl;
        }

        public void setForgotPasswordUrl(String forgotPasswordUrl) {
            this.forgotPasswordUrl = forgotPasswordUrl;
        }

        public String getLoginPageUrl() {
            return loginPageUrl;
        }

        public void setLoginPageUrl(String loginPageUrl) {
            this.loginPageUrl = loginPageUrl;
        }

        public String getLogoutSuccessUrl() {
            return logoutSuccessUrl;
        }

        public void setLogoutSuccessUrl(String logoutSuccessUrl) {
            this.logoutSuccessUrl = logoutSuccessUrl;
        }

        public String getPasswordParameter() {
            return passwordParameter;
        }

        public void setPasswordParameter(String passwordParameter) {
            this.passwordParameter = passwordParameter;
        }

        public String getRegistrationUrl() {
            return registrationUrl;
        }

        public void setRegistrationUrl(String registrationUrl) {
            this.registrationUrl = registrationUrl;
        }

        public String getRememberMeParameter() {
            return rememberMeParameter;
        }

        public void setRememberMeParameter(String rememberMeParameter) {
            this.rememberMeParameter = rememberMeParameter;
        }

        public String getUsernameParameter() {
            return usernameParameter;
        }

        public void setUsernameParameter(String usernameParameter) {
            this.usernameParameter = usernameParameter;
        }

        public Boolean isRegistrationEnabled() {
            return StringUtils.isNotBlank(registrationUrl);
        }

        public Boolean isForgotPasswordEnabled() {
            return StringUtils.isNotBlank(forgotPasswordUrl);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("authenticationUrl", authenticationUrl)
                    .add("usernameParameter", usernameParameter)
                    .add("passwordParameter", passwordParameter)
                    .add("rememberMeParameter", rememberMeParameter)
                    .add("captchaParameter", captchaParameter)
                    .add("loginPageUrl", loginPageUrl)
                    .add("failureUrl", failureUrl)
                    .add("logoutSuccessUrl", logoutSuccessUrl)
                    .add("registrationUrl", registrationUrl)
                    .add("forgotPasswordUrl", forgotPasswordUrl)
                    .add("cookieMaxAge", cookieMaxAge)
                    .add("captchaEnabled", captchaEnabled)
                    .add("category", category)
                    .toString();
        }
    }
}
