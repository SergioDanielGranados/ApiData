package org.data.RabbitMQ.filePoi;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.data.busisness.ItemServices;
import org.data.busisness.OrderServices;
import org.data.mongo.entity.Item;
import org.data.mongo.entity.Order;
import org.data.mongo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserReport {

  private final ItemServices itemServices;
  private final OrderServices orderServices;

  @Value("${reports.data.prueba.liverpool.path}")
  private String path;

  public UserReport(ItemServices itemServices, OrderServices orderServices) {
    this.itemServices = itemServices;
    this.orderServices = orderServices;
  }

  public void createWorkbook(List<User> listUser) {
    log.info("Generando Excel UserReport");

    try (HSSFWorkbook workbook = new HSSFWorkbook()) {

      LocalDateTime date = LocalDateTime.now();


      final String SHEET_NAME = "Reporte usuarios Pedidos";
      final String[] COLUMN_HEADERS0 = { "Datos Del Cliente", "", "" ,"","Datos de entrega","Datos Del Pedido"};
      final String[] COLUMN_HEADERS1 ={"Nombre","Apellido Paterno", "Apellido Materno","Correo Electronico","Direccion Entrega","Orden", "Código del producto", "Cantidad" ,"Canal de venta","Estatus de pedido (Fecha estimada de entrega)"};

      Sheet sheet = workbook.createSheet(SHEET_NAME);

      HSSFFont font = workbook.createFont();
      font.setBold(true);
      HSSFCellStyle headerStyle = workbook.createCellStyle();
      headerStyle.setFont(font);

      Row header = sheet.createRow(0);
      for (int i = 0; i < COLUMN_HEADERS0.length; i++) {
        Cell cell = header.createCell(i);
        cell.setCellValue(COLUMN_HEADERS0[i]);
        cell.setCellStyle(headerStyle);
      }

      Row header1 = sheet.createRow(1);
      for (int i = 0; i < COLUMN_HEADERS1.length; i++) {
        Cell cell = header1.createCell(i);
        cell.setCellValue(COLUMN_HEADERS1[i]);
        cell.setCellStyle(headerStyle);
      }

      int rowNum = 2;

      for(User u: listUser){
        List<Order> listOrder = orderServices.findByUserId(u.getUserId());
        for (Order order : listOrder) {
          log.info("order : {}",order.toString());
          for(String item : order.getItems()){
            Optional<Item> itemDto = Optional.ofNullable(
                itemServices.findFirstByItemId(item));
            if(itemDto.isPresent() && !itemDto.isEmpty()){
              Row row = sheet.createRow(rowNum++);
              log.info("Item : {}",itemDto.toString());
              Integer i= itemDto.get().getQuantity();
              fullCells(row.createCell(0),u.getName());
              fullCells(row.createCell(1),u.getAPaterno());
              fullCells(row.createCell(2),u.getAMaterno());
              fullCells(row.createCell(3),u.getEmail());
              fullCells(row.createCell(4),u.getDireccion());


              fullCells(row.createCell(5),order.getOrderRef());
              fullCells(row.createCell(6),item);
              fullCells(row.createCell(7),i);
              fullCells(row.createCell(8),order.getCanal());
              fullCells(row.createCell(9),order.getOrderStatus());
            }

          }

        }

      }



      for (int i = 0; i < COLUMN_HEADERS1.length; i++) {
        sheet.autoSizeColumn(i);
      }


      // 2. Define your file path
      String filePath = path+"ReportePedidosUsuarios"+date.atZone(
          ZoneId.systemDefault()).toInstant().toEpochMilli()+".xls";

      // 3. Write and save the file
      try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
        workbook.write(fileOut);
        log.info("Excel generado en : " + filePath);
      } catch (Exception e) {
        log.info(e.getMessage());
      }

    } catch (Exception e) {
      log.info(e.getMessage());
    }

  }

  private static void fullCells(Cell cell, Object value) {
    if (value instanceof Integer) {
      cell.setCellValue(((Integer) value).doubleValue());
    } else if (value instanceof Double) {
      cell.setCellValue((Double) value);
    } else if (value != null) {
      cell.setCellValue(value.toString());
    }
  }
}
