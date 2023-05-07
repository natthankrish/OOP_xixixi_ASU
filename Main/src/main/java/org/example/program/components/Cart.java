package org.example.program.components;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Cart extends BorderPane {
    private List<ItemCard> itemCards;
    private NewLabel titleLabel;
    private VBox contentPane;

    public Cart(List<ItemCard> cards) {
        // Set up the title and the member
        this.titleLabel = new NewLabel("Cart", 44, "#867070", 700);
        this.itemCards = cards;

        // make a scrollable container of the cads;
        this.contentPane = new VBox(10);
      
        // add all the itemcards to the scrollable container
        contentPane.getChildren().addAll(itemCards);

        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.setPrefHeight(520); // Set the preferred height of the scroll pane
        scrollPane.setFitToWidth(true);



        // put the title and the scrollable container in a vbox
        VBox container = new VBox(5, this.titleLabel, scrollPane);
        container.setAlignment(Pos.TOP_LEFT);

        setCenter(container);
        setPrefWidth(470); // Set the width to match the item card size
    }

    // Method to add an item card to the cart
    public void addItemCard(ItemCard itemCard) {
        itemCards.add(itemCard);
        // itemCardsBox.getChildren().add(itemCard);
        contentPane.getChildren().add(itemCard);
    }
    
    // Method to remove an item card from the cart
    public void removeItemCard(ItemCard itemCard) {
        itemCards.remove(itemCard);
        // itemCardsBox.getChildren().remove(itemCard);
        contentPane.getChildren().add(itemCard);
    }
}
