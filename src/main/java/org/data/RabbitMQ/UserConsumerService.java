package org.data.RabbitMQ;


import lombok.extern.slf4j.Slf4j;
import org.data.busisness.UserServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserConsumerService {

  private final UserServices userServices;

  @Autowired
  public UserConsumerService(UserServices userServices) {
    this.userServices = userServices;
  }


  @RabbitListener(queues = UserMqConfig.USER_QUEUE_NAME)
  public void consumeMessageFromQueue(String message) {
    log.info("Inicia Eliminacion Usuario {} ", message);
    userServices.deleteUserById(message);
  }

}
