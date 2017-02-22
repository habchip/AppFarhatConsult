package AppFarhatConsult.ExcelServices;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelWriter {
	private static String titleColumn1 = "CARACTERISTIQUES";
	private static String titleColumn2 = "AVANTAGES";
	private static String titleColumn3 = "PREUVES";
	private static int columnMaxWidth = 10000;
	
	private FileOutputStream fileOut;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private String fileOutName;

	public ExcelWriter(String fileOutName, String sheetOut) {
		this.fileOutName = fileOutName;
		this.wb = new XSSFWorkbook();
		this.sheet = wb.createSheet(sheetOut);
		
		// Set red font for title
		XSSFCellStyle cellStyle = wb.createCellStyle();
		XSSFFont cellFont = wb.createFont();
		cellFont.setFontHeight(16);
		cellStyle.setFont(cellFont);
		
		// Write titles in first line
		Row row = sheet.createRow(0);
		Cell cellA1 = row.createCell(0);
		Cell cellB1 = row.createCell(1);
		Cell cellC1 = row.createCell(2);
		
		cellA1.setCellValue(titleColumn1);
		cellA1.setCellStyle(cellStyle);
		cellB1.setCellValue(titleColumn2);
		cellB1.setCellStyle(cellStyle);
		cellC1.setCellValue(titleColumn3);
		cellC1.setCellStyle(cellStyle);
	}

	/**
	 * Add one line to the excel file
	 * 
	 * @param excelLine : the content of the line
	 * @param lineNumber :  the number of the line
	 */
	public void addLine(String[] excelLine, int lineNumber) {
		Cell cell = null;
		Row row = sheet.createRow((short) lineNumber);
		XSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		XSSFFont cellFont = wb.createFont();
		cellFont.setFontHeight(12);
		cellStyle.setFont(cellFont);
		cellStyle.setWrapText(true);

		for (int column = 0; column < excelLine.length; column++) {
			
			cell = row.createCell(column);
			cell.setCellValue(excelLine[column]);
			cell.setCellStyle(cellStyle);
			sheet.autoSizeColumn(column);
			if (sheet.getColumnWidth(column) > columnMaxWidth) {
				sheet.setColumnWidth(column, columnMaxWidth);
			}
		}
	}

	/**
	 * Write the file and close the output stream
	 * 
	 * @throws IOException
	 */
	public void writeAndClose() throws IOException {
		try {
			this.fileOut = new FileOutputStream(this.fileOutName);
			wb.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			System.out.println("Error while closing file : ");
			throw e;
		}
	}
}
