/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Cloud.
 *
 * Herodotus Cloud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Cloud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.vip>.
 */

$.sm2 = {
    createKeyPair: () => {
        return sm2.generateKeyPairHex();
    },

    encrypt: (content, publicKey) => {
        return '04' + sm2.doEncrypt(content, publicKey, this.cipherMode);
    },

    decrypt: (content, privateKey) => {
        let data = content.substring(2).toLocaleLowerCase();
        return sm2.doDecrypt(data, privateKey, this.cipherMode);
    }
}

$.sm4 = {
    encrypt: (content, publicKey) => {
        return sm4.encrypt(content, publicKey);
    },

    decrypt: (content, privateKey) => {
        return sm4.decrypt(content, privateKey);
    }
}

$.security = {
    exchange: (url, sessionId, backendPublicKey) => {
        const pair = $.sm2.createKeyPair();
        const encryptData = $.sm2.encrypt(pair.publicKey, backendPublicKey);

        return new Promise((resolve, reject) => {
            $.http.post(url, {publicKey: encryptData, sessionId: sessionId}, "json")
                .then(result => {
                    console.log("------exchange data------", result)
                    const confidential = result.data;
                    const key = $.sm2.decrypt(confidential, pair.privateKey);
                    console.log("------key------", key)
                    resolve(key);
                })
                .catch(error => {
                    reject(error);
                });
        })
    },

    captcha: (url, sessionId, category) => {
        return new Promise((resolve, reject) => {
            $.http.get(url, {identity:sessionId, category: category})
                .then(result => {
                    let src = result.data.graphicImageBase64;
                    resolve(src)
                })
                .catch(error => {
                    reject(error);
                });
        })
    },

    encryptSignInFormData: function (tank, fighter, missile, symmetric) {
        return {
            encryptTank: $.sm4.encrypt(tank, symmetric),
            encryptFighter: $.sm4.encrypt(fighter, symmetric),
            encryptMissile: $.sm4.encrypt(missile, symmetric)
        };
    }
}