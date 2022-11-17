package com.xxxx.yebserver.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xxxx.yebserver.entity.MailLog;
import com.xxxx.yebserver.service.MailLogService;
import com.xxxx.yebserver.utils.MailConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aquarius
 * @description RabbitMQ 配置类
 * @create 2022-11-15 19:23
 */
@Configuration
@Slf4j
public class RabbitMQConfig {
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private MailLogService mailLogService;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        /**
         * 消息确认回调，确认消息是否到达 broken
         * data: 消息的唯一标识
         * ack: 确认结果
         * cause：失败原因
         */
        rabbitTemplate.setConfirmCallback(((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                log.info("{}==============>消息发送成功", msgId);
                mailLogService.update(new UpdateWrapper<MailLog>().set("status", 1).eq("msg_id", msgId));
            } else {
                log.error("{}==============> 消息发送失败", msgId);
            }
        }));
        rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingKey) -> {
            log.error("{}=========================> 消息发送Queue是失败", msg.getBody());
        });
        return rabbitTemplate;
    }

    @Bean
    public Queue queue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTTING_KEY_NAME);
    }
}

