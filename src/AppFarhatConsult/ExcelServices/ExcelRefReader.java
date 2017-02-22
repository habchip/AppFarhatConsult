package AppFarhatConsult.ExcelServices;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelRefReader {
	private FileInputStream file;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	
	public ExcelRefReader(String fileName) throws IOException {
		try {
			file = new FileInputStream(new File(fileName));
			wb   = new XSSFWorkbook(file);
			sheet = wb.getSheetAt(0);
			
		} catch (FileNotFoundException e) {
			System.out.println("Error while open ref file : ");
			throw e;
		} catch (IOException e) {
			System.out.println("Error ref file is not an excel file : ");
			throw e;
		}
	}
	
	
	/**
	 * Read reference file and return all field associate to the keyword
	 * 
	 * @param keywordExpected
	 * @return a tab of string : one cell = one string
	 */
	public String [] read(String keywordExpected){
		Iterator<Row> rowIterator = sheet.iterator();
		String keywordRead = "";
		Row row = null;
		
		// Browse excel file
		while (rowIterator.hasNext() && keywordRead.compareTo(keywordExpected) != 0) {
			row = rowIterator.next();
			keywordRead = row.getCell(0).getStringCellValue();
		}
		
		if (keywordRead.compareTo(keywordExpected) != 0 || keywordRead == "")
			throw new IllegalArgumentException("Wrong keyword expected : " + keywordExpected);
		
		String [] result = {
				row.getCell(1).getStringCellValue(),
				row.getCell(2).getStringCellValue(),
				row.getCell(3).getStringCellValue()};
		
		return result;
	}
	
	  public void finalize(){
		  try {
			file.close();
		} catch (IOException e) {
			System.out.println("Cannot close ref file : " + e);
		}
	  }
}
