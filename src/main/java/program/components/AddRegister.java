package program.components;

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
        this.confirm = new CustomButton("Confirm Registration",16,"#FFFFFF","#867070", "bold", 10,10,10,10);
        this.name = new NewLabel(name,24, "#867070",700);
        this.phone = new NewLabel(phone, 24, "#867070", 700);
        this.customerName = new NewField(300,30,"#867070","#FFFFFF",20);
        this.phoneNumber = new NewField(300, 30, "#867070","#FFFFFF",20);
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        // Set the preferred width and height of the card
        setPrefSize(394, 225);

        VBox register = new VBox(5,
                this.name,
                this.customerName,
                this.phone,
                this.phoneNumber);
        register.setAlignment(Pos.TOP_LEFT);

        VBox confirm = new VBox(5,this.confirm);
        confirm.setAlignment(Pos.BOTTOM_RIGHT);

        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(register,confirm);
        setCenter(contentPane);

//        setOnMouseClicked(event -> {
//            System.out.println("customer clicked!");
//            // Add code here to perform the desired action when the card is click
//        });
//
//        // Add event handlers for mouse enter and exit
//        setOnMouseEntered(event -> {
//            setCursor(Cursor.HAND);
//            setBackground(new Background(new BackgroundFill(Color.web("EAD7D7"), new CornerRadii(10), Insets.EMPTY)));
//        });
//
//        setOnMouseExited(event -> {
//            setCursor(Cursor.DEFAULT);
//            setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
//        });
    }
    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
