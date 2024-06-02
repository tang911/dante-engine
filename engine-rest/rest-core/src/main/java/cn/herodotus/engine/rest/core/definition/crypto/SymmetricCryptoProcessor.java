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

package cn.herodotus.engine.rest.core.definition.crypto;

/**
 * <p>Description: 对称加密算法 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/5/1 15:05
 */
public interface SymmetricCryptoProcessor {

    /**
     * 创建 SM4 Key。可以为 16 进制串或字节数组，要求为 128 比特
     *
     * @return SM4 Key
     */
    String createKey();

    /**
     * 用私钥解密
     *
     * @param key  对称算法 秘钥
     * @param data 待解密数据
     * @return 解密后的数据
     */
    String decrypt(String data, String key);

    /**
     * 用公钥加密
     *
     * @param key  对称算法 秘钥
     * @param data 待加密数据
     * @return 加密后的数据
     */
    String encrypt(String data, String key);
}
