package org.example.program.page;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import org.example.program.components.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import org.example.program.components.CardRegister;
import org.example.program.components.DetailRegister;
import org.example.program.components.NewLabel;
import org.example.program.components.SearchBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.program.containers.Manager;
import org.example.program.containers.TransactionContainer;
import org.example.program.entities.bills.Bill;
import org.example.program.entities.bills.Time;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.ClientType;
import org.example.program.entities.clients.Customer;
import org.example.program.entities.clients.Member;

import java.util.ArrayList;
import java.util.List;

public class RegisterMember extends BasePage {
    private NewLabel label;
    private NewField searchBar;
    private VBox cardContainer;
    private ScrollPane scrollPane;
    private DetailRegister currentDetails;
    private AddRegister addRegister;
    private List<Client> name;


    public RegisterMember() {
        this.changeBackground("#FFFFFF");
        this.label = new NewLabel("Register Member", 60, "#867070", 700);
        this.label.setLayout(Screen.getPrimary().getVisualBounds().getWidth() / 20, Screen.getPrimary().getVisualBounds().getHeight() * 7/ 80);

        Manager m = Manager.getInstance();
        TransactionContainer tc = m.getTransactionContainer();
        this.name = m.getClientContainer().getBuffer();
        for (Client c : this.name){
            c.display();
        }

        this.cardContainer = new VBox(10);
        this.cardContainer.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8);
        this.cardContainer.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.cardContainer.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()* 5/ 20);

        for (Client client : name) {
            if (client.getType() instanceof Customer){
                CardRegister card = new CardRegister(client.getName(),
                        client.getId(),
                        client.getPhoneNumber());
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);

                card.setOnMouseClicked(event -> {
//                    Bill a = tc.getBillById(client.getId());
//                    Time tgl = a.getTransactionTime();
                    int tot = 0;
                    // Iterate to calculate total spend
                    for (Integer i : client.getTransactionHistory()){
                        Bill b = tc.getBillById(client.getId());
                        tot += b.getTotalPrice();
                    }

                    DetailRegister newDetails = new DetailRegister(client.getName(),client.getId(), client.getPhoneNumber(), client.getType(), client.getTransactionHistory().size(),"-",tot);
                    this.getChildren().remove(this.currentDetails);
                    this.currentDetails = newDetails;
                    this.currentDetails.setLayout(Screen.getPrimary().getVisualBounds().getWidth() * 9/ 20,Screen.getPrimary().getVisualBounds().getHeight()* 5/ 20);
                    this.getChildren().add(this.currentDetails);

                    this.currentDetails.getRegisterButton().setOnMouseClicked(event1 -> {
                        this.addRegister = new AddRegister("Customer Name", "Phone Number");
//                    this.getChildren().remove(this.addRegister);
                        this.addRegister.setLayout(Screen.getPrimary().getVisualBounds().getWidth() * 9/ 20,Screen.getPrimary().getVisualBounds().getHeight()* 12/ 20);
                        this.addRegister.getConfirm().setOnMouseClicked(event2 -> {
                            cardContainer.getChildren().clear();
                            client.setName(this.addRegister.getCustomerName().getText());
                            client.setPhoneNumber(String.valueOf(this.addRegister.getPhoneNumber().getText()));
                            client.makeClientAMember(this.addRegister.getCustomerName().getText(),this.addRegister.getPhoneNumber().getText(),0.0, true);

                        });
                        this.getChildren().add(this.addRegister);
                    });
                });
            }
        }

        // Mengubah VBox menjadi ScrollPane dan menambahkan cardContainer ke dalam ScrollPane
        this.scrollPane = new ScrollPane();
        this.scrollPane.setContent(cardContainer);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setStyle("-fx-background:white;-fx-background-color:transparent;");
        this.scrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 5 / 8);
        this.scrollPane.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.scrollPane.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()* 5/ 20);

        this.searchBar = new NewField("Search", Screen.getPrimary().getVisualBounds().getWidth() * 3 / 16, 35);
        this.searchBar.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20 + this.scrollPane.getPrefWidth() - this.searchBar.getPrefWidth() / 2);
        this.searchBar.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()* 8/ 40);
        this.searchBar.setOnKeyReleased(event -> {
            this.cardContainer.getChildren().remove(this.currentDetails);
            for (Client client : this.name) {
                if (client.getName() != null && client.getName().toLowerCase().contains(this.searchBar.getText().toLowerCase())
                        || String.valueOf(client.getId()).contains(this.searchBar.getText().toLowerCase())){
                    if (client.getType() instanceof Customer && client.getActiveStatus() != null && client.getActiveStatus()) {
                        CardRegister card = new CardRegister(client.getName(), client.getId(), client.getPhoneNumber());
                        card.setLayout(55, 200);
                        cardContainer.getChildren().add(card);
                        card.setOnMouseClicked(e -> {
                            int tot = 0;
                            for (Integer i : client.getTransactionHistory()){
                                Bill b = tc.getBillById(client.getId());
                                tot += b.getTotalPrice();
                            }

                            DetailRegister newDetail = new DetailRegister(client.getName(),client.getId(),client.getPhoneNumber(),client.getType(),client.getTransactionHistory().size(),"",tot);

                            this.getChildren().remove(this.currentDetails);
                            this.currentDetails = newDetail;
                            this.currentDetails.setLayout(770,180);
                            this.getChildren().add(this.currentDetails);
                        });
                    }
                }
            }


        });

        this.getChildren().addAll(this.label,
                this.scrollPane,
                this.searchBar
        );
    }
}