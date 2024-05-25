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

package cn.herodotus.engine.assistant.definition.domain.view.datatables;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * <p>Description: 返回给JQuery Datatables 组件使用的结果 </p>
 *
 * @author : gengwei.zheng
 * @date : 2019/11/24 15:48
 */
public class DataTableResult implements Serializable {

    private int pageNumber;
    private int pageSize;
    private String sEcho;
    private int iDisplayStart;
    private int iDisplayLength;
    private String jsonString;
    private long total;

    public DataTableResult(String sEcho, int iDisplayStart, int iDisplayLength, String jsonString) {
        this.sEcho = sEcho;
        this.iDisplayStart = iDisplayStart;
        this.iDisplayLength = iDisplayLength;
        this.pageNumber = this.iDisplayStart / this.iDisplayLength;
        this.pageSize = this.iDisplayLength;
        this.jsonString = jsonString;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pageNumber", pageNumber)
                .add("pageSize", pageSize)
                .add("sEcho", sEcho)
                .add("iDisplayStart", iDisplayStart)
                .add("iDisplayLength", iDisplayLength)
                .add("jsonString", jsonString)
                .add("total", total)
                .toString();
    }
}
