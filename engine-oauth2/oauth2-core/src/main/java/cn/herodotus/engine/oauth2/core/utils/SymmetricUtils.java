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

package cn.herodotus.engine.oauth2.core.utils;

import cn.herodotus.engine.assistant.definition.constants.SymbolConstants;
import cn.herodotus.engine.oauth2.core.exception.IllegalSymmetricKeyException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.util.RandomUtil;
import org.dromara.hutool.crypto.SecureUtil;
import org.dromara.hutool.crypto.symmetric.AES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Description : 基于Hutool的Aes加解密工具 </p>
 *
 * @author : gengwei.zheng
 * @date : 2020/1/28 17:25
 */
public class SymmetricUtils {

    private static final Logger log = LoggerFactory.getLogger(SymmetricUtils.class);

    private static String encryptedRealSecretKey(String symmetricKey) {
        String realSecretKey = RandomUtil.randomString(16);
        log.trace("[Herodotus] |- Generate Random Secret Key is : [{}]", realSecretKey);

        AES ase = SecureUtil.aes(symmetricKey.getBytes());
        String encryptedRealSecretKey = ase.encryptHex(realSecretKey);
        log.trace("[Herodotus] |- Generate Encrypt Hex Secret Key is : [{}]", encryptedRealSecretKey);

        return encryptedRealSecretKey;
    }

    public static String getEncryptedSymmetricKey() {
        String symmetricKey = RandomUtil.randomString(16);
        String realSecretKey = encryptedRealSecretKey(symmetricKey);
        log.trace("[Herodotus] |- Generate Symmetric Key is : [{}]", realSecretKey);

        return symmetricKey +
                SymbolConstants.FORWARD_SLASH +
                realSecretKey;
    }

    public static byte[] getDecryptedSymmetricKey(String key) {
        if (!StringUtils.contains(key, SymbolConstants.FORWARD_SLASH)) {
            throw new IllegalSymmetricKeyException("Parameter Illegal!");
        }

        String[] keys = StringUtils.split(key, SymbolConstants.FORWARD_SLASH);
        String symmetricKey = keys[0];
        String realSecretKey = keys[1];

        AES ase = SecureUtil.aes(symmetricKey.getBytes());
        return ase.decrypt(realSecretKey);
    }

    public static String decrypt(String content, byte[] key) {
        if (ArrayUtils.isNotEmpty(key)) {
            AES ase = SecureUtil.aes(key);
            return ase.decryptStr(content);
        }

        return "";
    }
}
