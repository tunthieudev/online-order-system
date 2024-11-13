package vn.hoidanit.laptopshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import vn.hoidanit.laptopshop.service.EmailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody String messageJson) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            SimpleMailMessage mailMessage=mapper.readValue(messageJson,SimpleMailMessage.class);
            SimpleMailMessage realMessage=new SimpleMailMessage();
            realMessage.setFrom(mailMessage.getFrom());
            realMessage.setTo(mailMessage.getReplyTo());
            realMessage.setText(mailMessage.getText());
            realMessage.setSubject(mailMessage.getSubject());
            emailService.sendEmail(realMessage);   

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
