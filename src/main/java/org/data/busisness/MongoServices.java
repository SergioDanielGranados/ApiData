package org.data.busisness;

import org.data.dao.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoServices {

  private final UserMongoRepository userMongoRepository;

  @Autowired
  public MongoServices(UserMongoRepository userMongoRepository) {
    this.userMongoRepository = userMongoRepository;
  }


  public void deleteUserById(String id) {
    userMongoRepository.deleteById(id);
  }

}
