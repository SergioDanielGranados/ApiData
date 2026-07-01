package org.data.RabbitMQ;


import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.data.RabbitMQ.filePoi.ItemReport;
import org.data.busisness.ItemServices;
import org.data.dto.ItemSearch;
import org.data.mongo.entity.Item;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class ItemConsumerService {

  private final ItemServices itemServices;
  private final ObjectMapper ojectMapper ;

  @Autowired
  public ItemConsumerService(ItemServices itemServices, ObjectMapper ojectMapper) {
    this.itemServices = itemServices;
    this.ojectMapper = ojectMapper;
  }


  @RabbitListener(queues = ItemMqConfig.ITEM_QUEUE_NAME)
  public void consumeMessageFromQueue(String message) {
    log.info("Recepcion de mensaje {} en la cola {} ", message,ItemMqConfig.ITEM_QUEUE_NAME);
    List<Item> listItem = new ArrayList<>();

    ItemSearch params = ojectMapper.readValue(message, ItemSearch.class);

    if(params.getSearch() == null || params.getSearch().isEmpty() || params.getSearch().isBlank() ){
      listItem = itemServices.getAll();
    }else {
      listItem = itemServices.findByDisplayNameContaining(params.getSearch());
    }

    log.info("Items in DB {}",listItem.toString());
    ItemReport.createWorkbook(listItem);
  }

}
