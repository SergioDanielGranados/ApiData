package org.data.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  public static final String USER_EXCHANGE_NAME = "user-exchange";
  public static final String USER_QUEUE_NAME = "user-queue";
  public static final String USER_ROUTING_KEY = "user-routing-key";

  @Bean
  public Queue userQueue() {
    return new Queue(USER_QUEUE_NAME, true);
  }

  @Bean
  public TopicExchange userExchange() {
    return new TopicExchange(USER_EXCHANGE_NAME);
  }

  @Bean
  public Binding userBinding() {
    return BindingBuilder
        .bind(userQueue())
        .to(userExchange())
        .with(USER_ROUTING_KEY);
  }
}
