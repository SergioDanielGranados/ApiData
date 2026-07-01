package org.data.controller;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.data.busisness.MongoServices;

/**
 * Clase de ejemplo para demostración de Javadoc.
 *
 * @author Tu Nombre
 * @version 1.0
 */
@RestController
public class DataBaseTestController {

  private final MongoServices mongoServices;

  /**
   * Suma dos números enteros.
   *
   * @param mongoServices El segundo número a sumar.
   * @return La suma de ambos números.
   */
  @Autowired
  private DataBaseTestController( MongoServices mongoServices) {
    this.mongoServices =  mongoServices;
  }


  /*@GetMapping("/mongo")
  public ResponseEntity<?>  mongo() {
    return ResponseEntity.status(200).body(mongoServices.getAllNeighbor().stream().map(Neighbor::toString)
        .collect(Collectors.toList()));
  }*/

}
