package org.data.busisness;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.data.mongo.entity.Item;
import org.data.mongo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemServices {

  private final ItemRepository itemRepository;
  private final MongoTemplate mongoTemplate;

  @Autowired
  public ItemServices(ItemRepository itemRepository, MongoTemplate mongoTemplate) {
    this.itemRepository = itemRepository;
    this.mongoTemplate = mongoTemplate;
  }


  public List<Item> findByDisplayNameContaining(String displayName) {
    log.info("Obtencion de Items con findByDisplayNameContaining displayName : {}  in DB ", displayName);
    return itemRepository.findByDisplayNameContainingIgnoreCase(displayName);
  }

  public Item findFirstByItemId(String ItemId) {
    log.info("Obtencion de Items con findByDisplayNameContaining displayName : {}  in DB ", ItemId);
    return itemRepository.findFirstByItemId(ItemId);
  }

}
