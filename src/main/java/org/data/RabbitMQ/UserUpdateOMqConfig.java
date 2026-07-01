package org.data.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUpdateOMqConfig {

  public static final String USER_U_O_EXCHANGE_NAME = "user-update-o-exchange";
  public static final String USER_U_O_QUEUE_NAME = "user-update-o-queue";
  public static final String USER_U_O_ROUTING_KEY = "user-update-o-routing-key";

  @Bean
  public Queue userUpdateOQueue() {
    return new Queue(USER_U_O_QUEUE_NAME, true);
  }

  @Bean
  public TopicExchange userUpdateOExchange() {
    return new TopicExchange(USER_U_O_EXCHANGE_NAME);
  }

  @Bean
  public Binding userUpdateOBinding() {
    return BindingBuilder
        .bind(userUpdateOQueue())
        .to(userUpdateOExchange())
        .with(USER_U_O_ROUTING_KEY);
  }
}
