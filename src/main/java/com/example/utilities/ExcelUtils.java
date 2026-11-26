
package com.example.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath)
            throws IOException {

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);

            for (int j = 0; j < cols; j++) {
                Cell cell = row.getCell(j);

                data[i - 1][j] =
                    cell == null ? "" :
                    cell.toString().trim();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}
