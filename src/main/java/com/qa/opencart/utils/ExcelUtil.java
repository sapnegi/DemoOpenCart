package com.qa.opencart.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {
    public static String TEST_DATA_SHEET = "./src/test/resources/TestData/demoOpenCartTestDataSheet.xlsx";
    private static Workbook wbook;
    private static Sheet sheet;

    public static Object[][] getTestData(String SheetName){
        Object data[][] = null;
        try {
            FileInputStream fp = new FileInputStream(TEST_DATA_SHEET);
            wbook = WorkbookFactory.create(fp);
            sheet = wbook.getSheet(SheetName);
            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
            for(int i=0; i< sheet.getLastRowNum();i++) {
                for (int j=0;j< sheet.getRow(0).getLastCellNum();j++) {
                        data[i][j]= sheet.getRow(i+1).getCell(j).toString();
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return data;
    }
}
