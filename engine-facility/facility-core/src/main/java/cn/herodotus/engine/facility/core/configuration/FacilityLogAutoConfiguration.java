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

package cn.herodotus.engine.facility.core.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import cn.herodotus.engine.assistant.core.json.jackson2.utils.Jackson2Utils;
import cn.herodotus.engine.facility.core.constants.FacilityConstants;
import cn.herodotus.engine.facility.core.annotation.ConditionalOnLogEnabled;
import cn.herodotus.engine.facility.core.properties.LogProperties;
import com.google.common.base.MoreObjects;
import jakarta.annotation.PostConstruct;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.logstash.TraceIdJsonProvider;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.logging.LogLevel;

import java.util.Map;

/**
 * <p>Description: 基础设置日志配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/2/5 19:01
 */
@AutoConfiguration
@ConditionalOnLogEnabled
@EnableConfigurationProperties({LogProperties.class})
public class FacilityLogAutoConfiguration {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FacilityLogAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Facility Log] Auto Configure.");
    }


    @Value(FacilityConstants.ANNOTATION_APPLICATION_NAME)
    private String serviceName;

    @Autowired
    private LogProperties logProperties;

    @PostConstruct
    public void init() {

        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LoggerContext loggerContext = rootLogger.getLoggerContext();

        LogstashTcpSocketAppender logstashTcpSocketAppender = new LogstashTcpSocketAppender();
        logstashTcpSocketAppender.setName("LOGSTASH");
        logstashTcpSocketAppender.addDestination(logProperties.getServerAddr());
        logstashTcpSocketAppender.setKeepAliveDuration(logProperties.getKeepAliveDuration());
        logstashTcpSocketAppender.setReconnectionDelay(logProperties.getReconnectionDelay());
        logstashTcpSocketAppender.setWriteTimeout(logProperties.getWriteTimeout());
        logstashTcpSocketAppender.setContext(loggerContext);

        TraceIdJsonProvider traceIdJsonProvider = new TraceIdJsonProvider();
        traceIdJsonProvider.setContext(loggerContext);

        CustomFields customFields = new CustomFields();
        customFields.setService(serviceName);

        LogstashEncoder logstashEncoder = new LogstashEncoder();
        logstashEncoder.setCustomFields(Jackson2Utils.toJson(customFields));
        logstashEncoder.addProvider(traceIdJsonProvider);

        logstashTcpSocketAppender.setEncoder(logstashEncoder);
        logstashTcpSocketAppender.start();

        rootLogger.addAppender(logstashTcpSocketAppender);
        rootLogger.setLevel(Level.toLevel(logProperties.getLogLevel().name(), Level.INFO));

        Map<String, LogLevel> loggers = logProperties.getLoggers();
        loggers.forEach((key, value) -> {
            Logger logger = (Logger) LoggerFactory.getLogger(key);
            logger.setLevel(Level.toLevel(value.name()));
        });
    }

    private static class CustomFields {

        private String service;

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("service", service)
                    .toString();
        }
    }
}
