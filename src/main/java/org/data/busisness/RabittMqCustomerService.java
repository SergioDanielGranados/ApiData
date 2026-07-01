package org.data.busisness;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.data.RabbitMQ.RabbitMqConfig;

@Slf4j
@Service
public class RabittMqCustomerService {

  private final UserServices userServices;

  @Autowired
  public RabittMqCustomerService(UserServices userServices) {
    this.userServices = userServices;
  }


  @RabbitListener(queues = RabbitMqConfig.USER_QUEUE_NAME)
  public void consumeMessageFromQueue(String message) {
    log.info("Inicia Eliminacion Usuario {} ", message);
    userServices.deleteUserById(message);
  }

}
