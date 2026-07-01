package org.data.cargaInicial;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.data.mongo.entity.Item;
import org.data.mongo.entity.Order;
import org.data.mongo.repository.ItemRepository;
import org.data.mongo.repository.OrderRepository;
import org.data.rest.ItemServiceRestClient;
import org.data.rest.OrderServiceRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CargaInicial {

  private final ItemServiceRestClient itemServiceRestClient;
  private final OrderServiceRestClient orderServiceRestClient;
  private final ObjectMapper ojectMapper ;
  private final ItemRepository itemRepository;
  private final OrderRepository orderRepository;


  @Autowired
  public CargaInicial(ItemServiceRestClient itemServiceRestClient,
                      OrderServiceRestClient orderServiceRestClient, ObjectMapper ojectMapper,
                      ItemRepository itemRepository, OrderRepository orderRepository) {
    this.itemServiceRestClient = itemServiceRestClient;
    this.orderServiceRestClient = orderServiceRestClient;
    this.ojectMapper = ojectMapper;
    this.itemRepository = itemRepository;
    this.orderRepository = orderRepository;
  }


  @EventListener(ApplicationReadyEvent.class)
  public void ejecutarAlIniciarCargaInicial() throws JsonProcessingException {

    log.info("Inicia Carga inicial  ");
    log.info("Inicia Carga Item por servicio externo item ");
    List<Item> JsonItem = ojectMapper.readValue(itemServiceRestClient.getAll(), new TypeReference<List<Item>>(){});
    saveAllItem(JsonItem);
    log.info("Carga Item Exitosa");
    log.info("Info Item : {}",JsonItem.toString());

    log.info("Inicia Carga Order por servicio externo order ");
    List<Order> JsonOrder = ojectMapper.readValue(orderServiceRestClient.getAll(), new TypeReference<List<Order>>(){});
    saveAllOrder(JsonOrder);
    log.info("Carga Order Exitosa");
    log.info("Info Order : {}",JsonOrder.toString());

  }

  private void saveAllItem(List<Item> listItem){
    itemRepository.saveAll(listItem);
  }

  private void saveAllOrder(List<Order> listOrder){
    orderRepository.saveAll(listOrder);
  }


}
