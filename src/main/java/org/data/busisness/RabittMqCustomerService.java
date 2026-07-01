package org.data.busisness;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.data.RabbitMQ.RabbitMqConfig;

@Service
public class RabittMqCustomerService {

  private final MongoServices mongoServices;

  @Autowired
  public RabittMqCustomerService(MongoServices mongoServices) {
    this.mongoServices = mongoServices;
  }


  @RabbitListener(queues = RabbitMqConfig.USER_QUEUE_NAME)
  public void consumeMessageFromQueue(String message) {
    System.out.println("Message received: " + message);
    mongoServices.deleteUserById(message);
  }

}
