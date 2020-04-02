package com.wsclient.fs;

import com.wsclient.fs.generetad.EnumFileServerType;
import com.wsclient.fs.generetad.GetFile;
import com.wsclient.fs.generetad.GetFileResponse;
import com.wsclient.fs.model.RequestGetFile;
import com.wsclient.fs.service.WsClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsApplication.class, args);
	}
//	@Bean
//	CommandLineRunner lookup(WsClientService client){
//		return args->{
//			RequestGetFile request = new RequestGetFile();
//			request.setFileId(8834144L);request.setServerType("KBS");
//
//			GetFileResponse response = client.getFileFromFileServer(request);
//			System.out.println("File Name -> " + response.getGetFileResult().getFileName());
//
//		};
//	}
}
