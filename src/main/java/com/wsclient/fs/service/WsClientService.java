package com.wsclient.fs.service;


import com.wsclient.fs.config.WsClientConfig;
import com.wsclient.fs.generetad.GetFile;
import com.wsclient.fs.generetad.GetFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class WsClientService {
    @Autowired
    private WsClientConfig template;

    public GetFileResponse getFileFromFileServer(GetFile request) {
        GetFileResponse response =
                (GetFileResponse) template.getWebServiceTemplate()
                        .marshalSendAndReceive(request,template);

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
