package org.data.mongo.entity;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase de User Entity para coneccion Mongo Collection User.
 *
 * @author Sergio
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User implements Serializable {

  @Id
  private String userId;
  private String name;
  private String aPaterno;
  private String aMaterno;
  private String email;
  private List<String> orders;
  private String direccion;
}
