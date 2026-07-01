package org.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.data.busisness.UserServices;

/**
 * Clase de ejemplo para demostración de Javadoc.
 *
 * @author Tu Nombre
 * @version 1.0
 */
@RestController
public class DataBaseTestController {

  private final UserServices userServices;

  /**
   * Suma dos números enteros.
   *
   * @param userServices El segundo número a sumar.
   * @return La suma de ambos números.
   */
  @Autowired
  private DataBaseTestController( UserServices userServices) {
    this.userServices = userServices;
  }


  /*@GetMapping("/mongo")
  public ResponseEntity<?>  mongo() {
    return ResponseEntity.status(200).body(mongoServices.getAllNeighbor().stream().map(Neighbor::toString)
        .collect(Collectors.toList()));
  }*/

}
