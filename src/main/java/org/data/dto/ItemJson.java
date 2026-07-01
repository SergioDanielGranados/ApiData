package org.data.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.mongo.entity.Item;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemJson {
  private List<Item> listItem;
 }
