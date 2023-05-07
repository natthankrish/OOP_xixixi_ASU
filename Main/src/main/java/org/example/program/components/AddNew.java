package org.example.program.components;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.example.program.containers.Manager;
import org.example.program.entities.Bill;
import org.example.program.entities.Product;
import org.example.program.entities.ReceiptInfo;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddNew extends BorderPane {

    private Bill bill;
    private Cart cart;
    private Product product = null;
    private Integer quantity;
    
    public AddNew(Bill bill, Cart cart){
        this.bill = bill;
        this.cart = cart;
                
        // Set up the labels
        NewLabel titleLabel = new NewLabel("Add New", 44, "#867070", 700);
        NewLabel idItemLabel = new NewLabel("Item's ID", 20, "#867070", 700);
        NewLabel itemNameLabel = new NewLabel("Item's Name", 20, "#867070", 700);
        NewLabel categoryLabel = new NewLabel("Category", 20, "#867070", 700);
        NewLabel quantityLabel = new NewLabel("Quanitity", 20, "#867070", 700);

        Manager m = Manager.getInstance();
        List<Product> products = m.getInventoryContainer().getBuffer();
        List<String> searchItems = new ArrayList<>();
        for (Product p : products){
            searchItems.add(Integer.toString(p.getId()) + " - " + p.getName() + " - " + p.getCategory());
        }

        SearchBar searchBar = new SearchBar(searchItems);
        CustomButton addButton = new CustomButton("add", 16, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10);
        HBox searchBox = new HBox(5, searchBar, addButton);
        NewLabel IDItemField = new NewLabel(" ", 16, "#867070", 700);
        NewLabel itemNameField = new NewLabel(" ", 16, "#867070", 700);
        NewLabel categoryField = new NewLabel(" ", 16, "#867070", 700);
        
        CustomButton minusButton = new CustomButton("-", 16, "#867070", "#F5EBEB", "bold", 10, 10, 10, 10);
        CustomButton plusButton = new CustomButton("+", 16, "#867070", "#F5EBEB", "bold", 10, 10, 10, 10);
        this.quantity = 0;
        NewLabel quantityNumberLabel = new NewLabel(Integer.toString(quantity), 20, "#867070", 700);
        HBox quantityBox = new HBox(10, minusButton, quantityNumberLabel, plusButton);
        
        NewLabel priceEachLabel = new NewLabel("Price per Quantity", 15, "#867070", 700);
        NewLabel priceEachNumberLabel = new NewLabel("IDR " + Integer.toString(0), 15, "#867070", 700);
        HBox priceBox = new HBox(5, priceEachLabel, priceEachNumberLabel);
        
        NewLabel totalLabel = new NewLabel("TOTAL", 20, "#867070", 700);
        NewLabel priceTotalNumberLabel = new NewLabel("IDR " + Integer.toString(0), 35, "#867070", 700);
        VBox totalBox = new VBox(5, totalLabel, priceTotalNumberLabel);
        
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-background-color: #867070; -fx-text-fill: #F5EBEB; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        HBox bottomBox = new HBox(10, totalBox, addToCartButton);
        
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        
        VBox container = new VBox(5, titleLabel, searchBox, idItemLabel, IDItemField, itemNameLabel, itemNameField, categoryLabel, categoryField, quantityLabel, quantityBox, priceBox, bottomBox);

        setCenter(container);
        setPadding(new Insets(20));

        minusButton.setOnAction(event -> {
            if (quantity > 0) {
                quantity -= 1;
                quantityNumberLabel.setText(Integer.toString(quantity));
                priceTotalNumberLabel.setText("IDR " + Double.toString(quantity * product.getPrice()));
            } 
                System.out.println("Minus clicked!");
            }
        );
        plusButton.setOnAction(event -> {
            quantity += 1;
            quantityNumberLabel.setText(Integer.toString(quantity));
            priceTotalNumberLabel.setText("IDR " + Double.toString(quantity * product.getPrice()));
            System.out.println("Plus clicked!");
        });

        addToCartButton.setOnAction(event -> {
            if (product != null && quantity != 0) {
                Boolean foundSameProduct = false;   
                List<Node> itemCards = cart.getContentPane().getChildren().stream().collect(Collectors.toList());
                for(Node obj : itemCards) {
                    if ( obj instanceof ItemCard){
                        ItemCard card = (ItemCard) obj;
                        ReceiptInfo receipt = card.getReceiptInfo();
                        if(receipt.getProductID().equals(product.getId())){
                            foundSameProduct = true;
                            receipt.setQuantity(receipt.getQuantity() + quantity);
                            System.out.println(quantity);
                            receipt.recalculateSubtotal();
                            bill.recalculateTotalPrice();
                            card.resetQuantity();
                            break;
                        } 
                    }
                }
                        
                if (!foundSameProduct) {
                    ReceiptInfo receipt = new ReceiptInfo(product.getId(), quantity, quantity*product.getPrice());
                    bill.getReceipt().add(receipt);
                    bill.recalculateTotalPrice();
                    cart.addItemCard(receipt);   
                }
            }
        });

        addButton.setOnAction(event -> {
            String text = searchBar.textField.getText();
            if (searchItems.indexOf(text) != -1) {
                product = products.get(searchItems.indexOf(text));   
                IDItemField.setText(Integer.toString(product.getId()));
                itemNameField.setText(product.getName());
                categoryField.setText(product.getCategory());
                priceEachNumberLabel.setText("IDR " + Double.toString(product.getPrice()));
                quantity = 1;
                quantityNumberLabel.setText(Integer.toString(quantity));
                priceTotalNumberLabel.setText("IDR " + Double.toString(product.getPrice()));
            }
        });




    }
}
