package com.excelcomparison.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.excelcomparison.controller.EmployeeController;
import com.excelcomparison.model.Employee;
import com.excelcomparison.util.FileUtil;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Test extends Application {

	File datasetA = null;
	File datasetB = null;
	File nameDataSet = null;
	
	EmployeeController employeeController = new EmployeeController();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Excel Comparator");
		
		Label label = new Label("Please select names dataset");
		
		Button startBtn = new Button("Start Comparison");
		startBtn.setDisable(Boolean.TRUE);
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
				label.setText("Comparing .....");
				
				startNameComparision();
				
				label.setText("Comparison DONE!");
				
            }
		});
		
		Button buttonDSA = new Button("Select Name Dataset");
		buttonDSA.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Microsoft", "*.xls", "*.xlsx"));
				nameDataSet = fileChooser.showOpenDialog(null);
				
				if(nameDataSet != null) {
					startBtn.setDisable(Boolean.FALSE);
				}
			}
		});
		
		/*Button buttonDSB = new Button("Select Dataset B");
		buttonDSB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Microsoft", "*.xls", "*.xlsx"));
				datasetB = fileChooser.showOpenDialog(null);
				
				if(datasetB != null && datasetA != null) {
					startBtn.setDisable(Boolean.FALSE);
				}
			}
		});*/
		
		StackPane root = new StackPane();
		
		VBox vb = new VBox(10);
		
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(label, buttonDSA, startBtn);
		
        root.getChildren().addAll(vb);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
		
	}
	
	private void startComparison() {
		
		Map<Integer, Employee> employeesA = FileUtil.readFile(datasetA);
		Map<Integer, Employee> employeesB = FileUtil.readFile(datasetB);
		
		Map<Integer, Employee> employeesC = employeeController.compare(employeesA, employeesB);
		
		FileUtil.writeIntoFile(new ArrayList<Employee>(employeesC.values()));
		
	}
	
	private void startNameComparision() {
		
		Map<String, String> names = FileUtil.readNames(nameDataSet);
		
		List<String> results = employeeController.compareNames(names);
		
		if(results != null && !results.isEmpty()) {
			FileUtil.writeNamesResult(names, results);
		}
		
	}

}
