package org.data.dto;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearchParams implements Serializable {

  private String orderRef;
  private String orderStatus;
  private String storeName;

}
