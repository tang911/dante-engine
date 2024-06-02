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

package cn.herodotus.engine.captcha.core.dto;

import cn.herodotus.engine.captcha.core.definition.domain.Coordinate;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * <p>Description: 验证数据实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/12/14 16:04
 */
public class Verification extends Captcha {

    /**
     * 滑块拼图验证参数
     */
    private Coordinate coordinate;
    /**
     * 文字点选验证参数
     */
    private List<Coordinate> coordinates;
    /**
     * 图形验证码验证参数
     */
    private String characters;

    public Verification() {
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Verification that = (Verification) o;
        return Objects.equal(characters, that.characters);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(characters);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("characters", characters)
                .toString();
    }
}
