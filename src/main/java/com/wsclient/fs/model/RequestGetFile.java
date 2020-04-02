package com.wsclient.fs.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class RequestGetFile {
    @Min(value = 1,message = "File Id must be greater than 0")
    private long fileId;
    @Pattern(regexp = "KBS|Evrak",message = "Server Type can be permitted values")
    private String serverType;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }
}
