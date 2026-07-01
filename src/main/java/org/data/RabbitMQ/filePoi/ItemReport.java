package org.data.RabbitMQ.filePoi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.data.mongo.entity.Item;

@Slf4j
public class ItemReport {

  public static void createSampleWorkbook(List<Item> listItem) {
    log.info("Generando Excel ItemReport");

    try (HSSFWorkbook workbook = new HSSFWorkbook()) {


      final String SHEET_NAME = "Reporte Items por nombre";
      final String[] COLUMN_HEADERS = { "itemId", "Cantidad", "sku" ,"displayName","deliveryStatus"};

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

      for (Item item : listItem) {
        Row row = sheet.createRow(rowNum++);

        fullCells(row.createCell(0),item.getItemId());
        fullCells(row.createCell(1),item.getQuantity());
        fullCells(row.createCell(2),item.getSkuId());
        fullCells(row.createCell(3),item.getDisplayName());
        fullCells(row.createCell(4),item.getDeliveryStatus());
      }

      for (int i = 0; i < COLUMN_HEADERS.length; i++) {
        sheet.autoSizeColumn(i);
      }


      // 2. Define your file path
      String filePath = "E:\\Liverpool\\Workspace Repo\\Reportes\\ReporteItemsPorDisplayName.xls";

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
