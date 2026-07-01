package org.data.RabbitMQ;


import lombok.extern.slf4j.Slf4j;
import org.data.busisness.UserServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserUpdateOConsumerService {

  private final UserServices userServices;

  @Autowired
  public UserUpdateOConsumerService(UserServices userServices) {
    this.userServices = userServices;
  }


  @RabbitListener(queues = UserUpdateOMqConfig.USER_U_O_QUEUE_NAME)
  public void consumeMessageFromQueue(String message) {
    log.info("Recepcion de mensaje {} en la cola {} ", message,UserUpdateOMqConfig.USER_U_O_QUEUE_NAME);
    userServices.updateUserByIdAndOrders(message);
    log.info("Se Actualizo correctamente el usuario {}",message);
  }

}
