package functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility extends EventListeners{

	//Read data from Excel and return as an array object which contains test data in hashmap format
	@SuppressWarnings("resource")
	public Object[][] excelReader(String fileName, String sheetName, String TestCaseName) throws IOException {

		int intRowCount = 0; 
		String excelTCName;
		String colName;
		String colValue;

		String dir = System.getProperty("user.dir");
		String filePath = dir + "\\src\\main\\resources";

		File file = new File(filePath + "\\" + fileName);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet workSheet = workBook.getSheet(sheetName);
		intRowCount = workSheet.getLastRowNum() - workSheet.getFirstRowNum();

		HashMap<String, String> hm = new HashMap<String, String>();
		for(int i=1;i<=intRowCount;i++) {

			excelTCName = workSheet.getRow(i).getCell(0).getStringCellValue();
			if (excelTCName.equals(TestCaseName)) {

				XSSFRow currentRow = workSheet.getRow(i);
				for(int j=0;j<currentRow.getLastCellNum();j++) {
					colName = workSheet.getRow(0).getCell(j).getStringCellValue();
					colValue = currentRow.getCell(j).getStringCellValue();
					hm.put(colName, colValue);
				}
			}
		}


		Object[][] objData = new Object[1][1];
		objData[0][0] = hm;
		return objData;
	}

	//Decode the string using base64
	public String decodedString(String encodedString) {

		byte[] decBytes = Base64.getDecoder().decode(encodedString);
		String decString = new String(decBytes);
		return decString;		
	}

}
