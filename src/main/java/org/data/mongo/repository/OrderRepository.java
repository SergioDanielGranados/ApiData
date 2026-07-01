package org.data.mongo.repository;

import java.util.List;
import org.data.mongo.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

  List<Order> findByOrderRefOrOrderStatusOrStoreName(String orderRef, String orderStatus, String storeName);



  List<Order> findByOrderRefAndOrderStatusAndStoreNameContainingIgnoreCase(String orderRef, String orderStatus, String storeName);

  List<Order> findByOrderRefAndOrderStatusAndStoreName(String orderRef, String orderStatus, String storeName);

}
