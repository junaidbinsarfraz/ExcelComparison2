package com.excelcomparison.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.excelcomparison.model.Employee;
import com.excelcomparison.util.Constants;
import com.excelcomparison.util.NameUtil;

public class EmployeeController {
	
	public List<String> compareNames(Map<String, String> names) {
		List<String> results = new LinkedList<>();
		
		try {
			
			for(String rawName : names.keySet()) {
				String result = "";
				String edittedName = names.get(rawName);
				
				if(rawName != null && rawName != "" && edittedName != null && edittedName != "" && !edittedName.equals(rawName)) {
					String shortRawName = NameUtil.getShorterName(rawName);
					
					if(edittedName.equals(shortRawName)) {
						result = Constants.TRANSFORMATION;
					} else if(rawName.equals(shortRawName)) {
						result = Constants.CORRECTION;
					} else {
						result = Constants.CORRECTION_TRANSFORMATION;
					}
				}
				
				results.add(result);
			}
			
		} catch(Exception e) {
			return null;
		}
		
		return results;
	}

	public Map<Integer, Employee> compare(Map<Integer, Employee> employeesA, Map<Integer, Employee> employeesB) {

		Map<Integer, Employee> employees = new HashMap(0);

		try {

			if (employeesA == null || employeesB == null) {
				return null;
			}

			// All employees are added
			if (employeesA == null || employeesA.size() == 0) {
				// All in employees B are added

				for (Employee employeeB : employeesB.values()) {
					employeeB.setStatus(Constants.STATUS_ADDED);
					employeeB.setRemarks(Constants.ADD_FALSE_NEGATIVE);
					employees.put(employeeB.getRowId(), employeeB);
				}

				return employees;
			}

			// All employees are removed
			if (employeesB == null || employeesB.size() == 0) {
				// All in employees B are added

				for (Employee employeeA : employeesA.values()) {
					employeeA.setStatus(Constants.STATUS_DELETED);
					employeeA.setRemarks(Constants.DELETE_FALSE_POSITIVE);
					employees.put(employeeA.getRowId(), employeeA);
				}

				return employees;
			}

			// Check removed
			for (Employee employeeA : employeesA.values()) {

				Employee employeeB = employeesB.get(employeeA.getRowId());

				if (employeeB == null) {
					Employee employee = (Employee) employeeA.clone();

					employee.setRemarks(Constants.DELETE_FALSE_POSITIVE);

					// Check for a reason
					
					for (Employee tempEmployeeB : employeesB.values()) {
						if (employeeA.getFirstName().equals(tempEmployeeB.getFirstName())
								&& employeeA.getLastName().equals(tempEmployeeB.getLastName())) {
							// Duplicate
							employee.setRemarks(Constants.DELETE_UPDATE);
							int count = 0;
							for (Employee tempEmployeeA : employeesA.values()) {
								if (employeeA.getFirstName().equals(tempEmployeeA.getFirstName())
										&& employeeA.getLastName().equals(tempEmployeeA.getLastName())) {
									count++;
								}
							}
							if(count > 1) {
								employee.setRemarks(Constants.DELETE_DUPLICATE);
							}
						}
					}
					
					employee.setStatus(Constants.STATUS_DELETED);

					employees.put(employee.getRowId(), employee);
				}
			}

			// Check added
			for (Employee employeeB : employeesB.values()) {

				Employee employeeA = employeesA.get(employeeB.getRowId());

				if (employeeA == null) {
					Employee employee = (Employee) employeeB.clone();
					
					employee.setRemarks(Constants.ADD_FALSE_NEGATIVE);
					
					// Check for false negative
					for (Employee tempEmployeeA : employeesA.values()) {
						if (employeeB.getFirstName().equals(tempEmployeeA.getFirstName())
								&& employeeB.getLastName().equals(tempEmployeeA.getLastName())) {
							// Duplicate
							employee.setRemarks(Constants.ADD_UPDATE);
						}
					}
					
					employee.setStatus(Constants.STATUS_ADDED);

					employees.put(employee.getRowId(), employee);
				}
			}

			// Check matched

			// Check updated
			for (Employee employeeA : employeesA.values()) {

				Employee employeeB = employeesB.get(employeeA.getRowId());

				if (employeeB != null && !(employeeA.equals(employeeB))) {
					
					String remarks = "";
					
					// Check for column name(s)
					if (!(employeeA.getFirstName().equals(employeeB.getFirstName()))) {
						remarks += " FirstName ";
					}
					if (!(employeeA.getLastName().equals(employeeB.getLastName()))) {
						remarks += " LastName ";
					}
					if (!(employeeA.getPosition().equals(employeeB.getPosition()))) {
						remarks += " Position ";
					}
					if (!(employeeA.getDepartment().equals(employeeB.getDepartment()))) {
						remarks += " Department ";
					}
					if (!(employeeA.getDesignation().equals(employeeB.getDesignation()))) {
						remarks += " Designation ";
					}

					Employee employee = (Employee) employeeA.clone();

					employee.setStatus(Constants.STATUS_UPDATED);
					employee.setRemarks(remarks);
					
					employees.put(employee.getRowId(), employee);
				}
			}

		} catch (Exception e) {
			return null;
		}

		return employees;
	}

}
