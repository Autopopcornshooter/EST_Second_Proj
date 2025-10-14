package EST.Baemin.Manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints (StompEndpointRegistry registry) {
    // 클라이언트가 연결할 엔드포인트
    registry.addEndpoint("/ws").withSockJS();
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // 메시지를 받을 때 브로커 역할
    registry.enableSimpleBroker("/topic");
    // 클라이언트가 보낼 때 prefix
    registry.setApplicationDestinationPrefixes("/app");
  }
}
