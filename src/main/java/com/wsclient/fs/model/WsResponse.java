package com.wsclient.fs.model;

public class WsResponse {
    private byte[] blobData;
    protected String fileName;

    public WsResponse(byte[] blobData, String fileName) {
        this.blobData = blobData;
        this.fileName = fileName;
    }

    public byte[] getBlobData() {
        return blobData;
    }

    public void setBlobData(byte[] blobData) {
        this.blobData = blobData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
