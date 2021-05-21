package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.Base.TestBase;

public class TestUtil extends TestBase{

	public static long PAGE_LODE_TIMEOUT=60;
	public static long IMPLICIT_WAIT_TIMEOUT=60;
	
	public static String EXCEL_DATA_PATH ="D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\src\\main\\java\\com\\qa\\TestData\\VirventureWebsiteTestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	public static  Object[][] getTestData(String sheetName) {
		FileInputStream file= null;
		try {
			file= new FileInputStream(EXCEL_DATA_PATH);
		} catch (FileNotFoundException e) {
			System.out.println("Excel file not found");
			e.printStackTrace();
		}try {
			book= WorkbookFactory.create(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sheet = book.getSheet(sheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int k=0; k<sheet.getRow(0).getLastCellNum();k++) {
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
			}
		}
		return data;
		
	}
	
	
}

