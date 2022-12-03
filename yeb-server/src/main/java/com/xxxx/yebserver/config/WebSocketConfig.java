package com.xxxx.yebserver.config;

import com.xxxx.yebserver.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Aquarius
 * @description websocket config
 * @create 2022-11-24 16:28
 */
@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 添加这个 Endpoint, 这样的网页可以通过 websocket 连接上服务
     * 也就是我们配置 websocket 的服务地址，并且可以指定是否使用 socketJS
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 1. 将 ws/ep 路径注册为 stomp 的端点，用户连接了这个端点就可以进行 websocket 通讯，支持 socketJS
         * 2. setAllowedOrigins("*"):允许跨域
         */
        registry.addEndpoint("/ws/ep").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        registration.interceptors(new ChannelInterceptor() {
            // 预发送
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                // 判断是否为连接，如果是，需要获取token，并且设置用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    // 令牌
                    String token = accessor.getFirstNativeHeader("Auth-Token");//前端定义的参数
                    log.info("Auth-Token:{}", token);
                    if (!StringUtils.isEmpty(token)) {
                        String authToken = token.substring(tokenHead.length());
                        // 从token获取用户名
                        String username = jwtUtils.getUsernameFromToken(authToken);
                        if (!StringUtils.isEmpty(username)) {
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            if (jwtUtils.validateToken(authToken, userDetails)) {
                                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                                accessor.setUser(authentication);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    /**
     * 配置消息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");
    }
}
