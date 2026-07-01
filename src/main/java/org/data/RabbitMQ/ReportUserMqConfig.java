package org.data.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportUserMqConfig {

  public static final String USER_EXCHANGE_NAME = "user-report-exchange";
  public static final String USER_QUEUE_NAME = "user-report-queue";
  public static final String USER_ROUTING_KEY = "user-report-routing-key";

  @Bean
  public Queue userReporQueue() {
    return new Queue(USER_QUEUE_NAME, true);
  }

  @Bean
  public TopicExchange userReporExchange() {
    return new TopicExchange(USER_EXCHANGE_NAME);
  }

  @Bean
  public Binding useReporrBinding() {
    return BindingBuilder
        .bind(userReporQueue())
        .to(userReporExchange())
        .with(USER_ROUTING_KEY);
  }

}
