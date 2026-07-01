package org.data.busisness;


import lombok.extern.slf4j.Slf4j;
import org.data.mongo.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServices {

  private final UserMongoRepository userMongoRepository;

  @Autowired
  public UserServices(UserMongoRepository userMongoRepository) {
    this.userMongoRepository = userMongoRepository;
  }


  public void deleteUserById(String id) {
    log.info("Eliminacion Usuario in DB {} ", id);
    userMongoRepository.deleteById(id);
  }

}
