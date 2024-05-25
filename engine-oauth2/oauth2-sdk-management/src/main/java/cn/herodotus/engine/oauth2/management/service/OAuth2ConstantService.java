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

package cn.herodotus.engine.oauth2.management.service;

import cn.herodotus.engine.assistant.core.enums.Database;
import cn.herodotus.engine.assistant.core.enums.ServerDevice;
import cn.herodotus.engine.oauth2.core.enums.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: OAuth2 常量服务 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/3/17 14:36
 */
@Service
public class OAuth2ConstantService {

    private static final List<Map<String, Object>> APPLICATION_TYPE_ENUM = ApplicationType.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> GRANT_TYPE_ENUM = GrantType.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> SIGNATURE_JWS_ALGORITHM_ENUM = SignatureJwsAlgorithm.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> ALL_JWS_ALGORITHM_ENUM = AllJwsAlgorithm.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> AUTHENTICATION_METHOD_ENUM = AuthenticationMethod.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> PERMISSION_EXPRESSION_ENUM = PermissionExpression.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> DATABASE_ENUM = Database.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> SERVER_DEVICE_ENUM = ServerDevice.getPreprocessedJsonStructure();

    public Map<String, Object> getAllEnums() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("applicationType", APPLICATION_TYPE_ENUM);
        map.put("grantType", GRANT_TYPE_ENUM);
        map.put("signatureJwsAlgorithm", SIGNATURE_JWS_ALGORITHM_ENUM);
        map.put("allJwsAlgorithm", ALL_JWS_ALGORITHM_ENUM);
        map.put("permissionExpression", PERMISSION_EXPRESSION_ENUM);
        map.put("authenticationMethod", AUTHENTICATION_METHOD_ENUM);
        map.put("database", DATABASE_ENUM);
        map.put("serverDevice", SERVER_DEVICE_ENUM);
        return map;
    }
}
