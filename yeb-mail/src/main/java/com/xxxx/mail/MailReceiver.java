package com.xxxx.mail;

import com.rabbitmq.client.Channel;
import com.xxxx.yebserver.entity.Employee;
import com.xxxx.yebserver.utils.MailConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;


/**
 * @author Aquarius
 * @description 邮件接收
 * @create 2022-11-13 22:39
 */
@Component
@Slf4j
public class MailReceiver {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate redisTemplate;

    // 监听队列
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message<Employee> message, Channel channel) {
        // 获取员工类
        Employee employee = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");
        HashOperations hashOperations = redisTemplate.opsForHash();
        try {
            if (hashOperations.entries("mail_log").containsKey(msgId)) {
                log.error("消息已经被消费!=====================>{}", msgId);
                channel.basicAck(tag, false);
                return;
            }
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);

            helper.setFrom(mailProperties.getUsername());
            helper.setTo(employee.getEmail());
            helper.setSubject("入职欢迎邮件");
            helper.setSentDate(new Date());

            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("joblevelName", employee.getJoblevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());

            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);
            mailSender.send(msg);
            log.info("邮件发送成功!");
            hashOperations.put("mail_log", msgId, "ok");
            channel.basicAck(tag, false);
        } catch (Exception e) {
            try {
                channel.basicNack(tag, false, true);
            } catch (IOException ex) {
                log.error("邮件发送失败==============>{}", ex.getMessage());
            }
            log.error("邮件发送失败 ==========={}", e.getMessage());
        }
    }
}
