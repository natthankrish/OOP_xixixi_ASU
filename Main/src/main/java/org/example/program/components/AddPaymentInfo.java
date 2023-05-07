package org.example.program.components;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddPaymentInfo extends BorderPane {
    private NewLabel titleLabel;
    private DropDown IDCustomerField;
    private DropDown customerNameField;
    private DropDown statusField;
    private String[] idList;
    private String[] customerNameList;
    private String[] statusList;
    
    public AddPaymentInfo(){
        // Set up the labels
        this.titleLabel = new NewLabel("Add New", 44, "#867070", 700);
        NewLabel idCustomLabel = new NewLabel("Customer's ID", 20, "#867070", 700);
        NewLabel customerNameLabel = new NewLabel("Customer's Name", 20, "#867070", 700);
        NewLabel statusLabel = new NewLabel("Status", 20, "#867070", 700);

        // set up the fields
        String [] tes = {"melvin", "melvin","melvin","melvin"};
        this.idList = tes;
        this.customerNameList = tes;
        this.statusList = tes;

        this.IDCustomerField = new DropDown(idList);
        this.customerNameField = new DropDown(customerNameList);
        this.statusField = new DropDown(statusList);
        VBox container = new VBox(5);


        NewLabel usePointsLabel = new NewLabel("Use Points", 20, "#867070", 700);
        CheckBox checkBox = new CheckBox("Check me");
        HBox bottomBox = new HBox(10, checkBox, usePointsLabel);

        container.getChildren().addAll(this.titleLabel, idCustomLabel, IDCustomerField, customerNameLabel, customerNameField, statusLabel, statusField, bottomBox);
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));


        setCenter(container);
    }

    public void addToList(String id, String name, String category){
        String[] newIdList = new String[idList.length + 1];
        String[] newCustomerNameList = new String[customerNameList.length + 1];
        String[] newStatusList = new String[statusList.length + 1];

        // Copy the elements from the original array to the new array
        for (int i = 0; i < idList.length; i++) {
            newIdList[i] = idList[i];
        }
        for (int i = 0; i < customerNameList.length; i++) {
            newCustomerNameList[i] = customerNameList[i];
        }
        for (int i = 0; i < statusList.length; i++) {
            newStatusList[i] = statusList[i];
        }

        // Add the new element at the end of the new array
        newIdList[newIdList.length - 1] = id;
        newCustomerNameList[newCustomerNameList.length - 1] = name;
        newStatusList[newStatusList.length - 1] = category;

        // Replace the original array with the new array
        this.idList = newIdList;
        this.customerNameList = newCustomerNameList;
        this.statusList = newStatusList;  
    }
}
