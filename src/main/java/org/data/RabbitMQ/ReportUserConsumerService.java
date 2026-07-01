package org.data.RabbitMQ;


import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.data.RabbitMQ.filePoi.ItemReport;
import org.data.RabbitMQ.filePoi.OrderReport;
import org.data.RabbitMQ.filePoi.UserReport;
import org.data.busisness.ItemServices;
import org.data.busisness.UserServices;
import org.data.mongo.entity.Item;
import org.data.mongo.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReportUserConsumerService {

  private final UserServices userServices;
  private final UserReport userReport;

  @Autowired
  public ReportUserConsumerService(UserServices userServices, UserReport userReport) {
    this.userServices = userServices;
    this.userReport = userReport;
  }




  @RabbitListener(queues = ReportUserMqConfig.USER_QUEUE_NAME)
  public void consumeMessageFromQueue(String message) {
    log.info("Recepcion de mensaje {} en la cola {} ", message,ReportUserMqConfig.USER_QUEUE_NAME);
    List<User> listUser = userServices.getAllUSers();
    log.info("USers in DB {}",listUser.toString());
    userReport.createWorkbook(listUser);
  }



}
