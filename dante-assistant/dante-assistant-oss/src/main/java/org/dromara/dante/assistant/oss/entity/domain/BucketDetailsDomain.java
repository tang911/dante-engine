/*
 * Copyright 2020-2030 码匠君<herodotus@aliyun.com>
 *
 * Dante Engine licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Dante Engine 是 Dante Cloud 系统核心组件库，采用 APACHE LICENSE 2.0 开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1. 请不要删除和修改根目录下的LICENSE文件。
 * 2. 请不要删除和修改 Dante Engine 源码头部的版权声明。
 * 3. 请保留源码和相关描述文件的项目出处，作者声明等。
 * 4. 分发源码时候，请注明软件出处 <https://gitee.com/dromara/dante-cloud>
 * 5. 在修改包名，模块名称，项目代码等时，请注明软件出处 <https://gitee.com/dromara/dante-cloud>
 * 6. 若您的项目无法满足以上几点，可申请商业授权
 */

package org.dromara.dante.assistant.oss.entity.domain;

import com.google.common.base.MoreObjects;
import org.dromara.dante.assistant.oss.definition.domain.OssDomain;
import org.dromara.dante.assistant.oss.enums.BucketPolicy;
import org.dromara.dante.assistant.oss.enums.BucketVersioning;

import java.time.LocalDateTime;

/**
 * <p>Description: 存储桶详情域对象 </p>
 * <p>
 * 存储桶信息获取会涉及到多个操作，每个操作都会返回不同的信息。定义 {@link BucketDetailsDomain} 用于装配多个操作的返回信息。
 *
 * @author : gengwei.zheng
 * @date : 2026/2/5 22:19
 */
public class BucketDetailsDomain implements OssDomain {

    private String bucketName;

    private LocalDateTime creationDate;
    /**
     * BucketRegion表示存储桶所在的亚马逊网络服务（AWS）区域。如果请求中至少包含一个有效参数，则该参数将包含在响应中。
     */
    private String bucketRegion;

    private BucketPolicy policy;

    private BucketVersioning versioning;

    private Boolean objectLockEnabled;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getBucketRegion() {
        return bucketRegion;
    }

    public void setBucketRegion(String bucketRegion) {
        this.bucketRegion = bucketRegion;
    }

    public BucketPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(BucketPolicy policy) {
        this.policy = policy;
    }

    public BucketVersioning getVersioning() {
        return versioning;
    }

    public void setVersioning(BucketVersioning versioning) {
        this.versioning = versioning;
    }

    public Boolean getObjectLockEnabled() {
        return objectLockEnabled;
    }

    public void setObjectLockEnabled(Boolean objectLockEnabled) {
        this.objectLockEnabled = objectLockEnabled;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bucketName", bucketName)
                .add("creationDate", creationDate)
                .add("bucketRegion", bucketRegion)
                .add("policy", policy)
                .add("versioning", versioning)
                .toString();
    }
}
