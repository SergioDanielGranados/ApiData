package org.data.mongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.data.mongo.entity.User;


@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {

}
