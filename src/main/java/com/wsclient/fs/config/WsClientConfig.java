package com.wsclient.fs.config;

import com.wsclient.fs.generetad.AuthHeader;
import com.wsclient.fs.generetad.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@Configuration
public class WsClientConfig  implements WebServiceMessageCallback {
    private final String SOAP_1_2_PROTOCOL= "SOAP 1.2 Protocol";
    private Jaxb2Marshaller marshaller;

    @Value( "${fileserverwsprops.credential.wsusername}" )
    private String wsusername;
    @Value( "${fileserverwsprops.credential.wspassword}" )
    private String wspassword;
    @Value( "${fileserverwsprops.credential.wsuserid}" )
    private int wsuserid;
    @Value( "${fileserverwsprops.credential.wsclientip}" )
    private String wsclientip;

    @Value( "${fileserverwsprops.serviceuri}" )
    private String serviceuri;

    private Jaxb2Marshaller getFileClientWsMarshaller() {
        Jaxb2Marshaller marshal = new Jaxb2Marshaller();
        marshal.setPackagesToScan("com.wsclient.fs.generetad");
        return marshal;
    }

    @Bean
    public WebServiceTemplate getWebServiceTemplate() {
        SaajSoapMessageFactory messageFactory=null;
        try {
            messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance(SOAP_1_2_PROTOCOL));
            messageFactory.setSoapVersion(SoapVersion.SOAP_12);
            messageFactory.afterPropertiesSet();
        } catch (SOAPException e) {

        }

        WebServiceTemplate template = new WebServiceTemplate(messageFactory);
        marshaller = getFileClientWsMarshaller();

        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        template.setDefaultUri(serviceuri);

        return template;
    }

    @Override
    public void doWithMessage(WebServiceMessage webServiceMessage) throws IOException, TransformerException {
        SoapHeader soapHeader = ((SoapMessage) webServiceMessage).getSoapHeader();
        // create the header element
        ObjectFactory factory = new ObjectFactory();
        AuthHeader header = factory.createAuthHeader();
        header.setKullaniciAdi(wsusername);
        header.setSifre(wspassword);
        header.setSystemUserId(wsuserid);
        header.setClientIp(wsclientip);

        JAXBElement<AuthHeader> headers = factory.createAuthHeader(header);

        marshaller.marshal(headers, soapHeader.getResult());
    }
}