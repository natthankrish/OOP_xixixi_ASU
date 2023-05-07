package org.example.program.page;

import java.util.ArrayList;
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
import org.example.program.entities.bills.Time;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.VIP;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import lombok.Getter;

public class Transaction extends BasePage {
    private Client client;
    @Getter
    private Bill bill;
    private Double reducePoints = 0.0;
    private Double customerRewardPoints = 0.0;
    private Double customerPayNominal = 0.0;
    private Cart cart;
    private VBox rightSide;

    public Transaction() {
        this.client = null;
        Manager m = Manager.getInstance();
        Time time = new Time();
        time.updateCurrentTime();
        this.bill = new Bill(m.getTransactionContainer().getMinID(), -1, new ArrayList<>(), 0.0, false, time);
        m.getTransactionContainer().addBill(bill);
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
        this.rightSide = new VBox(10, addNew, checkoutButton);
        rightSide.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        rightSide.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        
        this.getChildren().addAll(cart, rightSide);
        
        // PAGE 2
        CustomButton backButton = new CustomButton("Back", 24, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10  );
        backButton.setPrefWidth(240);
        backButton.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 2);
        backButton.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() /5);
        PaymentNominal paymentNominal = new PaymentNominal(this.bill, this.client);
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
        
        paymentNominal.getCompletePaymentButton().setOnAction(event -> {
            System.out.println("Data: ");
            this.client = addPaymentInfo.getClient();
            this.customerPayNominal = paymentNominal.getCustomerPayNominal();
            this.customerRewardPoints = paymentNominal.getCustomerGetPoints();
            this.reducePoints = addPaymentInfo.getReducePoints();
            this.bill.setIdBill(m.getTransactionContainer().getMaxID());
            this.bill.setIdClient(client.getId());
            System.out.println("User : " + this.client.getName());
            System.out.println("Point Used : " + this.reducePoints);
            System.out.println("Point Get : " + this.customerRewardPoints);
            System.out.println("Grand total : " + this.bill.getTotalPrice());
            System.out.println("Customer pay : " + this.customerPayNominal);
            time.updateCurrentTime();
            this.bill.setTransactionTime(time);
            this.bill.setFixed();
            m.getClientContainer().getClientById(client.getId()).addTransaction(this.bill.getIdBill());
            m.getClientContainer().getClientById(client.getId()).setPoint(client.getPoint() - this.reducePoints + this.customerRewardPoints);
        });
    }

    public Transaction(Bill bill) {
        this.client = null;
        Manager m = Manager.getInstance();
        Time time = new Time();
        time.updateCurrentTime();
        this.bill = bill;
        this.changeBackground("white");

        // Set the page title
        NewLabel title = new NewLabel("Transaction", 60, "#867070", 700);
        title.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 25);
        this.getChildren().add(title);

        // NewImage logo = new NewImage("assets/products/No_Image_Available.jpg");

        // PAGE 1
        Cart cart = new Cart(this.bill);
        cart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        cart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);


        AddNew addNew = new AddNew(this.bill, cart);
        CustomButton checkoutButton = new CustomButton("Proceed To Checkout", 30, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10);
        checkoutButton.setPrefWidth(540);
        this.rightSide = new VBox(10, addNew, checkoutButton);
        rightSide.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        rightSide.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);

        this.getChildren().addAll(cart, rightSide);

        // PAGE 2
        CustomButton backButton = new CustomButton("Back", 24, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10  );
        backButton.setPrefWidth(240);
        backButton.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 2);
        backButton.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() /5);
        PaymentNominal paymentNominal = new PaymentNominal(this.bill, this.client);
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

        paymentNominal.getCompletePaymentButton().setOnAction(event -> {
            System.out.println("Data: ");
            this.client = addPaymentInfo.getClient();
            this.customerPayNominal = paymentNominal.getCustomerPayNominal();
            this.customerRewardPoints = paymentNominal.getCustomerGetPoints();
            this.reducePoints = addPaymentInfo.getReducePoints();
            this.bill.setIdBill(m.getTransactionContainer().getMaxID());
            this.bill.setIdClient(client.getId());
            System.out.println("User : " + this.client.getName());
            System.out.println("Point Used : " + this.reducePoints);
            System.out.println("Point Get : " + this.customerRewardPoints);
            System.out.println("Grand total : " + this.bill.getTotalPrice());
            System.out.println("Customer pay : " + this.customerPayNominal);
            time.updateCurrentTime();
            this.bill.setTransactionTime(time);
            this.bill.setFixed();
            m.getClientContainer().getClientById(client.getId()).addTransaction(this.bill.getIdBill());
            m.getClientContainer().getClientById(client.getId()).setPoint(client.getPoint() - this.reducePoints + this.customerRewardPoints);
        });
    }
    public void setBill(Bill bill){
        this.bill = bill;
        if (this.bill.getIdClient() != -1){
            Manager m = Manager.getInstance();
            this.client = m.getClientContainer().getClientById(this.bill.getIdClient());
        } else {
            this.client = null;
        }
        this.getChildren().removeAll(this.cart, this.rightSide);

        this.cart = new Cart(bill);
        cart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        cart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        
        AddNew addNew = new AddNew(this.bill, cart);        
        CustomButton checkoutButton = new CustomButton("Proceed To Checkout", 30, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10);
        checkoutButton.setPrefWidth(540);
        this.rightSide = new VBox(10, addNew, checkoutButton);
        rightSide.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        rightSide.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        
        this.getChildren().addAll(cart, rightSide);
    }
}
