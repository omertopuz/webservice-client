package com.wsclient.fs.controller;


import com.wsclient.fs.generetad.GetFile;
import com.wsclient.fs.generetad.GetFileResponse;
import com.wsclient.fs.model.RequestGetFile;
import com.wsclient.fs.model.ResponseFileServer;
import com.wsclient.fs.service.WsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/file-server/")
public class WsClientRestController {

    @Autowired
    private WsClientService service;

    @PostMapping("file-response")
    public ResponseFileServer getfileresponse(@Valid @RequestBody RequestGetFile request){
        GetFileResponse response =  service.getFileFromFileServer(request);
        return new ResponseFileServer(response.getGetFileResult().getFileData().getBlobData(),response.getGetFileResult().getFileName());
    }

}

