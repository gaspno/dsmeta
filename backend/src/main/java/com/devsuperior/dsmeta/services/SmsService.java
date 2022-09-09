package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    private SaleRepository repository;

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    public void sendSms(Long id) {

        Sale sale = repository.findById(id).get();
        String val=String.format("%.2f" ,sale.getAmount());

        String msg="O vendedor "+
                sale.getSellerName()+
                " foi destque em "+
                sale.getDate().getMonthValue()+
                "/"+
                sale.getDate().getYear()+
                " com um total de R$ "+val;

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, msg).create();

        System.out.println(message.getSid());
    }
}
