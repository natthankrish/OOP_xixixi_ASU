package org.example.program.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AddRegister extends BorderPane{
    CustomButton confirm;
    NewLabel name ;
    NewLabel phone;
    NewField customerName;
    NewField phoneNumber;

    public AddRegister(String name,
                       String phone)
    {
        this.confirm = new CustomButton("Confirm Registration",14,"#FFFFFF","#867070", "bold", 10,10,10,10);
        this.name = new NewLabel(name,24, "#867070",700);
        this.phone = new NewLabel(phone, 24, "#867070", 700);
//        this.customerName = new NewField(50,20,"#867070","#FFFFFF",20);
//        this.phoneNumber = new NewField(50, 20, "#867070","#FFFFFF",20);
        this.customerName = new NewField("Your Name...", 100,20);
        this.phoneNumber = new NewField("Your Phone Number...", 100,20);
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER_LEFT);

        // Set the preferred width and height of the card
        setPrefSize(394, 225);

        vBox.getChildren().addAll(this.name, this.customerName, this.phone, this.phoneNumber, this.confirm);
        setCenter(vBox);
    }
    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public CustomButton getConfirm() {
        return confirm;
    }

    public NewField getCustomerName(){
        return this.customerName;
    }
    public NewField getPhoneNumber(){
        return this.phoneNumber;
    }
}