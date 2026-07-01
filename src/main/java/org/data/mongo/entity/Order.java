package org.data.mongo.entity;


import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order implements Serializable {
  @Id
  private String id;
  private String orderRef;
  private String userId;
  private String canal;
  private String orderStatus;
  private Boolean marketPlace;
  private Boolean giftRegistry;
  private List<String> items;
  private String storeName;

}
