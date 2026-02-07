/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Herodotus Stirrup.
 *
 * Herodotus Stirrup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Herodotus Stirrup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package cn.herodotus.stirrup.assistant.oss.entity.argument;

import cn.herodotus.stirrup.assistant.oss.definition.argument.AbstractObjectArgument;
import cn.herodotus.stirrup.assistant.oss.entity.domain.ChecksumDomain;
import cn.herodotus.stirrup.assistant.oss.entity.domain.PutObjectDomain;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 上传对象请求参数实体 </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/22 23:51
 */
@Schema(name = "上传对象请求参数实体", title = "上传对象请求参数实体")
public class PutObjectArgument extends AbstractObjectArgument {

    private String acl;

    private String checksumAlgorithm;

    private String grantFullControl;

    private String grantRead;

    private String grantReadACP;

    private String grantWriteACP;

    private String websiteRedirectLocation;

    private String tagging;

    private ChecksumDomain checksum = new ChecksumDomain();

    private PutObjectDomain metadata = new PutObjectDomain();

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public ChecksumDomain getChecksum() {
        return checksum;
    }

    public void setChecksum(ChecksumDomain checksum) {
        this.checksum = checksum;
    }

    public String getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    public void setChecksumAlgorithm(String checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    public String getGrantFullControl() {
        return grantFullControl;
    }

    public void setGrantFullControl(String grantFullControl) {
        this.grantFullControl = grantFullControl;
    }

    public String getGrantRead() {
        return grantRead;
    }

    public void setGrantRead(String grantRead) {
        this.grantRead = grantRead;
    }

    public String getGrantReadACP() {
        return grantReadACP;
    }

    public void setGrantReadACP(String grantReadACP) {
        this.grantReadACP = grantReadACP;
    }

    public String getGrantWriteACP() {
        return grantWriteACP;
    }

    public void setGrantWriteACP(String grantWriteACP) {
        this.grantWriteACP = grantWriteACP;
    }

    public PutObjectDomain getMetadata() {
        return metadata;
    }

    public void setMetadata(PutObjectDomain metadata) {
        this.metadata = metadata;
    }

    public String getTagging() {
        return tagging;
    }

    public void setTagging(String tagging) {
        this.tagging = tagging;
    }

    public String getWebsiteRedirectLocation() {
        return websiteRedirectLocation;
    }

    public void setWebsiteRedirectLocation(String websiteRedirectLocation) {
        this.websiteRedirectLocation = websiteRedirectLocation;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("acl", acl)
                .add("checksumAlgorithm", checksumAlgorithm)
                .add("grantFullControl", grantFullControl)
                .add("grantRead", grantRead)
                .add("grantReadACP", grantReadACP)
                .add("grantWriteACP", grantWriteACP)
                .add("websiteRedirectLocation", websiteRedirectLocation)
                .add("tagging", tagging)
                .add("checksum", checksum)
                .add("metadata", metadata)
                .toString();
    }
}
