package org.data.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.data.mongo.repository")
@EntityScan(basePackages = {"org.data.mongo.entity"})
public class MongoConfig {

  @Bean
  public MongoClient mongoClient() {
    return MongoClients.create("mongodb://mongo:mongopass@localhost:27017/test?authSource=test");
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), "test");
  }
}
