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


import java.util.Date;

public class CardRegister extends BorderPane {
    private NewLabel name;
    private int id;
    private String date;

    public CardRegister(String itemName, int id, String date) {
        this.name = new NewLabel(itemName, 24, "#867070", 700);
        this.id = id;
        this.date = date;

        // Set up the card's layout
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        // Set the preferred width and height of the card
        setPrefSize(457, 80);

        // Set up the item name and quantity
        Text ID = new Text("Customer {" + String.valueOf(id) + "}");
        ID.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));
        ID.setFill(Color.web("#867070"));

        Text datelbl = new Text(date);
        datelbl.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        datelbl.setFill(Color.web("#867070"));

        VBox IDBox = new VBox(5, ID);
        IDBox.setAlignment(Pos.TOP_LEFT);

        VBox tgl = new VBox(5, datelbl);
        tgl.setAlignment(Pos.CENTER_LEFT);


        // Combine the nameQuantityBox and imageView in an HBox
        HBox topBox = new HBox(10, IDBox);
        topBox.setAlignment(Pos.TOP_LEFT);

        // Set up the main content of the card
        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(topBox, tgl);
        setCenter(contentPane);

        // Add event handler for mouse click
        setOnMouseClicked(event -> {
            System.out.println("customer clicked!");
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
