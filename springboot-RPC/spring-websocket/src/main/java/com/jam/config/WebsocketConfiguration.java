package com.jam.config;

import com.jam.server.DemoWebSocketHandler;
import com.jam.server.DemoWebSocketShakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(this.webSocketHandler(), "/")
                .addInterceptors(new DemoWebSocketShakeInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public DemoWebSocketHandler webSocketHandler() {
        return new DemoWebSocketHandler();
    }

    @Bean
    public DemoWebSocketShakeInterceptor webSocketShakeInterceptor() {
        return new DemoWebSocketShakeInterceptor();
    }
}
