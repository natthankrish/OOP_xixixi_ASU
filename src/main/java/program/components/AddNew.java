package program.components;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddNew extends BorderPane {
    private NewLabel titleLabel;
    private NewField IDItemField;
    private NewField itemNameField;
    private NewField categoryField;
    private String[] idList;
    private String[] itemNameList;
    private String[] categoryList;
    private NewLabel quantityLabel;
    private NewLabel priceEachLabel;
    private NewLabel priceTotalLabel;
    private int quantity;
    private int priceQuantity;
    
    public AddNew(){
        // Set up the labels
        this.titleLabel = new NewLabel("Add New", 44, "#867070", 700);
        // NewLabel idItemLabel = new NewLabel("Item's ID", 20, "#867070", 700);
        // NewLabel itemNameLabel = new NewLabel("Item's Name", 20, "#867070", 700);
        // NewLabel categoryLabel = new NewLabel("Category", 20, "#867070", 700);

        String [] tes = {"melvin", "melvin","melvin","melvin"};
        this.idList = tes;
        this.itemNameList = tes;
        this.categoryList = tes;

        this.IDItemField = new NewField("Item's ID", idList);
        this.itemNameField = new NewField("Item's Name", itemNameList);
        this.categoryField = new NewField("Category", categoryList);
        VBox container = new VBox(5, this.titleLabel, IDItemField, itemNameField, categoryField);

        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));


        setCenter(container);
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
