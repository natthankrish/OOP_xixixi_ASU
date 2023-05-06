package program.page;
import java.util.ArrayList;

import javafx.stage.Screen;
import program.components.AddNew;
import program.components.Cart;
import program.components.ItemCard;
import program.components.NewImage;
import program.components.NewLabel;
import program.components.PaymentNominal;

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
        this.getChildren().add(cart);


        // AddNew addNew = new AddNew();
        // addNew.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 2);
        // addNew.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        // addNew.addToList("1", "melvin", "manusia");
        // this.getChildren().add(addNew);

        

        PaymentNominal paymentNominal = new PaymentNominal(0, 0, 0);
        paymentNominal.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 2);
        paymentNominal.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        paymentNominal.setCustomerPayNominal(50000);
        this.getChildren().add(paymentNominal);
    }
}
