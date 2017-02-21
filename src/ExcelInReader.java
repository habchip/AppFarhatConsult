import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelInReader {
	private FileInputStream file;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;

	public ExcelInReader(String fileName) throws IOException {
		try {
			file = new FileInputStream(new File(fileName));
			wb = new XSSFWorkbook(file);
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
	 * Parse input file
	 * 
	 * @param lineToParse : the line that contains all keywords
	 * @param separator : the separator between each keywords
	 * @return all keywords: one string = one keywords
	 */
	public String[] Parse(int lineToParse, String separator) {
		StringBuilder sb = new StringBuilder();

		Row row = sheet.getRow(lineToParse);
		Iterator<Cell> cellIterator = row.cellIterator();
		String cellString;

		// Browse all cells in this line
		while (cellIterator.hasNext()) {
			cellString = cellIterator.next().getStringCellValue();
			cellString = cellString.replaceAll("\"", "");
			sb.append(cellString);
			sb.append(separator);
		}
		sb.deleteCharAt(sb.length() - 1);

		return sb.toString().split(separator);
	}

	public void finalize() {
		try {
			file.close();
		} catch (IOException e) {
			System.out.println("Cannot close input file : " + e);
		}
	}
}