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

package cn.herodotus.engine.captcha.graphic.definition;

import cn.herodotus.engine.assistant.definition.constants.SymbolConstants;
import cn.herodotus.engine.captcha.core.definition.domain.Metadata;
import com.madgag.gif.fmsware.AnimatedGifEncoder;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.codec.binary.Base64;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.stream.IntStream;

/**
 * <p>Description: Gif 类型图形验证码绘制器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/12/21 23:25
 */
public abstract class AbstractGifGraphicRenderer extends AbstractBaseGraphicRenderer {

    @Override
    protected String getBase64ImagePrefix() {
        return BASE64_GIF_IMAGE_PREFIX;
    }

    @Override
    public Metadata draw() {

        String[] drawCharacters = this.getDrawCharacters();

        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        // gif编码类
        AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
        // 生成字符
        gifEncoder.start(out);
        // 设置量化器取样间隔
        gifEncoder.setQuality(180);
        // 帧延迟 (默认100)
        int delay = 100;
        //设置帧延迟
        gifEncoder.setDelay(delay);
        //帧循环次数
        gifEncoder.setRepeat(0);

        IntStream.range(0, drawCharacters.length).forEach(i -> {
            BufferedImage frame = createGifBufferedImage(drawCharacters, i);
            gifEncoder.addFrame(frame);
            frame.flush();
        });

        gifEncoder.finish();

        String characters = StringUtils.join(drawCharacters, SymbolConstants.BLANK);

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(getBase64ImagePrefix() + Base64.encode(out.toByteArray()));
        metadata.setCharacters(characters);
        return metadata;
    }
}
