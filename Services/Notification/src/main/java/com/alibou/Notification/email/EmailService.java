package com.alibou.Notification.email;


import com.alibou.Notification.kafka.order.PurchaseResponce;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

import static com.alibou.Notification.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.alibou.Notification.email.EmailTemplates.PAYMENT_CONFIRMATION;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Async
    public void SendPaymentSuccessful(
            String destinationEmail,
            String customerName,
            String orderRef,
            BigDecimal amount
    ) throws MessagingException
    {
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        mimeMessageHelper.setFrom("mohamedumark2005@gmail.com");

        final String templateName = PAYMENT_CONFIRMATION.getTemplate();


        Map<String,Object> var = new HashMap<>();
        var.put("customer name",customerName);
        var.put("amount" , amount);
        var.put("orderRef",orderRef);

        Context context = new Context();
        context.setVariables(var);
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = springTemplateEngine.process(templateName,context);
            mimeMessageHelper.setText(htmlTemplate,true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMailMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));

        } catch (MessagingException e) {
            log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
        }
    }

    @Async
    public void SendOrderSuccessful(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderRef,
            List<PurchaseResponce> products
    ) throws MessagingException
    {
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        mimeMessageHelper.setFrom("mohamedumark2005@gmail.com");

        final String templateName = ORDER_CONFIRMATION.getTemplate();


        Map<String,Object> var = new HashMap<>();
        var.put("customer name",customerName);
        var.put("amount" , amount);
        var.put("orderRef",orderRef);
        var.put("products",products);

        Context context = new Context();
        context.setVariables(var);
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = springTemplateEngine.process(templateName,context);
            mimeMessageHelper.setText(htmlTemplate,true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMailMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));

        } catch (MessagingException e) {
            log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
        }
    }

}
