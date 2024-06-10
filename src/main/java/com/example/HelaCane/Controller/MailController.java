package com.example.HelaCane.Controller;

import com.example.HelaCane.Dto.MailDto;
import com.example.HelaCane.Util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailUtil mailUtil;

    @PostMapping({"/sendmail"})
    public MailDto receiveString(@RequestBody MailDto data) {
        String sendMail = data.getSendMail();
        String orderId=data.getOrderId();

        mailUtil.sendOrderConfirmation(sendMail,orderId);
        return data;
    }

    @PostMapping("/customizationConformEmail")
    public MailDto receiveStringCus(@RequestBody MailDto data){
        String sendMail=data.getSendMail();
        String customizeId=data.getCustomizeId();
        mailUtil.sendCustomizationConformation(sendMail,customizeId);
        return data;
    }
}
