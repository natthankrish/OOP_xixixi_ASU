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

public class AddPaymentInfo extends BorderPane {
    private NewLabel titleLabel;
    private NewField IDCustomerField;
    private NewField customerNameField;
    private NewField statusField;
    private String[] idList;
    private String[] customerNameList;
    private String[] statusList;
    
    public AddPaymentInfo(){
        // Set up the labels
        this.titleLabel = new NewLabel("Add New", 44, "#867070", 700);

        // set up the fields
        String [] tes = {"melvin", "melvin","melvin","melvin"};
        this.idList = tes;
        this.customerNameList = tes;
        this.statusList = tes;

        this.IDCustomerField = new NewField("Customer's ID", idList);
        this.customerNameField = new NewField("Customer's Name", customerNameList);
        this.statusField = new NewField("Category", statusList);
        VBox container = new VBox(5);
        container.getChildren().addAll(this.titleLabel, IDCustomerField, customerNameField, statusField);

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
