package org.example.program.components;

import java.util.ArrayList;
import java.util.List;
import org.example.program.containers.Manager;
import org.example.program.entities.bills.Bill;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.Member;
import org.example.program.entities.clients.VIP;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;

public class AddPaymentInfo extends BorderPane {
    @Getter
    private Bill bill;
    @Getter
    private Client client;
    @Getter
    private Double reducePoints;
    public CheckBox pointsCheckBox;
    @Getter
    private CustomButton addButton;

    private NewLabel IDCustomerField;
    private NewLabel customerNameField;
    private NewLabel statusField;
    private NewLabel pointsField;

    public AddPaymentInfo(Bill bill, Client client, Double reducePoints, PaymentNominal paymentNominal){
        
        // Set up the labels
        this.bill = bill;
        this.client = client;
        this.reducePoints = reducePoints;
        NewLabel titleLabel = new NewLabel("Add Payment Info", 44, "#867070", 700);
        NewLabel idCustomLabel = new NewLabel("Customer's ID", 20, "#867070", 700);
        NewLabel customerNameLabel = new NewLabel("Customer's Name", 20, "#867070", 700);
        NewLabel statusLabel = new NewLabel("Status", 20, "#867070", 700);
        NewLabel pointsLabel = new NewLabel("Point", 20, "#867070", 700);

        if (this.client == null ) {
            // set up the fields
            this.IDCustomerField = new NewLabel(" ", 16, "#867070", 700);
            this.customerNameField = new NewLabel(" ", 16, "#867070", 700);
            this.statusField = new NewLabel(" ", 16, "#867070", 700);
            this.pointsField = new NewLabel(" ", 16, "#867070", 700);
        } else {
            // set up the fields
            this.IDCustomerField = new NewLabel(Integer.toString(this.client.getId()), 16, "#867070", 700);
            this.customerNameField = new NewLabel(this.client.getName(), 16, "#867070", 700);
            if (this.client.getType() instanceof VIP){
                this.statusField = new NewLabel("VIP", 16, "#867070", 700);
            } else {
                this.statusField = new NewLabel("Member", 16, "#867070", 700);
            }
            this.pointsField = new NewLabel(Double.toString(this.client.getPoint()), 16, "#867070", 700);
        }

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(213, 180, 180, 0.3), new CornerRadii(30), Insets.EMPTY);
        Background bg = new Background(backgroundFill);
        
        BorderPane IDCustomerPane = new BorderPane();
        VBox IDCustomerBox = new VBox(5, IDCustomerField);
        IDCustomerPane.setCenter(IDCustomerBox);
        IDCustomerPane.setPadding(new Insets(10));
        IDCustomerPane.setBackground(bg);
        IDCustomerPane.setPrefSize(340, 30);

        BorderPane customerNamePane = new BorderPane();
        VBox customerNameBox = new VBox(5, customerNameField);
        customerNamePane.setCenter(customerNameBox);
        customerNamePane.setPadding(new Insets(10));
        customerNamePane.setBackground(bg);
        customerNamePane.setPrefSize(340, 30);

        BorderPane statusPane = new BorderPane();
        VBox statusBox = new VBox(5, statusField);
        statusPane.setCenter(statusBox);
        statusPane.setPadding(new Insets(10));
        statusPane.setBackground(bg);
        statusPane.setPrefSize(500, 30);


        NewLabel usePointsLabel = new NewLabel("Use Points", 20, "#867070", 700);
        this.pointsCheckBox = new CheckBox("");
        pointsCheckBox.setDisable(this.client == null);
        HBox usePointsBox = new HBox(10, pointsCheckBox, usePointsLabel);

        
        Manager m = Manager.getInstance();
        List<Client> clients = m.getClientContainer().getBuffer();
        List<String> searchItems = new ArrayList<>();
        for (Client c : clients){
            String type = "";
            if (c.getType() instanceof Member){
                type = "Member";
            } else {
                type = "VIP";
            }
            searchItems.add(Integer.toString(c.getId()) + " - " + c.getName() + " - " + type);
        }
        
        SearchBar searchBar = new SearchBar(searchItems);
        this.addButton = new CustomButton("add", 16, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10);
        HBox searchBox = new HBox(5, searchBar, addButton);

        VBox container = new VBox(5);
        container.getChildren().addAll(titleLabel, searchBox, idCustomLabel, IDCustomerPane, customerNameLabel, customerNamePane, statusLabel, statusPane, pointsLabel, pointsField, usePointsBox);
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        setPadding(new Insets(20));
        setCenter(container);

        addButton.setOnAction(event -> {
            String text = searchBar.textField.getText();
            if (searchItems.indexOf(text) != -1) {
                this.client = clients.get(searchItems.indexOf(text));
                this.bill.setIdClient(this.client.getId());   
                IDCustomerField.setText(Integer.toString(this.client.getId()));
                customerNameField.setText(this.client.getName());
                String type = "";
                if (this.client.getType() instanceof Member){
                    type = "Member";
                    paymentNominal.setCustomerPayNominal(this.bill.getTotalPrice());
                } else {
                    type = "VIP";
                    paymentNominal.setCustomerPayNominal(this.bill.getTotalPrice() * 0.9);
                }
                paymentNominal.setCustomerGetPoints(this.bill.getTotalPrice() * 0.01);
                statusField.setText(type);
                pointsField.setText(Double.toString(this.client.getPoint()));
                pointsCheckBox.setSelected(false);
                this.reducePoints = 0.0;
                pointsCheckBox.setDisable(false);


            } else {
                this.client = null; 
                this.bill.setIdClient(-1);     
                paymentNominal.setCustomerPayNominal(bill.getTotalPrice());
                paymentNominal.setCustomerGetPoints(0.0);
                IDCustomerField.setText("");
                customerNameField.setText("");
                statusField.setText("");
                pointsField.setText("");
                pointsCheckBox.setSelected(false);
                pointsCheckBox.setDisable(true);             
            }
        });
        
        
        pointsCheckBox.setOnAction(event -> {
            if (pointsCheckBox.isSelected()) {
                if (this.client.getPoint() <= paymentNominal.getCustomerPayNominal()){
                    this.reducePoints = this.client.getPoint();
                    paymentNominal.reduceCustomerPayNominal(this.reducePoints);
                    System.out.println("tes luar");
                } else {
                    this.reducePoints = paymentNominal.getCustomerPayNominal();
                    paymentNominal.reduceCustomerPayNominal(this.reducePoints);
                    System.out.println("tes luar");
                }
            }  else {
                paymentNominal.setCustomerPayNominal(paymentNominal.getCustomerPayNominal() + this.reducePoints);
                this.reducePoints = 0.0;
            }
        });      
        
    }
}
