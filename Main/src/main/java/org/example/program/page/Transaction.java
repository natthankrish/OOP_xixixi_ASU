package org.example.program.page;

import java.util.ArrayList;

import org.example.program.components.AddNew;
import org.example.program.components.AddPaymentInfo;
import org.example.program.components.Cart;
import org.example.program.components.ItemCard;
import org.example.program.components.NewImage;
import org.example.program.components.NewLabel;
import org.example.program.components.PaymentNominal;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class Transaction extends BasePage {
    public Transaction() {
        this.changeBackground("white");
        
        // Set the page title
        NewLabel title = new NewLabel("Transaction", 60, "#867070", 700);
        title.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 25);
        this.getChildren().add(title);

        NewImage logo = new NewImage("assets/products/No_Image_Available.jpg");
        ItemCard itemcard1 = new ItemCard("melvin", logo, 50000, 5);
        ItemCard itemcard2 = new ItemCard("melvin", logo, 50000, 5);
        ItemCard itemcard3 = new ItemCard("melvin", logo, 50000, 5);
        ItemCard itemcard4 = new ItemCard("melvin", logo, 50000, 5);
        ItemCard itemcard5 = new ItemCard("melvin", logo, 50000, 5);
        ItemCard itemcard6 = new ItemCard("melvin", logo, 50000, 5);
        ItemCard itemcard7 = new ItemCard("melvin", logo, 50000, 5);
        ItemCard itemcard8 = new ItemCard("melvin", logo, 50000, 5);
        ItemCard itemcard9 = new ItemCard("melvin", logo, 50000, 5);
        // itemcard1.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 10);
        // itemcard1.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 5 / 15);
        // this.getChildren().add(itemcard1);

        ArrayList<ItemCard> itemCards = new ArrayList<ItemCard>();
        Cart cart = new Cart(itemCards);
        cart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        cart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        cart.addItemCard(itemcard1);
        cart.addItemCard(itemcard2);
        cart.addItemCard(itemcard3);
        cart.addItemCard(itemcard4);
        cart.addItemCard(itemcard5);
        cart.addItemCard(itemcard6);
        cart.addItemCard(itemcard7);
        cart.addItemCard(itemcard8);
        cart.addItemCard(itemcard9);
        
        // PAGE 1
        AddNew addNew = new AddNew();
        addNew.addToList("1", "melvin", "manusia");
        
        Button checkoutButton = new Button("Proceed To Checkout");
        checkoutButton.setStyle("-fx-background-color: #867070; -fx-text-fill: #F5EBEB; -fx-font-size: 30px; -fx-font-weight: bold;");
        VBox rightSide = new VBox(10, addNew, checkoutButton);
        rightSide.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        rightSide.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        
        this.getChildren().addAll(cart, rightSide);
        
        // PAGE 2
        AddPaymentInfo addPaymentInfo = new AddPaymentInfo();
        addPaymentInfo.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        addPaymentInfo.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        
        PaymentNominal paymentNominal = new PaymentNominal(0, 0, 0);
        paymentNominal.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        paymentNominal.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        paymentNominal.setCustomerPayNominal(50000);
        
        // this.getChildren().addAll(addPaymentInfo, paymentNominal);
        
    }
}
