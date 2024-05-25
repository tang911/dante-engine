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

package cn.herodotus.engine.captcha.core.definition;

import cn.herodotus.engine.cache.jetcache.stamp.AbstractStampManager;
import cn.herodotus.engine.captcha.core.properties.CaptchaProperties;
import cn.herodotus.engine.captcha.core.provider.ResourceProvider;
import com.alicp.jetcache.anno.CacheType;
import org.dromara.hutool.swing.img.ImgUtil;

import java.awt.image.BufferedImage;
import java.time.Duration;

/**
 * <p>Description: 基础绘制器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/12/21 21:46
 */
public abstract class AbstractRenderer<K, V> extends AbstractStampManager<K, V> implements Renderer {

    protected static final String BASE64_PNG_IMAGE_PREFIX = "data:image/png;base64,";
    protected static final String BASE64_GIF_IMAGE_PREFIX = "data:image/gif;base64,";

    private ResourceProvider resourceProvider;

    public AbstractRenderer(String cacheName) {
        super(cacheName);
    }

    public AbstractRenderer(String cacheName, CacheType cacheType) {
        super(cacheName, cacheType);
    }

    public AbstractRenderer(String cacheName, CacheType cacheType, Duration expire) {
        super(cacheName, cacheType, expire);
    }

    public ResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    public void setResourceProvider(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    protected CaptchaProperties getCaptchaProperties() {
        return getResourceProvider().getCaptchaProperties();
    }

    protected String getBase64ImagePrefix() {
        return BASE64_PNG_IMAGE_PREFIX;
    }

    protected String toBase64(BufferedImage bufferedImage) {
        String image = ImgUtil.toBase64(bufferedImage, ImgUtil.IMAGE_TYPE_PNG);
        return getBase64ImagePrefix() + image;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
