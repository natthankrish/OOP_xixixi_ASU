package org.example.program.page;

import javafx.geometry.Pos;
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
import org.example.program.entities.Bill;
import org.example.program.entities.Time;
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
        this.label = new NewLabel("Register Member", 48, "#867070", 700);
        this.label.setLayout(45, 35);

        Manager m = Manager.getInstance();
        TransactionContainer tc = m.getTransactionContainer();
        this.name = m.getClientContainer().getBuffer();

        this.cardContainer = new VBox(10);
        this.cardContainer.setAlignment(Pos.CENTER);
        this.cardContainer.setPrefWidth(660);
        this.cardContainer.setLayoutX(55);
        this.cardContainer.setLayoutY(200);

        for (Client client : name) {
            if (client.getType() instanceof Customer){
                CardRegister card = new CardRegister(client.getName(),
                        client.getId(),
                        client.getPhoneNumber());

                card.setOnMouseClicked(event -> {
                    Bill a = tc.getBillById(client.getId());
                    Time tgl = a.getTransactionTime();
                    int tot = 0;
                    // Iterate to calculate total spend
                    for (Integer i : client.getTransactionHistory()){
                        Bill b = tc.getBillById(client.getId());
                        tot += b.getTotalPrice();
                    }

                    DetailRegister newDetails = new DetailRegister(client.getName(),client.getId(), client.getPhoneNumber(), client.getType(), client.getTransactionHistory().size(),tgl.getStringTime(),tot);
                    this.getChildren().remove(this.currentDetails);
                    this.currentDetails= newDetails;
                    this.currentDetails.setLayout(770,180);
                    this.getChildren().add(this.currentDetails);

                    this.currentDetails.getRegisterButton().setOnMouseClicked(event1 -> {
                        this.addRegister = new AddRegister("Customer Name", "Phone Number");
//                    this.getChildren().remove(this.addRegister);
                        this.addRegister.setLayout(770,500);
                        this.addRegister.getConfirm().setOnMouseClicked(event2 -> {
                            client.makeClientACustomer();
                            client.makeClientAMember(String.valueOf(this.addRegister.getCustomerName().getTextField()),
                                    String.valueOf(this.addRegister.getPhoneNumber().getTextField()),
                                    client.getPoint(),
                                    client.getActiveStatus());
                        });
                        this.getChildren().add(this.addRegister);
                    });
                });
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);
            }
        }

        // Mengubah VBox menjadi ScrollPane dan menambahkan cardContainer ke dalam ScrollPane
        this.scrollPane = new ScrollPane();
        this.scrollPane.setContent(cardContainer);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setStyle("-fx-background:white;-fx-background-color:transparent;");
        this.scrollPane.setPrefViewportHeight(520);
        this.scrollPane.setLayoutX(55);
        this.scrollPane.setLayoutY(180);

        this.searchBar = new NewField("Search", Screen.getPrimary().getVisualBounds().getWidth() * 3 / 16, 40);
        this.searchBar.setLayoutX(410);
        this.searchBar.setLayoutY(130);
        this.searchBar.setOnKeyReleased(event -> {
            this.cardContainer.getChildren().clear();
            for (Client client : this.name) {
                if (client.getName() != null && client.getName().toLowerCase().contains(this.searchBar.getText().toLowerCase())) {
                    if (client.getActiveStatus() != null && client.getActiveStatus()){
                        CardMember card = new CardMember(client.getName(), client.getId(), client.getPhoneNumber());
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