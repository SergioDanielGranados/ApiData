package org.data.mongo.repository;

import java.util.List;
import org.data.mongo.entity.Item;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
  List<Item> findByDisplayNameStartingWith(String displayName);

  Item findFirstByItemId(String itemId);

  List<Item> findAllBy(TextCriteria criteria);

  List<Item> findByDisplayNameContainingIgnoreCase(String displayName);

}
