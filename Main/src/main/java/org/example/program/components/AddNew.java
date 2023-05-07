package org.example.program.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddNew extends BorderPane {
    private NewLabel titleLabel;
    private DropDown IDItemField;
    private DropDown itemNameField;
    private DropDown categoryField;
    private String[] idList;
    private String[] itemNameList;
    private String[] categoryList;
    private NewLabel quantityNumberLabel;
    private NewLabel priceEachNumberLabel;
    private NewLabel priceTotalNumberLabel;
    private int quantity;
    private int priceQuantity;
    
    public AddNew(){
        // Set up the labels
        this.titleLabel = new NewLabel("Add New", 44, "#867070", 700);
        NewLabel idItemLabel = new NewLabel("Item's ID", 20, "#867070", 700);
        NewLabel itemNameLabel = new NewLabel("Item's Name", 20, "#867070", 700);
        NewLabel categoryLabel = new NewLabel("Category", 20, "#867070", 700);
        NewLabel quantityLabel = new NewLabel("Quanitity", 20, "#867070", 700);
        
        String [] tes = {"melvin"};
        this.idList = tes;
        this.itemNameList = tes;
        this.categoryList = tes;

        this.IDItemField = new DropDown(idList);
        this.itemNameField = new DropDown(itemNameList);
        this.categoryField = new DropDown(categoryList);

        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-background-color: #867070; -fx-text-fill: #F5EBEB; -fx-font-weight: bold;");
        this.quantityNumberLabel = new NewLabel(Integer.toString(quantity), 20, "#867070", 700);
        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-background-color: #867070; -fx-text-fill: #F5EBEB; -fx-font-weight: bold;");
        HBox quantityBox = new HBox(10, minusButton, this.quantityNumberLabel, plusButton);
        

        NewLabel priceEachLabel = new NewLabel("Price per Quantity", 15, "#867070", 700);
        this.priceEachNumberLabel = new NewLabel("IDR " + Integer.toString(priceQuantity), 15, "#867070", 700);
        HBox priceBox = new HBox(5, priceEachLabel, this.priceEachNumberLabel);
        
        NewLabel totalLabel = new NewLabel("TOTAL", 20, "#867070", 700);
        this.priceTotalNumberLabel = new NewLabel("IDR " + Integer.toString(this.quantity * this.priceQuantity), 35, "#867070", 700);
        VBox totalBox = new VBox(5, totalLabel, this.priceTotalNumberLabel);
        
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-background-color: #867070; -fx-text-fill: #F5EBEB; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        HBox bottomBox = new HBox(10, totalBox, addToCartButton);
        
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        
        VBox container = new VBox(5, this.titleLabel, idItemLabel, IDItemField, itemNameLabel, itemNameField, categoryLabel, categoryField, quantityLabel, quantityBox, priceBox, bottomBox);

        setCenter(container);
        setPadding(new Insets(20));
    }

    public void addToList(String id, String name, String category){
        String[] newIdList = new String[idList.length + 1];
        String[] newItemNameList = new String[itemNameList.length + 1];
        String[] newCategoryList = new String[categoryList.length + 1];

        // Copy the elements from the original array to the new array
        for (int i = 0; i < idList.length; i++) {
            newIdList[i] = idList[i];
        }
        for (int i = 0; i < itemNameList.length; i++) {
            newItemNameList[i] = itemNameList[i];
        }
        for (int i = 0; i < categoryList.length; i++) {
            newCategoryList[i] = categoryList[i];
        }

        // Add the new element at the end of the new array
        newIdList[newIdList.length - 1] = id;
        newItemNameList[newItemNameList.length - 1] = name;
        newCategoryList[newCategoryList.length - 1] = category;

        // Replace the original array with the new array
        this.idList = newIdList;
        this.itemNameList = newItemNameList;
        this.categoryList = newCategoryList;  
    }
}
