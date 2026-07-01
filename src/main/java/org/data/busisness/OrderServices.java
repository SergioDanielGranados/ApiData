package org.data.busisness;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.data.dto.OrderSearchParams;
import org.data.mongo.entity.Item;
import org.data.mongo.entity.Order;
import org.data.mongo.repository.ItemRepository;
import org.data.mongo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServices {

  private final OrderRepository orderRepository;

  @Autowired
  public OrderServices(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }


  public List<Order> findByOrderRefOrOrderStatusOrStoreName(OrderSearchParams orderSearchParams) {
    log.info("Obtencion de Order con findByOrderRefOrOrderStatusOrStoreName  : {}  in DB ", orderSearchParams.toString());
    return orderRepository.findByOrderRefOrOrderStatusOrStoreName(orderSearchParams.getOrderRef(),orderSearchParams.getOrderStatus(),orderSearchParams.getStoreName());
  }

  public List<Order> findByOrderRefAndOrderStatusAndStoreNameContainingIgnoreCase(OrderSearchParams orderSearchParams) {
    log.info("Obtencion de Order con findByOrderRefAndOrderStatusAndStoreNameContainingIgnoreCase  : {}  in DB ", orderSearchParams.toString());
    return orderRepository.findByOrderRefAndOrderStatusAndStoreNameContainingIgnoreCase(orderSearchParams.getOrderRef(),orderSearchParams.getOrderStatus(),orderSearchParams.getStoreName());
  }

  public List<Order> findByOrderRefAndOrderStatusAndStoreName(OrderSearchParams orderSearchParams) {
    log.info("Obtencion de Order con findByOrderRefAndOrderStatusAndStoreNameContainingIgnoreCase  : {}  in DB ", orderSearchParams.toString());
    return orderRepository.findByOrderRefAndOrderStatusAndStoreName(orderSearchParams.getOrderRef(),orderSearchParams.getOrderStatus(),orderSearchParams.getStoreName());
  }

}
