package org.example.program.components;

import org.example.program.containers.Manager;
import org.example.program.entities.Bill;
import org.example.program.entities.ReceiptInfo;
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

public class ItemCard extends BorderPane {
    private Bill bill;
    private VBox parentContentPane;
    private ReceiptInfo receiptInfo;
    private Double price;
    private Integer quantity;
    private Text priceText;
    private Text quantityText;

    public ItemCard(Bill bill, VBox parentContentPane, ReceiptInfo receiptInfo) {
        this.bill = bill;
        this.parentContentPane = parentContentPane;
        this.receiptInfo = receiptInfo;

        Integer itemId = receiptInfo.getProductID();
        Manager m = Manager.getInstance();
        String name = m.getInventoryContainer().getProductById(itemId).getName();
        NewLabel itemName = new NewLabel(name, 24, "#867070", 700);
        NewImage logo = new NewImage("assets/products/No_Image_Available.jpg");
        NewImage gambar = logo;
        this.price = receiptInfo.getSubtotal();
        this.quantity = receiptInfo.getQuantity();
        CustomButton minusButton = new CustomButton("-", 16, "#867070", "#F5EBEB", "bold", 10, 10, 10, 10);
        CustomButton plusButton = new CustomButton("+", 16, "#867070", "#F5EBEB", "bold", 10, 10, 10, 10);
        CustomButton deleteAllButton = new CustomButton("x", 16, "#867070", "#F5EBEB", "bold", 10, 10, 10, 10);

        // Set up the card's layout
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        
        // Set the preferred width and height of the card
        setPrefSize(457, 80);


        gambar.setDimension(57, 57);

        // Set up the item name and quantity
        this.quantityText = new Text("Quantity: " + String.valueOf(quantity));
        quantityText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15) );
        quantityText.setFill(Color.web("#867070"));
        this.priceText = new Text("IDR " + String.valueOf(price));
        priceText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        priceText.setFill(Color.web("#867070"));
        VBox nameQuantityBox = new VBox(5, itemName, quantityText);
        nameQuantityBox.setAlignment(Pos.TOP_LEFT);

        // Set up button box
        HBox buttonBox = new HBox(5, minusButton, plusButton, deleteAllButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        // Set up the minus, plus button and price
        VBox buttonPriceBox = new VBox(5, priceText, buttonBox);
        buttonPriceBox.setAlignment(Pos.TOP_RIGHT);

        // Combine the nameQuantityBox and imageView in an HBox
        HBox topBox = new HBox(10, gambar, nameQuantityBox);
        topBox.setAlignment(Pos.TOP_LEFT);

        // Set up the main content of the card
        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(topBox, buttonPriceBox);
        setCenter(contentPane);

        // Add event handler for mouse click
        setOnMouseClicked(event -> {
            System.out.println("ItemCard clicked!");
            // Add code here to perform the desired action when the card is clicked
        });
        minusButton.setOnAction(event -> {
            if (receiptInfo.getQuantity() > 1) {
                receiptInfo.setQuantity(receiptInfo.getQuantity()-1);
                receiptInfo.recalculateSubtotal();
                bill.recalculateTotalPrice();
                priceText.setText("IDR " + Double.toString(receiptInfo.getSubtotal()));
                quantityText.setText("Quantity: " + Integer.toString(receiptInfo.getQuantity()));
            } else {
                // receiptInfo.setQuantity(0);
                // receiptInfo.recalculateSubtotal();
                parentContentPane.getChildren().remove(this);
                this.bill.getReceipt().remove(receiptInfo);
                bill.recalculateTotalPrice();
            }
                System.out.println("Minus clicked!");
            }
        );
        plusButton.setOnAction(event -> {
            receiptInfo.setQuantity(receiptInfo.getQuantity()+1);
            receiptInfo.recalculateSubtotal();
            bill.recalculateTotalPrice();
            priceText.setText("IDR " + Double.toString(receiptInfo.getSubtotal()));
            quantityText.setText("Quantity: " + Integer.toString(receiptInfo.getQuantity()));
            System.out.println("Plus clicked!");
        });
        deleteAllButton.setOnAction(event -> {
            this.bill.getReceipt().remove(receiptInfo);
            this.bill.recalculateTotalPrice();
            parentContentPane.getChildren().remove(this);
            System.out.println("Delete all clicked!");
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
    public ReceiptInfo getReceiptInfo(){
        return receiptInfo;
    }

    public void resetQuantity(){
        this.receiptInfo.recalculateSubtotal();
        this.quantity = this.receiptInfo.getQuantity();
        this.price = this.receiptInfo.getSubtotal();
        this.priceText.setText("IDR " + Double.toString(this.price));
        this.quantityText.setText("Quantitiy: " + Integer.toString(this.quantity));
    } 
}
