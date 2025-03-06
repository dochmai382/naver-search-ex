package util;

import model.dto.NaverSearchResultItem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.logger.MyLogger;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelWriter {
    private final MyLogger logger;

    public ExcelWriter() {
        this.logger = new MyLogger(ExcelWriter.class);
        logger.info("ExcelWriter initialized");
    }

    public void createExcel(String filepath, String sheetName, List<NaverSearchResultItem> items) {
        try (
                Workbook workbook = new XSSFWorkbook();
                FileOutputStream fileOutputStream = new FileOutputStream(filepath)
        ) {
            Sheet sheet = workbook.createSheet(sheetName);
            createHeaderRow(sheet);
            createDataRows(sheet, items);

            workbook.write(fileOutputStream);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("날짜");
        headerRow.createCell(1).setCellValue("제목");
        headerRow.createCell(2).setCellValue("링크");
        headerRow.createCell(3).setCellValue("설명");
    }

    private void createDataRows(Sheet sheet, List<NaverSearchResultItem> items) {
        int i = 0;
        for (NaverSearchResultItem item : items) {
            Row row = sheet.createRow(++i);
            row.createCell(0).setCellValue(item.pubDate());
            row.createCell(1).setCellValue(item.title());
            row.createCell(2).setCellValue(item.link());
            row.createCell(3).setCellValue(item.description());
        }
    }
}