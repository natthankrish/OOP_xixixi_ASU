package org.example.program.page;

import java.util.ArrayList;
import java.util.List;
import org.example.program.components.AddNew;
import org.example.program.components.AddPaymentInfo;
import org.example.program.components.Cart;
import org.example.program.components.CustomButton;
import org.example.program.components.NewImage;
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
import lombok.Setter;

public class Transaction extends BasePage {
    @Getter @Setter
    private Client client;
    @Getter
    private Bill bill;
    @Getter @Setter
    private Double reducePoints = 0.0;
    @Getter @Setter
    private Double customerRewardPoints = 0.0;
    @Getter @Setter
    private Double customerPayNominal = 0.0;
    private Cart cart;
    private VBox rightSide;

    public Transaction() {
        this.client = null;
        Manager m = Manager.getInstance();
        Time time = new Time();
        time.updateCurrentTime();
        this.bill = new Bill(m.getTransactionContainer().getMinID() - 1, -1, new ArrayList<>(), 0.0, false, time);
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
            // System.out.println("Data: ");
            this.client = addPaymentInfo.getClient();
            this.customerPayNominal = paymentNominal.getCustomerPayNominal();
            this.customerRewardPoints = paymentNominal.getCustomerGetPoints();
            this.reducePoints = addPaymentInfo.getReducePoints();
            this.bill.setIdBill(m.getTransactionContainer().getMaxID());
            if (this.client == null) {
                this.bill.setIdClient(-1);
            } else {
                this.bill.setIdClient(client.getId());
            }

            time.updateCurrentTime();
            if (this.bill.getTotalPrice() != 0) {
                this.bill.setTransactionTime(time);
                this.bill.setFixed();
                if (this.client != null) {
                    m.getClientContainer().getClientById(this.bill.getIdClient()).addTransaction(this.bill.getIdBill());
                    m.getClientContainer().getClientById(this.bill.getIdClient()).setPoint(client.getPoint() - this.reducePoints + this.customerRewardPoints);
                } else {
                    List<Integer> transactionHistory = new ArrayList<Integer>();
                    transactionHistory.add(this.bill.getIdBill());
                    Client client = new Client(m.getClientContainer().getMaxID()+1, transactionHistory, null);
                    client.makeClientACustomer();
                    m.getClientContainer().addClient(client);
                    m.getClientContainer().getClientById(client.getId()).addTransaction(this.bill.getIdBill());
                }
                this.getChildren().removeAll(addPaymentInfo, backButton, paymentNominal);  
                
                NewImage logo = new NewImage("Main/assets/logo/1.png");
                double dim = Screen.getPrimary().getVisualBounds().getHeight() * 5 / 30;
                logo.setDimension(dim * 16 / 9, dim);
                logo.setPosition(Screen.getPrimary().getVisualBounds().getWidth() / 20, Screen.getPrimary().getVisualBounds().getHeight() * 3 / 30);
                this.getChildren().add(logo);

                NewLabel productName = new NewLabel("Hooray! Transaction Completed!", 75, "#867070", 700);
                productName.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 10);
                productName.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 4 / 15);
                this.getChildren().add(productName);

            }
        });
    }

    public Transaction(Bill bill) {
        Manager m = Manager.getInstance();
        if (bill.getIdClient() != -1){
            this.client = m.getClientContainer().getClientById(bill.getIdClient());
        } else {
            this.client = null;
        }
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
            // System.out.println("Data: ");
            this.client = addPaymentInfo.getClient();
            this.customerPayNominal = paymentNominal.getCustomerPayNominal();
            this.customerRewardPoints = paymentNominal.getCustomerGetPoints();
            this.reducePoints = addPaymentInfo.getReducePoints();
            this.bill.setIdBill(m.getTransactionContainer().getMaxID());
            if (this.client == null) {
                this.bill.setIdClient(-1);
            } else {
                this.bill.setIdClient(client.getId());
            }

            time.updateCurrentTime();
            if (this.bill.getTotalPrice() != 0) {
                this.bill.setTransactionTime(time);
                this.bill.setFixed();
                if (this.client != null) {
                    m.getClientContainer().getClientById(this.bill.getIdClient()).addTransaction(this.bill.getIdBill());
                    m.getClientContainer().getClientById(this.bill.getIdClient()).setPoint(client.getPoint() - this.reducePoints + this.customerRewardPoints);
                } else {
                    List<Integer> transactionHistory = new ArrayList<Integer>();
                    transactionHistory.add(this.bill.getIdBill());
                    Client client = new Client(m.getClientContainer().getMaxID()+1, transactionHistory, null);
                    client.makeClientACustomer();
                    m.getClientContainer().addClient(client);
                    m.getClientContainer().getClientById(client.getId()).addTransaction(this.bill.getIdBill());
                }
                this.getChildren().removeAll(addPaymentInfo, backButton, paymentNominal);  
                
                NewImage logo = new NewImage("Main/assets/logo/1.png");
                double dim = Screen.getPrimary().getVisualBounds().getHeight() * 5 / 30;
                logo.setDimension(dim * 16 / 9, dim);
                logo.setPosition(Screen.getPrimary().getVisualBounds().getWidth() / 20, Screen.getPrimary().getVisualBounds().getHeight() * 3 / 30);
                this.getChildren().add(logo);

                NewLabel productName = new NewLabel("Hooray! Transaction Completed!", 75, "#867070", 700);
                productName.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 10);
                productName.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 4 / 15);
                this.getChildren().add(productName);

            }
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
