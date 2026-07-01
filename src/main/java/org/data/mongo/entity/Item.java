package org.data.mongo.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "item")
public class Item implements Serializable {
  @Id
  private String id;
  private String itemId;
  private String skuId;
  private Integer quantity;
  private String displayName;
  private String deliveryStatus;

}
