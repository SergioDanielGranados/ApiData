package org.data.busisness;


import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.data.mongo.entity.Order;
import org.data.mongo.entity.User;
import org.data.mongo.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de UserServices Para las consultas de la coleccion User.
 *
 * @author Sergio
 * @version 1.0
 */
@Slf4j
@Service
public class UserServices {

  private final UserMongoRepository userMongoRepository;
  private final OrderServices orderServices;

  @Autowired
  public UserServices(UserMongoRepository userMongoRepository, OrderServices orderServices) {
    this.userMongoRepository = userMongoRepository;
    this.orderServices = orderServices;
  }


  public void deleteUserById(String id) {
    log.info("Eliminacion Usuario in DB {} ", id);
    userMongoRepository.deleteById(id);
  }

  /**
   * Regresa todos los usuarios.
   *
   * @return todos los usuarios.
   */
  public List<User> getAllUSers() {
    return userMongoRepository.findAll();
  }

  /**
   * Actualiza las ordenes del Usuario con respecto a su IdUsuario de registro
   *
   * @param userId al cual sele cargaran las ordenes
   */
  public void updateUserByIdAndOrders(String  userId) {
    log.info("Inicia Actualizacion de las Ordenes pedido del Usuario");

    List<Order> orderList= orderServices.findByUserId(userId);
    List<String> stringOrdersList = new ArrayList<>();
    for(Order o :orderList){
      stringOrdersList.add(o.getOrderRef());
    }

    log.info("Se Actualizacion de las Ordenes pedido del Usuario {}"
        ,userMongoRepository.updateById(userId, stringOrdersList));
      }


}
