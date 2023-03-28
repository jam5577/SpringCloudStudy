package com.jam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 因为 Tomcat WebSocket 使用的是 Session 作为会话，而 Spring WebSocket 使用的是 WebSocketSession 作为会话，导致我们需要略微修改下 WebSocketUtil 工具类。改动非常略微，胖友点击 WebSocketUtil 查看下，秒懂的噢。 主要有两点：
 * <p>
 * 将所有使用 Session 类的地方，调整成 WebSocketSession 类。
 * 将发送消息，从 Session 修改成 WebSocketSession 。
 */

@SpringBootApplication
public class TomcatWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(TomcatWebsocketApplication.class, args);
    }

}