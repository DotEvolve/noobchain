/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.thirdParty;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import net.dotevolve.base.data.NotificationDelivery;
import net.dotevolve.base.data.NotificationDeliveryEnum;
import net.dotevolve.base.utils.CodeCondition;
import net.dotevolve.base.utils.DateTimeFormatEnum;
import net.dotevolve.base.utils.DateUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private Configuration config;

    @Value("${environment}")
    private String env;

    public String formatMapToHtml(String templateName, Map<String, Object> data) {
        try {
            config.setClassForTemplateLoading(this.getClass(), "/mail");
            Template freemarkerTemplate = config.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, data);
        } catch (IOException | TemplateException e) {
            CodeCondition.validate(false, e.getMessage(), data);
        }
        return "";

    }

    public NotificationDelivery sendSimpleMessage(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = body;
            // message.setFrom("syed@dotevolve.com");
            if (env.compareTo("dev") == 0) {
                message.setFrom("Tech Server<no-reply@dotevolve.com>");
            } else {
                message.setFrom("No Reply<no-reply@dotevolve.com>");
            }
            if (to.contains(",")) {
                message.setTo(to.split(","));
            } else {
                message.setTo(to);
            }
            message.setSubject(subject);
            message.setText(body, true);
            emailSender.send(mimeMessage);
            NotificationDelivery delivery = new NotificationDelivery();
            delivery.setServiceId("NO_ID");
            delivery.setTime(dateUtil.getCurrentDateTime(DateTimeFormatEnum.FULL_DATE_TIME));
            delivery.setStatus(NotificationDeliveryEnum.DELIVERED);
            return delivery;
        } catch (Exception e) {
            NotificationDelivery delivery = new NotificationDelivery();
            delivery.setTime(dateUtil.getCurrentDateTime(DateTimeFormatEnum.FULL_DATE_TIME));
            delivery.setStatus(NotificationDeliveryEnum.ERROR);
            delivery.setError(e.getMessage());
            return delivery;
        }
    }

    /**
     * @param to       : recipients email
     * @param subject  : email subject
     * @param body     : email body in html
     * @param file     : file on server
     * @param fileName : custom file name that you want to on email content
     *
     * @return : NotificationDelivery delivery
     *
     * @apiNote Send email with single attachment , Only 25 MB file can be sent through
     * email
     */
    public NotificationDelivery sendSimpleMessage(String to, String subject, String body, File file, String fileName) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
            if (env.compareTo("dev") == 0) {
                message.setFrom("Tech Server<no-reply@dotevolve.net>");
            } else {
                message.setFrom("No Reply<no-reply@dotevolve.net>");
            }
            if (to.contains(",")) {
                message.setTo(to.split(","));
            } else {
                message.setTo(to);
            }
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText(body);
            messageBodyPart1.setHeader("Content-Type", "text/html");
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(fileName);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            mimeMessage.setContent(multipart);
            message.setSubject(subject);
//			message.setText(body, true);
            emailSender.send(mimeMessage);
            NotificationDelivery delivery = new NotificationDelivery();
            delivery.setServiceId("NO_ID");
            delivery.setTime(dateUtil.getCurrentDateTime(DateTimeFormatEnum.FULL_DATE_TIME));
            delivery.setStatus(NotificationDeliveryEnum.DELIVERED);
            return delivery;
        } catch (Exception e) {
            NotificationDelivery delivery = new NotificationDelivery();
            delivery.setTime(dateUtil.getCurrentDateTime(DateTimeFormatEnum.FULL_DATE_TIME));
            delivery.setStatus(NotificationDeliveryEnum.ERROR);
            delivery.setError(e.getMessage());
            return delivery;
        }
    }
}
