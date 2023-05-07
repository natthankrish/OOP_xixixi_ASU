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

public class CardMember extends BorderPane {
    private NewLabel customerName;
    private int id;
    private String phone;

    public CardMember(String customerName, int id, String phone) {
        this.customerName = new NewLabel(customerName, 24, "#867070", 700);
        this.id = id;
        this.phone = phone;
//        this.closeButton = new CloseButton();
//        this.closeButton.img.setFitWidth(25);
//        this.closeButton.img.setFitHeight(25);

        // Set up the card's layout
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        // Set the preferred width and height of the card
        setPrefSize(486, 80);


        // Set up the item name and quantity
        Text quantityText = new Text("Phone: " + String.valueOf(phone));
        quantityText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15) );
        quantityText.setFill(Color.web("#867070"));
        Text priceText = new Text("ID " + String.valueOf(id));
        priceText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        priceText.setFill(Color.web("#867070"));
        VBox nameQuantityBox = new VBox(5, this.customerName, quantityText);
        nameQuantityBox.setAlignment(Pos.TOP_LEFT);

        // Set up the close button and price
        VBox closeButtonPriceBox = new VBox(5, priceText);
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
            // Add code here to perform the desired action when the card is clicked
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
    // Getter and setter methods for the properties
}