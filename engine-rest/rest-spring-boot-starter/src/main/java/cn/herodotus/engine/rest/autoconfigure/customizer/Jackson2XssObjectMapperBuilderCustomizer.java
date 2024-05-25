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

package cn.herodotus.engine.rest.autoconfigure.customizer;

import cn.herodotus.engine.assistant.autoconfigure.customizer.BaseObjectMapperBuilderCustomizer;
import cn.herodotus.engine.assistant.core.json.jackson2.Jackson2CustomizerOrder;
import cn.herodotus.engine.rest.protect.jackson2.XssStringJsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: Jackson Xss Customizer </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/4/29 16:30
 */
public class Jackson2XssObjectMapperBuilderCustomizer implements BaseObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new XssStringJsonDeserializer());

        builder.modulesToInstall(modules -> {
            List<Module> install = new ArrayList<>(modules);
            install.add(simpleModule);
            builder.modulesToInstall(toArray(install));
        });
    }

    @Override
    public int getOrder() {
        return Jackson2CustomizerOrder.CUSTOMIZER_XSS;
    }
}
