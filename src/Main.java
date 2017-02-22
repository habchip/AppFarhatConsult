public class Main {
	public static String fileInputName = "PDDI.xlsx";
	public static String fileRefName   = "D:/Workspace/Tests/Ref_10000.xlsx";
	public static String fileOutName   = "ARGI.xlsx";
	public static String sheetOutName  = "feuille";
	public static String separator     = ",";
	public static int lineToParseIn    = 1;

	public static void main(String[] arg) {
		try {
			ExcelInReader fileParsor = new ExcelInReader(fileInputName);
			ExcelWriter excelWriter = new ExcelWriter(fileOutName, sheetOutName);
			ExcelRefReader excelReader = new ExcelRefReader(fileRefName);
			String[] keywords = fileParsor.Parse(lineToParseIn, separator);
			int lineNumber = 2;

			// For each keyword found, write a line
			for (String keyword : keywords) {
				String[] excelLine = excelReader.read(keyword);
				excelWriter.addLine(excelLine, lineNumber);
				lineNumber++;
			}

			excelWriter.writeAndClose();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}