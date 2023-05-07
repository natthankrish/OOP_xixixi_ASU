package org.example.program.components;

import java.util.Iterator;
import org.example.program.entities.bills.Bill;
import org.example.program.entities.bills.ReceiptInfo;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Cart extends BorderPane {
    private Bill bill;
    private NewLabel titleLabel;
    private VBox contentPane;

    public Cart(Bill bill) {
        this.bill = bill;
        // Set up the title
        this.titleLabel = new NewLabel("Cart", 44, "#867070", 700);

        // make a scrollable container of the cads;
        this.contentPane = new VBox(10);
      
        // add all the receiptInfo to the scrollable container
        Iterator<ReceiptInfo> iterator = this.bill.getReceipt().iterator();
        while (iterator.hasNext()) {
            ReceiptInfo item = iterator.next();
            ItemCard itemCard = new ItemCard(this.bill, contentPane, item);
            contentPane.getChildren().add(itemCard);
        }
        
        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.setStyle("-fx-background:white;-fx-background-color:transparent;");
        scrollPane.setPrefHeight(520); // Set the preferred height of the scroll pane
        scrollPane.setFitToWidth(true);

        // put the title and the scrollable container in a vbox
        VBox container = new VBox(5, this.titleLabel, scrollPane);
        container.setAlignment(Pos.TOP_LEFT);

        setCenter(container);
        setPrefWidth(470); // Set the width to match the item card size
    
        setOnMouseExited(event -> {
            
        });
    
    }

    // Method to add an item card to the cart
    public void addItemCard(ReceiptInfo receiptInfo) {
        // itemCards.add(itemCard);
        // itemCardsBox.getChildren().add(itemCard);
        ItemCard itemCard = new ItemCard(this.bill, contentPane, receiptInfo);
        contentPane.getChildren().add(itemCard);
    }
    
    // Method to remove an item card from the cart
    public void removeItemCard(ReceiptInfo receiptInfo) {
        // itemCards.remove(itemCard);
        // itemCardsBox.getChildren().remove(itemCard);
        ItemCard itemCard = new ItemCard(this.bill, contentPane, receiptInfo);
        contentPane.getChildren().remove(itemCard);
    }

    public VBox getContentPane(){
        return this.contentPane;
    }
}
