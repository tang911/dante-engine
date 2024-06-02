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

package cn.herodotus.engine.rest.protect.crypto.processor;

import cn.herodotus.engine.rest.core.definition.crypto.SymmetricCryptoProcessor;
import org.dromara.hutool.core.codec.binary.HexUtil;
import org.dromara.hutool.crypto.bc.SmUtil;
import org.dromara.hutool.crypto.symmetric.SM4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;

/**
 * <p>Description: 国密对称算法 SM4 处理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/5/2 16:56
 */
public class SM4CryptoProcessor implements SymmetricCryptoProcessor {

    private static final Logger log = LoggerFactory.getLogger(SM4CryptoProcessor.class);

    @Override
    public String createKey() {
        SM4 sm4 = SmUtil.sm4();
        SecretKey secretKey = sm4.getSecretKey();
        byte[] encoded = secretKey.getEncoded();
        String result = HexUtil.encodeStr(encoded);
        log.trace("[Herodotus] |- SM4 crypto create hex key, value is : [{}]", result);
        return result;
    }

    @Override
    public String decrypt(String data, String key) {
        // TODO: 2022-05-08 这里主要有一个诡异问题：大多数情况都没有问题，但是相关代码已放到 DecryptRequestBodyAdvice 里面就无法解密
        SM4 sm4 = SmUtil.sm4(HexUtil.decode(key));
        log.trace("[Herodotus] |- SM4 crypto decrypt data [{}] with key : [{}]", data, key);
        String result = sm4.decryptStr(data);
        log.trace("[Herodotus] |- SM4 crypto decrypt result is : [{}]", result);
        return result;
    }

    @Override
    public String encrypt(String data, String key) {
        SM4 sm4 = SmUtil.sm4(HexUtil.decode(key));
        log.trace("[Herodotus] |- SM4 crypto encrypt data [{}] with key : [{}]", data, key);
        String result = sm4.encryptHex(data);
        log.trace("[Herodotus] |- SM4 crypto encrypt result is : [{}]", result);
        return result;
    }
}
