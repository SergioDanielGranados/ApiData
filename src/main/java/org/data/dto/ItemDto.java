package org.data.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Serializable {
  private String id;
  private String itemId;
  private String skuId;
  private Integer quantity;
  private String displayName;
  private String deliveryStatus;

}
