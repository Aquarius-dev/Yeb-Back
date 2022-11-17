package com.xxxx.yebserver.utils;

/**
 * @author Aquarius
 * @description 消息状态
 * @create 2022-11-15 13:07
 */
public class MailConstants {
    /**
     * 消息投递中
     */
    public static final Integer DELIVERING = 0;
    /**
     * 消息投递成功
     */
    public static final Integer SUCCESS = 1;
    /**
     * 消息投递失败
     */
    public static final Integer FAILURE = 2;
    /**
     * 最大重试次数
     */
    public static final Integer MAX_TRY_COUNT = 3;
    /**
     * 消息超时时间
     * 1 Minutes
     */
    public static final Integer MEG_TIMEOUT = 1;
    /**
     * 队列
     */
    public static final String MAIL_QUEUE_NAME = "mail.queue";
    /**
     * 交换机
     */
    public static final String MAIL_EXCHANGE_NAME = "mail.exchange";
    /**
     * 路由键
     */
    public static final String MAIL_ROUTTING_KEY_NAME = "mail.routing.key";
}