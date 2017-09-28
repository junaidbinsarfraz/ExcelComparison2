package com.excelcomparison.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.excelcomparison.model.Employee;

public class FileUtil {
	
	public static Map<String, String> readNames(File file) {
		Map<String, String> names = new LinkedHashMap<>();
		
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
					
					names.put((row.getCell(1) == null || row.getCell(1).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(1).getStringCellValue(), 
							(row.getCell(2) == null || row.getCell(2).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(2).getStringCellValue());
					
					/*Employee employee = new Employee();

					employee.setRowId((int) row.getCell(0).getNumericCellValue());
					employee.setFirstName((row.getCell(1) == null || row.getCell(1).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(1).getStringCellValue());
					employee.setLastName((row.getCell(2) == null || row.getCell(2).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(2).getStringCellValue());
					employee.setPosition((row.getCell(3) == null || row.getCell(3).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(3).getStringCellValue());
					employee.setDesignation((row.getCell(4) == null || row.getCell(4).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(4).getStringCellValue());
					employee.setDepartment((row.getCell(5) == null || row.getCell(5).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(5).getStringCellValue());

					employees.put(employee.getRowId(), employee);*/
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
			return null;
		}

		return names;
	}

	public static Map<Integer, Employee> readFile(File file) {

		Map<Integer, Employee> employees = new HashMap(0);

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
					Employee employee = new Employee();

					employee.setRowId((int) row.getCell(0).getNumericCellValue());
					employee.setFirstName((row.getCell(1) == null || row.getCell(1).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(1).getStringCellValue());
					employee.setLastName((row.getCell(2) == null || row.getCell(2).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(2).getStringCellValue());
					employee.setPosition((row.getCell(3) == null || row.getCell(3).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(3).getStringCellValue());
					employee.setDesignation((row.getCell(4) == null || row.getCell(4).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(4).getStringCellValue());
					employee.setDepartment((row.getCell(5) == null || row.getCell(5).getCellTypeEnum().equals(XSSFCell.CELL_TYPE_BLANK)) ? "" : row.getCell(5).getStringCellValue());

					employees.put(employee.getRowId(), employee);
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
			return null;
		}

		return employees;
	}

	public static void writeIntoFile(List<Employee> employees) {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Result");

		if (employees != null && employees.size() > 0) {

			for (int i = -1; i < employees.size(); i++) {
				Row row = sheet.createRow(i + 1);
				// Header
				if (i == -1) {
					row.createCell(0).setCellValue("Rowid");
					row.createCell(1).setCellValue("FirstName");
					row.createCell(2).setCellValue("LastName");
					row.createCell(3).setCellValue("Position");
					row.createCell(4).setCellValue("Designation");
					row.createCell(5).setCellValue("Department");
					row.createCell(6).setCellValue("Status");
					row.createCell(7).setCellValue("Remarks");
				} else {
					row.createCell(0).setCellValue(employees.get(i).getRowId());
					row.createCell(1).setCellValue(employees.get(i).getFirstName());
					row.createCell(2).setCellValue(employees.get(i).getLastName());
					row.createCell(3).setCellValue(employees.get(i).getPosition());
					row.createCell(4).setCellValue(employees.get(i).getDesignation());
					row.createCell(5).setCellValue(employees.get(i).getDepartment());
					row.createCell(6).setCellValue(employees.get(i).getStatus());
					row.createCell(7).setCellValue(employees.get(i).getRemarks());
				}
			}
		}

		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("Result.xlsx"));
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
