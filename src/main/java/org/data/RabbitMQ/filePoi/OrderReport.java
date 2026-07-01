package org.data.RabbitMQ.filePoi;

import java.io.File;
import java.io.FileOutputStream;
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
import org.data.mongo.entity.Item;
import org.data.mongo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderReport {

  private ItemServices itemServices;

  @Autowired
  public OrderReport(ItemServices itemServices) {
    this.itemServices = itemServices;
  }

  public void createSampleWorkbook(List<Order> listOrder) {
    log.info("Generando Excel OrderReport");

    try (HSSFWorkbook workbook = new HSSFWorkbook()) {


      final String SHEET_NAME = "Reporte Order por Parametros";
      final String[] COLUMN_HEADERS = { "Orden", "Código del producto", "Cantidad" ,"Canal de venta","Estatus de pedido (Fecha estimada de entrega)"};

      Sheet sheet = workbook.createSheet(SHEET_NAME);

      HSSFFont font = workbook.createFont();
      font.setBold(true);
      HSSFCellStyle headerStyle = workbook.createCellStyle();
      headerStyle.setFont(font);

      Row header = sheet.createRow(0);
      for (int i = 0; i < COLUMN_HEADERS.length; i++) {
        Cell cell = header.createCell(i);
        cell.setCellValue(COLUMN_HEADERS[i]);
        cell.setCellStyle(headerStyle);
      }

      int rowNum = 1;

      for (Order order : listOrder) {
        log.info("order : {}",order.toString());
        for(String item : order.getItems()){
          Optional<Item> itemDto = Optional.ofNullable(itemServices.findFirstByItemId(item));
          if(itemDto.isPresent() && !itemDto.isEmpty()){
            Row row = sheet.createRow(rowNum++);
            log.info("Item : {}",itemDto.toString());
            Integer i= itemDto.get().getQuantity();
            fullCells(row.createCell(0),order.getOrderRef());
            fullCells(row.createCell(1),item);
            fullCells(row.createCell(2),i);
            fullCells(row.createCell(3),order.getCanal());
            fullCells(row.createCell(4),order.getOrderStatus());
          }

        }

      }

      for (int i = 0; i < COLUMN_HEADERS.length; i++) {
        sheet.autoSizeColumn(i);
      }


      // 2. Define your file path
      String filePath = "E:\\Liverpool\\Workspace Repo\\Reportes\\ReporteOrderPorParametros.xls";

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
