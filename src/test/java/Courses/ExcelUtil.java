package Courses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private Workbook workbook;
	private Sheet sheet;
	
	public ExcelUtil(String filePath, String sheetname) throws IOException {
		FileInputStream fis=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
	}
	//get row count
	public int getRowCount()
	{
		return sheet.getLastRowNum();
	}
	//get column count
	public int getColumnCount() 
	{
		return sheet.getRow(0).getLastCellNum();
	}
	//get cell data
	public String getCellData(int rowNum, int colNum)
	{
		Row row= sheet.getRow(rowNum);
		Cell cell=row.getCell(colNum);
		return row.getCell(colNum).getStringCellValue();
	}
	public void close() throws IOException
	{
		workbook.close();
	}
}
	
