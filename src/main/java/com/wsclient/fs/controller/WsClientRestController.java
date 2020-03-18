package com.wsclient.fs.controller;


import com.wsclient.fs.generetad.GetFile;
import com.wsclient.fs.generetad.GetFileResponse;
import com.wsclient.fs.model.WsResponse;
import com.wsclient.fs.service.WsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/ws/")
public class WsClientRestController {

    @Autowired
    private WsClientService service;

    @GetMapping("getfileresponse")
    public WsResponse getfileresponse(@Valid @RequestBody GetFile request){
        GetFileResponse response =  service.getFileFromFileServer(request);
        return new WsResponse(response.getGetFileResult().getFileData().getBlobData(),response.getGetFileResult().getFileName());
    }

}

