package com.wsclient.fs.service;


import com.wsclient.fs.config.WsClientConfig;
import com.wsclient.fs.generetad.EnumFileServerType;
import com.wsclient.fs.generetad.GetFile;
import com.wsclient.fs.generetad.GetFileResponse;
import com.wsclient.fs.model.RequestGetFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class WsClientService {
    @Autowired
    private WsClientConfig template;

    public GetFileResponse getFileFromFileServer(RequestGetFile request) {
        GetFile wsRequest = new GetFile();
        wsRequest.setFileId(request.getFileId());
        wsRequest.setServerType(EnumFileServerType.valueOf(request.getServerType()));
        wsRequest.setGetData(true);
        GetFileResponse response =
                (GetFileResponse) template.getWebServiceTemplate()
                        .marshalSendAndReceive(wsRequest,template);

//		writeBytesToFileClassic(response.getGetFileResult().getFileData().getBlobData(),response.getGetFileResult().getFileName());
        return response;
    }

    private void writeBytesToFileClassic(byte[] bFile, String fileName) {
        String fileDest = "C:\\temp\\" + fileName;
        FileOutputStream fileOuputStream = null;

        try {
            fileOuputStream = new FileOutputStream(fileDest);
            fileOuputStream.write(bFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOuputStream != null) {
                try {
                    fileOuputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}}
}
