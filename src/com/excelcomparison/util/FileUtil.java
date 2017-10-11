package com.excelcomparison.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The class FileUtil is use to manipulate i.e. read from and write into the
 * files
 * 
 * @author Junaid
 */
public class FileUtil {

	/**
	 * The method writeNamesOrDatesResult() is use to write names and dates
	 * result into the file
	 * 
	 * @param names
	 *            list of name or date
	 * @param results
	 *            comparison result
	 * @param isName
	 *            true if names else false for dates
	 */
	public static void writeNamesOrDatesResult(Map<String, String> names, List<String> results, Boolean isName) {

		String appendStr = Boolean.TRUE.equals(isName) ? "Name" : "Date";

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Result");

		if (names != null && names.size() > 0 && results != null && results.size() > 0) {

			Set<Entry<String, String>> keys = names.entrySet();

			Row row = sheet.createRow(0);

			row.createCell(0).setCellValue("Raw " + appendStr);
			row.createCell(1).setCellValue("Editted " + appendStr);
			row.createCell(2).setCellValue("Category");

			Integer index = 0;

			for (String key : names.keySet()) {

				row = sheet.createRow(index + 1);

				row.createCell(0).setCellValue(key);
				row.createCell(1).setCellValue((String) names.get(key));
				row.createCell(2).setCellValue(results.get(index++));
			}
		}

		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("Result-" + appendStr + ".xlsx"));
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method readNamesOrDates() is use to read names or dates from the file
	 * 
	 * @param file
	 *            to be read
	 * @param readBothCategories
	 *            true the read first two columns from one file else read first
	 *            columns from both files
	 * @return read names or dates
	 */
	public static Object readNamesOrDates(File file, Boolean readBothCategories) {
		Map<String, String> values = new LinkedHashMap<>();
		List<String> list = new LinkedList<>();

		try {
			FileInputStream excelFile = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row = null;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it
			// doesn't start from first few rows
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);

				if (row != null) {
					if (Boolean.TRUE.equals(readBothCategories)) {
						values.put(
								(row.getCell(0) == null
										|| row.getCell(0).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? ""
												: row.getCell(0).getStringCellValue(),
								(row.getCell(1) == null
										|| row.getCell(1).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? ""
												: row.getCell(1).getStringCellValue());
					} else {
						list.add((row.getCell(0) == null
								|| row.getCell(0).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? ""
										: row.getCell(0).getStringCellValue());
					}
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
			return null;
		}

		return Boolean.TRUE.equals(readBothCategories) ? values : list;
	}

}
