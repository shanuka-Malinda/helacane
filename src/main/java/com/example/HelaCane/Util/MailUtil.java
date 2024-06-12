package com.example.HelaCane.Util;

import com.example.HelaCane.Dto.MailDto;
import com.example.HelaCane.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Component
public class MailUtil {

    private final JavaMailSender mailSender;

    private final UserService userService;

    @Autowired
    public MailUtil(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }

    public void sendOrderConfirmation(String toEmail, String orderId) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shanukamalinda5888@gmail.com");
        message.setTo(toEmail);
        message.setText("Your order has been conformed!\n" +
                "Your order (Order ID: ["+orderId+"]) has been successfully processed. Thank you for your order!");
        message.setSubject("HelaCane - Order Confirmation!");

        mailSender.send(message);
        System.out.println("Mail Sent!!!!!");
    }

    public void sendCustomizationConformation(String toEmail, String customizeId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shanukamalinda5888@gmail.com");
        message.setTo(toEmail);
        message.setText("Your Customization has been conformed!\n" +
                "Your order (Customization ID: ["+customizeId+"]) has been successfully processed. Thank you for your order! \n"+
                "Your product will be deliver within 30-40 days");
        message.setSubject("HelaCane - Order Confirmation!");

        mailSender.send(message);
        System.out.println("Mail Sent!!!!!");
    }

    public CommonResponse resetPassword(MailDto mailDto){
        CommonResponse commonResponse=new CommonResponse();
        String toEmail=mailDto.getSendMail();
        int OTP;
        if (userService.emailExits(toEmail)){
            OTP= ThreadLocalRandom.current().nextInt(100000,999999);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("shanukamalinda5888@gmail.com");
            message.setTo(toEmail);
            message.setText("Your OTP ! \n" +
                    "Your One Time Passcode is::"+OTP+" ");
            message.setSubject("HalaCane - Account Password reset");

            mailSender.send(message);
            System.out.println("Mail Sent!!!!!");
            commonResponse.setStatus(true);
            commonResponse.setPayload(Collections.singletonList(String.valueOf(OTP)));
        }else {
            LOGGER.error("/**************** Exception in  MailUtil -> PasswordReset()");
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(Collections.singletonList("An error occurred while password resetting."));
        }

      return commonResponse;
    }
}
