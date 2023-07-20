package com.sokol.util.xlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

public final class ExportToXLXS {
    private ExportToXLXS() {
    }

    public static void exportToXLSX(List<?> data, String filePath, String[] columnNames) throws Exception {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");
            Row row = sheet.createRow(0);

            CellStyle centerBoldCellStyle = new MyCellStyle(workbook).centerBold();
            CellStyle dateCellStyle = new MyCellStyle(workbook).date();

            setHeaders(data, columnNames, row, centerBoldCellStyle);

            for (int i = 0; i < data.size(); i++) {
                row = sheet.createRow(i + 1);
                Object object = data.get(i);

                for (int j = 0; j < object.getClass().getDeclaredFields().length; j++) {
                    org.apache.poi.ss.usermodel.Cell cell = row.createCell(j);

                    java.lang.reflect.Field field = object.getClass().getDeclaredFields()[j];
                    field.setAccessible(true);
                    Object value = field.get(object);

                    if (value instanceof String valueStr) {
                        cell.setCellValue(valueStr);
                    } else if (value instanceof Double valueDouble) {
                        cell.setCellValue(valueDouble);
                    } else if (value instanceof Integer valueInt) {
                        cell.setCellValue(valueInt);
                    } else if (value instanceof LocalDate valueLocalDate) {
                        cell.setCellValue(valueLocalDate);
                        cell.setCellStyle(dateCellStyle);
                    } else if (value instanceof Enum<?> valueEnum) {
                        cell.setCellValue(valueEnum.toString());
                    }
                }
            }

            for (int i = 0; i < data.get(0).getClass().getDeclaredFields().length; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream out = new FileOutputStream(filePath);
            workbook.write(out);
            out.close();
        }
    }

    private static void setHeaders(List<?> data, String[] columnNames, Row row, CellStyle centerBoldCellStyle) {
        for (int i = 0; i < data.get(0).getClass().getDeclaredFields().length; i++) {
            Cell cell = row.createCell(i);

            if (columnNames != null && i < columnNames.length) {
                cell.setCellValue(columnNames[i]);
            } else {
                cell.setCellValue(data.get(0).getClass().getDeclaredFields()[i].getName());
            }

            cell.setCellStyle(centerBoldCellStyle);
        }
    }

}
