package org.example.program.page;

import java.util.List;
import org.example.program.components.AddNew;
import org.example.program.components.AddPaymentInfo;
import org.example.program.components.Cart;
import org.example.program.components.CustomButton;
import org.example.program.components.NewLabel;
import org.example.program.components.PaymentNominal;
import org.example.program.containers.Manager;
import org.example.program.entities.bills.Bill;
import org.example.program.entities.bills.ReceiptInfo;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.VIP;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class Transaction extends BasePage {
    private Client client;
    private Bill bill;
    private Double reducePoints = 0.0;
    public Transaction() {
        this.client = null;
        Manager m = Manager.getInstance();
        this.bill = m.getTransactionContainer().getBillById(2);
        System.out.println(bill.getIdBill() + "");

        this.changeBackground("white");
        
        // Set the page title
        NewLabel title = new NewLabel("Transaction", 60, "#867070", 700);
        title.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 25);
        this.getChildren().add(title);

        // NewImage logo = new NewImage("assets/products/No_Image_Available.jpg");

        // PAGE 1
        Cart cart = new Cart(bill);
        cart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        cart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
     
        
        AddNew addNew = new AddNew(this.bill, cart);        
        CustomButton checkoutButton = new CustomButton("Proceed To Checkout", 30, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10);
        checkoutButton.setPrefWidth(540);
        VBox rightSide = new VBox(10, addNew, checkoutButton);
        rightSide.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        rightSide.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        
        this.getChildren().addAll(cart, rightSide);
        
        // PAGE 2
        CustomButton backButton = new CustomButton("Back", 24, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10  );
        backButton.setPrefWidth(240);
        backButton.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 2);
        backButton.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() /5);
        PaymentNominal paymentNominal = new PaymentNominal(this.bill, this.client, this.reducePoints);
        paymentNominal.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 2 / 5);
        paymentNominal.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 11 / 40);
        
        AddPaymentInfo addPaymentInfo = new AddPaymentInfo(this.bill, this.client, this.reducePoints, paymentNominal);
        addPaymentInfo.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        addPaymentInfo.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 5);


        // this.getChildren().addAll(addPaymentInfo, paymentNominal);
        checkoutButton.setOnAction(event -> {
            List<ReceiptInfo> receipts = bill.getReceipt();
            Boolean valid = true;
            for (ReceiptInfo receipt : receipts){
                receipt.recalculate(receipt.getQuantity());
                if (receipt.getIsValid() == false) {
                    valid = false;
                    break;
                }
            }
            
            if (valid) {
                this.getChildren().removeAll(cart, rightSide);
                paymentNominal.setGrandTotalNominal(this.bill.getTotalPrice());
                if (this.client != null && this.client.getType() instanceof VIP) {
                    paymentNominal.setCustomerPayNominal(this.bill.getTotalPrice() * 0.9);        
                    System.out.println("vip");
                } else {
                    paymentNominal.setCustomerPayNominal(this.bill.getTotalPrice());
                }
                this.getChildren().addAll(addPaymentInfo, backButton, paymentNominal);
            }
        });
        
        backButton.setOnMouseClicked(event -> {
            this.client = addPaymentInfo.getClient();
            if (this.client != null && this.client.getType() instanceof VIP) {
                paymentNominal.setCustomerPayNominal(this.bill.getTotalPrice() * 0.9);        
                System.out.println("vip");
            } 
            addPaymentInfo.pointsCheckBox.setSelected(false);
            
            this.getChildren().removeAll(addPaymentInfo, backButton, paymentNominal);
            this.getChildren().addAll(cart, rightSide);
        });
        
    }
}
