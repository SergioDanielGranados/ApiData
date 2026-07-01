package org.data.RabbitMQ;


import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.data.RabbitMQ.filePoi.ItemReport;
import org.data.busisness.ItemServices;
import org.data.busisness.UserServices;
import org.data.mongo.entity.Item;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemConsumerService {

  private final ItemServices itemServices;

  @Autowired
  public ItemConsumerService(ItemServices itemServices) {
    this.itemServices = itemServices;
  }


  @RabbitListener(queues = ItemMqConfig.ITEM_QUEUE_NAME)
  public void consumeMessageFromQueue(String message) {
    log.info("Recepcion de mensaje {} en la cola {} ", message,ItemMqConfig.ITEM_QUEUE_NAME);
    List<Item> listItem = itemServices.findByDisplayNameContaining(message);
    log.info("Items in DB {}",listItem.toString());
    ItemReport.createSampleWorkbook(listItem);
  }

}
