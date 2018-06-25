package com.healthkart.hkAutomation.util;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;

public class ExcelReading {

	private Workbook ExcelWBook;
	private Sheet ExcelWSheet;
	
	   //Constructor to connect to the Excel with sheetname and Path
    public ExcelReading(String Path) {

        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = WorkbookFactory.create(ExcelFile);
        }
        catch (Exception e){
            System.out.println("Exception while reading Excel");
        }
    }
   //This method to get the data and get the value as strings.
    public String getCellDataAsString(String SheetName, int RowNum, int ColNum) {

        try {
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            String CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
            System.out.println("The value of Cell (" + RowNum + "," + ColNum + ") is " + CellData);
            return CellData;
        }
        catch (Exception e) {
            return "Error in Getting Cell Data";
        }
    }
    
    public String getCellDataNumberString(String SheetName, int RowNum, int ColNum) {

        try {
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            double CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
            String str = NumberToTextConverter.toText(CellData);
            System.out.println("The value of Cell (" + RowNum + "," + ColNum + ") is " + CellData);
            return str;
        }
        catch (Exception e){
            return "Unable to get cell data";
        }
    }
	
}
