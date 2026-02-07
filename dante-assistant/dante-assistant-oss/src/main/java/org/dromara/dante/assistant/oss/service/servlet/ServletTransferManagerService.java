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

package cn.herodotus.stirrup.assistant.oss.service.servlet;

import cn.herodotus.stirrup.assistant.oss.converter.argument.*;
import cn.herodotus.stirrup.assistant.oss.converter.result.CompletedDirectoryUploadToResultConverter;
import cn.herodotus.stirrup.assistant.oss.converter.result.CompletedFileDownloadToResultConverter;
import cn.herodotus.stirrup.assistant.oss.converter.result.CompletedFileUploadToResultConverter;
import cn.herodotus.stirrup.assistant.oss.converter.result.CompletedUploadToResultConverter;
import cn.herodotus.stirrup.assistant.oss.definition.service.AbstractServletService;
import cn.herodotus.stirrup.assistant.oss.entity.argument.*;
import cn.herodotus.stirrup.assistant.oss.entity.result.FailedFileUploadResult;
import cn.herodotus.stirrup.assistant.oss.entity.result.GetObjectResult;
import cn.herodotus.stirrup.assistant.oss.entity.result.PutObjectResult;
import cn.herodotus.stirrup.assistant.oss.service.base.S3TransferManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: AWS SDK V2 高阶操作阻塞式 Service </p>
 *
 * @author : gengwei.zheng
 * @date : 2024/7/29 20:54
 */
@Service
public class ServletTransferManagerService extends AbstractServletService {

    private final S3TransferManagerService transferManagerService;

    public ServletTransferManagerService(S3TransferManagerService transferManagerService) {
        this.transferManagerService = transferManagerService;
    }

    public GetObjectResult downloadFile(DownloadFileArgument argument) {
        return process(argument, new ArgumentToDownloadFileRequestConverter(), new CompletedFileDownloadToResultConverter(), transferManagerService::downloadFile);
    }

    public GetObjectResult resumeDownloadFile(ResumableFileDownloadArgument argument) {
        return process(argument, new ArgumentToResumableFileDownloadConverter(), new CompletedFileDownloadToResultConverter(), transferManagerService::resumeDownloadFile);
    }

    public PutObjectResult uploadFile(UploadFileArgument argument) {
        return process(argument, new ArgumentToUploadFileRequestConverter(), new CompletedFileUploadToResultConverter(), transferManagerService::uploadFile);
    }

    public PutObjectResult resumeUploadFile(ResumableFileUploadArgument argument) {
        return process(argument, new ArgumentToResumableFileUploadConverter(), new CompletedFileUploadToResultConverter(), transferManagerService::resumeUploadFile);
    }

    public PutObjectResult upload(UploadArgument argument) {
        return process(argument, new ArgumentToUploadRequestConverter(), new CompletedUploadToResultConverter(), transferManagerService::upload);
    }

    public List<FailedFileUploadResult> uploadDirectory(UploadDirectoryArgument argument) {
        return process(argument, new ArgumentToUploadDirectoryRequestConverter(), new CompletedDirectoryUploadToResultConverter(), transferManagerService::uploadDirectory);
    }
}
