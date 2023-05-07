package org.example.program.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
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
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Date;

public class CardRegister extends BorderPane {
    private NewLabel itemName;
    private int id;
    private String date;
    private CloseButton closeButton;

    public CardRegister(String itemName, int id, String date) {
        this.itemName = new NewLabel(itemName, 24, "#867070", 700);
        this.id = id;
        this.date = date;
        this.closeButton = new CloseButton();
        this.closeButton.img.setFitWidth(25);
        this.closeButton.img.setFitHeight(25);

        // Set up the card's layout
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        // Set the preferred width and height of the card
        setPrefSize(457, 80);



        // Set up the item name and quantity
        Text quantityText = new Text(String.valueOf(date));
        quantityText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15) );
        quantityText.setFill(Color.web("#867070"));
        Text priceText = new Text("{ID " + String.valueOf(id) + "}");
        priceText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        priceText.setFill(Color.web("#867070"));
        VBox nameQuantityBox = new VBox(5, this.itemName, quantityText);
        nameQuantityBox.setAlignment(Pos.TOP_LEFT);

        // Set up the close button and price
        VBox closeButtonPriceBox = new VBox(5, this.closeButton, priceText);
        closeButtonPriceBox.setAlignment(Pos.TOP_RIGHT);

        // Combine the nameQuantityBox and imageView in an HBox
        HBox topBox = new HBox(10, nameQuantityBox);
        topBox.setAlignment(Pos.TOP_LEFT);

        // Set up the main content of the card
        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(topBox, closeButtonPriceBox);
        setCenter(contentPane);

        // Add event handler for mouse click
        setOnMouseClicked(event -> {
            System.out.println("customer clicked!");
            // Add code here to perform the desired action when the card is click
//            DetailRegister detailRegister = new DetailRegister(itemName, id, date, "Member",6,"11/11/2011", 100000000);
//            StackPane rootPane = (StackPane) getScene().getRoot();
//            rootPane.getChildren().add(detailRegister);
        });

        // Add event handlers for mouse enter and exit
        setOnMouseEntered(event -> {
            setCursor(Cursor.HAND);
            setBackground(new Background(new BackgroundFill(Color.web("EAD7D7"), new CornerRadii(10), Insets.EMPTY)));
        });

        setOnMouseExited(event -> {
            setCursor(Cursor.DEFAULT);
            setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        });
    }


    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
