package org.data.mongo.repository;


import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.data.mongo.entity.User;


@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {

  // Find user by ID and set a new email address
  @Query("{ '_id': ?0 }")
  @Update("{ '$set': { 'orders': ?1 } }")
  long updateById(String userId, List<String> listOrders);

}
