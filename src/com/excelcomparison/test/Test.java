package com.excelcomparison.test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.excelcomparison.controller.EmployeeController;
import com.excelcomparison.util.FileUtil;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The class Test is use to make an interface for user and let user to comapare names and dates
 * @author Junaid
 */
public class Test extends Application {

	File datasetA = null;
	File datasetB = null;
	File dataSet = null;
	
	EmployeeController employeeController = new EmployeeController();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Excel Comparator");
		
		Label label = new Label("Please select dataset");
	
		// Left side of grid
		Button startNameBtn = new Button("Start Name Comparison");
		startNameBtn.setDisable(Boolean.TRUE);
		startNameBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
				label.setText("Comparing .....");
				
				startNameComparision(Boolean.TRUE);
				
				label.setText("Comparison DONE!");
				
            }
		});
		
		Button startDateBtn = new Button("Start Date Comparison");
		startDateBtn.setDisable(Boolean.TRUE);
		startDateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
				label.setText("Comparing .....");
				
				startDateComparision(Boolean.TRUE);
				
				label.setText("Comparison DONE!");
				
            }
		});
		
		Button buttonDS = new Button("Select Dataset");
		buttonDS.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Microsoft", "*.xls", "*.xlsx"));
				dataSet = fileChooser.showOpenDialog(null);
				
				if(dataSet != null) {
					startNameBtn.setDisable(Boolean.FALSE);
					startDateBtn.setDisable(Boolean.FALSE);
				}
			}
		});
		
		// Right side of grid
		
		Label label1 = new Label("Please select datasets");
		
		Button startNameBtn1 = new Button("Start Name Comparison");
		startNameBtn1.setDisable(Boolean.TRUE);
		startNameBtn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
				label1.setText("Comparing .....");
				
				startNameComparision(Boolean.FALSE);
				
				label1.setText("Comparison DONE!");
				
            }
		});
		
		Button startDateBtn1 = new Button("Start Date Comparison");
		startDateBtn1.setDisable(Boolean.TRUE);
		startDateBtn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
				label1.setText("Comparing .....");
				
				startDateComparision(Boolean.FALSE);
				
				label1.setText("Comparison DONE!");
				
            }
		});
		
		Button buttonDSA = new Button("Select Dataset A");
		buttonDSA.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Microsoft", "*.xls", "*.xlsx"));
				datasetA = fileChooser.showOpenDialog(null);
				
				if(datasetA != null && datasetB != null) {
					startNameBtn1.setDisable(Boolean.FALSE);
					startDateBtn1.setDisable(Boolean.FALSE);
				}
			}
		});
		
		Button buttonDSB = new Button("Select Dataset B");
		buttonDSB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Microsoft", "*.xls", "*.xlsx"));
				datasetB = fileChooser.showOpenDialog(null);
				
				if(datasetB != null && datasetA != null) {
					startNameBtn1.setDisable(Boolean.FALSE);
					startDateBtn1.setDisable(Boolean.FALSE);
				}
			}
		});

		// Boxing the element in vertical order
		VBox vb1 = new VBox(10);
		
		vb1.getChildren().addAll(label1, buttonDSA, buttonDSB, startNameBtn1, startDateBtn1);
		
		StackPane root = new StackPane();
		
		VBox vb = new VBox(10);
		
		vb.getChildren().addAll(label, buttonDS, startNameBtn, startDateBtn);
		
		GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(2);
        grid.setHgap(5);
        
        final Separator sepVert1 = new Separator();
        sepVert1.setOrientation(Orientation.VERTICAL);
        sepVert1.setValignment(VPos.CENTER);
        sepVert1.setPrefHeight(180);
        GridPane.setConstraints(sepVert1, 2, 2);
        GridPane.setRowSpan(sepVert1, 2);
		
        grid.setRowIndex(vb, 0);
        grid.setColumnIndex(vb, 0);
        
        grid.getChildren().add(0, vb);
        
        grid.setRowIndex(sepVert1, 0);
        grid.setColumnIndex(sepVert1, 1);
        
        grid.getChildren().add(1, sepVert1);
        
        grid.setRowIndex(vb1, 0);
        grid.setColumnIndex(vb1, 2);
        
        grid.getChildren().add(2, vb1);
        
        root.getChildren().addAll(grid);
        primaryStage.setScene(new Scene(root, 350, 250));
        primaryStage.show();
		
	}

	/**
	 * The method startNameComparision() is use to compare employees' name and
	 * collect result to show on the interface
	 * 
	 * @param readBothCategories
	 *            true the read first two columns from one file else read first
	 *            columns from both files
	 */
	private void startNameComparision(Boolean readBothCategories) {
		
		Map<String, String> names = null;
		
		if(Boolean.TRUE.equals(readBothCategories)) {
			names = (Map<String, String>) FileUtil.readNamesOrDates(dataSet, Boolean.TRUE);
		} else {
			List<String> names1 = (List<String>) FileUtil.readNamesOrDates(datasetA, Boolean.FALSE);
			List<String> names2 = (List<String>) FileUtil.readNamesOrDates(datasetB, Boolean.FALSE);
			
			names = new LinkedHashMap<>();
			
			for(int i = 0; i < names1.size(); i++) {
				names.put(names1.get(i), names2.get(i));
			}
		}
		
		List<String> results = employeeController.compareNames(names);
		
		if(results != null && !results.isEmpty()) {
			FileUtil.writeNamesOrDatesResult(names, results, Boolean.TRUE);
		}
		
	}
	
	/**
	 * The method startDateComparision is use to compare employees' date and
	 * collect result to show on the interface
	 * 
	 * @param readBothCategories
	 *            true the read first two columns from one file else read first
	 *            columns from both files
	 */
	private void startDateComparision(Boolean readBothCategories) {
		
		Map<String, String> dates = null;
		
		if(Boolean.TRUE.equals(readBothCategories)) {
			dates = (Map<String, String>) FileUtil.readNamesOrDates(dataSet, Boolean.TRUE);
		} else {
			List<String> dates1 = (List<String>) FileUtil.readNamesOrDates(datasetA, Boolean.FALSE);
			List<String> dates2 = (List<String>) FileUtil.readNamesOrDates(datasetB, Boolean.FALSE);
			
			dates = new LinkedHashMap<>();
			
			for(int i = 0; i < dates1.size(); i++) {
				dates.put(dates1.get(i), dates2.get(i));
			}
		}
		
		List<String> results = employeeController.compareDates(dates);
		
		if(results != null && !results.isEmpty()) {
			FileUtil.writeNamesOrDatesResult(dates, results, Boolean.FALSE);
		}
		
	}

}
