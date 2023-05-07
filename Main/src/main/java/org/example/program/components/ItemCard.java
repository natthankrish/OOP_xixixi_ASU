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

public class ItemCard extends BorderPane {
    private NewLabel itemName;
    private NewImage image;
    private int price;
    private int quantity;
    private CloseButton closeButton;

    public ItemCard(String itemName, NewImage image, int price, int quantity) {
        this.itemName = new NewLabel(itemName, 24, "#867070", 700);
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.closeButton = new CloseButton();
        this.closeButton.img.setFitWidth(25);
        this.closeButton.img.setFitHeight(25);

        // Set up the card's layout
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        
        // Set the preferred width and height of the card
        setPrefSize(457, 80);


        image.setDimension(57, 57);

        // Set up the item name and quantity
        Text quantityText = new Text("Quantity: " + String.valueOf(quantity));
        quantityText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15) );
        quantityText.setFill(Color.web("#867070"));
        Text priceText = new Text("IDR " + String.valueOf(price));
        priceText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        priceText.setFill(Color.web("#867070"));
        VBox nameQuantityBox = new VBox(5, this.itemName, quantityText);
        nameQuantityBox.setAlignment(Pos.TOP_LEFT);

        // Set up the close button and price
        VBox closeButtonPriceBox = new VBox(5, this.closeButton, priceText);
        closeButtonPriceBox.setAlignment(Pos.TOP_RIGHT);

        // Combine the nameQuantityBox and imageView in an HBox
        HBox topBox = new HBox(10, image, nameQuantityBox);
        topBox.setAlignment(Pos.TOP_LEFT);

        // Set up the main content of the card
        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(topBox, closeButtonPriceBox);
        setCenter(contentPane);

        // Add event handler for mouse click
        setOnMouseClicked(event -> {
            System.out.println("ItemCard clicked!");
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

    // Getter and setter methods for the properties
}
