package org.data.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemMqConfig {

  public static final String ITEM_EXCHANGE_NAME = "item-report-exchange";
  public static final String ITEM_QUEUE_NAME = "item-report-queue";
  public static final String ITEM_ROUTING_KEY = "item-report-routing-key";

  @Bean
  public Queue itemQueue() {
    return new Queue(ITEM_QUEUE_NAME, true);
  }

  @Bean
  public TopicExchange itemExchange() {
    return new TopicExchange(ITEM_EXCHANGE_NAME);
  }

  @Bean
  public Binding itemBinding() {
    return BindingBuilder
        .bind(itemQueue())
        .to(itemExchange())
        .with(ITEM_ROUTING_KEY);
  }

}
