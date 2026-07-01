package org.data.RabbitMQ;


import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.data.RabbitMQ.filePoi.ItemReport;
import org.data.RabbitMQ.filePoi.OrderReport;
import org.data.busisness.OrderServices;
import org.data.dto.OrderSearchParams;
import org.data.mongo.entity.Item;
import org.data.mongo.entity.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class OrderConsumerService {

  private final OrderServices orderServices ;
  private final ObjectMapper ojectMapper ;
  private final OrderReport orderReport ;

  @Autowired
  public OrderConsumerService(OrderServices orderServices, ObjectMapper ojectMapper,
                              OrderReport orderReport) {
    this.orderServices = orderServices;
    this.ojectMapper = ojectMapper;
    this.orderReport = orderReport;
  }




  @RabbitListener(queues = OrderMqConfig.ORDER_QUEUE_NAME)
  public void consumeMessageFromQueue(String message) {
    log.info("Recepcion de mensaje {} en la cola {} ", message,OrderMqConfig.ORDER_QUEUE_NAME);

    OrderSearchParams params = ojectMapper.readValue(message, OrderSearchParams.class);

    List<Order> listOrder = orderServices.findByOrderRefOrOrderStatusOrStoreName(params);
    log.info("Orders in DB OR {}",listOrder.toString());
    log.info("Orders in DB  and {}",orderServices.findByOrderRefAndOrderStatusAndStoreName(params));
    log.info("Orders in DB  container {}",orderServices.findByOrderRefAndOrderStatusAndStoreNameContainingIgnoreCase(params));
    orderReport.createSampleWorkbook(listOrder);
  }

}
