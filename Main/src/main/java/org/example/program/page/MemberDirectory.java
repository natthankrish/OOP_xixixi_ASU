package org.example.program.page;

import javafx.stage.Screen;
import org.example.program.components.CustomButton;
import org.example.program.components.NewLabel;
import org.example.program.components.SearchBar;
import org.example.program.components.*;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.program.containers.Manager;
import org.example.program.containers.TransactionContainer;
import org.example.program.entities.Bill;
import org.example.program.entities.Product;
import org.example.program.entities.Time;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.ClientType;
import org.example.program.entities.clients.Member;
import org.example.program.entities.clients.VIP;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MemberDirectory extends BasePage {
    private CustomButton buttonVIP;
    private CustomButton buttonMember;
    private CustomButton buttonDeactivated;
    private NewLabel label;
    private NewField searchBar;
    private CardMember card;
    private VBox cardContainer;
    private ScrollPane scrollPane;
    private DetailMember detailMember;
    private CustomButton lastClickedButton;
    private List<Client> name;
    public MemberDirectory() {

        Manager m = Manager.getInstance();
        TransactionContainer tc = m.getTransactionContainer();
        this.name = m.getClientContainer().getBuffer();

        this.changeBackground("#FFFFFF");
        this.label = new NewLabel("Member Directory", 48, "#867070", 700);
        this.label.setLayout(45, 35);

        this.buttonVIP = new CustomButton("VIP", 16, "#FFFFFF", "#867070", "bold", 10,0,0,10);
        this.buttonVIP.setOnMouseClicked(event -> {

            cardContainer.getChildren().clear();
            List<Client> vipClients = name.stream().filter(client -> client.getType() instanceof VIP
                    && client.getActiveStatus()).toList();

            for (Client clientVIP : vipClients) {
                CardMember card = new CardMember(clientVIP.getName(), clientVIP.getId(), clientVIP.getPhoneNumber());
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);

                card.setOnMouseClicked(e -> {
                    int tot = 0;
                    for (Integer i : clientVIP.getTransactionHistory()){
                        Bill b = tc.getBillById(clientVIP.getId());
                        tot += b.getTotalPrice();
                    }

                    DetailMember newDetail = new DetailMember(clientVIP.getName(),clientVIP.getId(), clientVIP, clientVIP.getTransactionHistory().size(),"",tot);

                    this.getChildren().remove(this.detailMember);
                    this.detailMember = newDetail;
                    this.detailMember.getKanan().setOnMouseClicked(e2 -> {
                        clientVIP.setInactive();
                    });
                    this.detailMember.getKiri().setOnMouseClicked(e2 -> {
                        clientVIP.makeClientAMember(clientVIP.getName(),clientVIP.getPhoneNumber(), clientVIP.getPoint(), clientVIP.getActiveStatus());
                    });
                    this.detailMember.setLayout(770,180);
                    this.getChildren().add(this.detailMember);
                });
            }

        });
        this.buttonVIP.setLayout(480,130);


        this.buttonMember = new CustomButton("Member", 16, "#FFFFFF", "#867070", "bold",1,1,1,1);
        this.buttonMember.setOnMouseClicked(event -> {
            cardContainer.getChildren().clear();
            List<Client> memberClients = name.stream().filter(client -> client.getType() instanceof Member
                    && client.getActiveStatus()!= null && client.getActiveStatus()).toList();

            for (Client clientMember : memberClients) {
                CardMember card = new CardMember(clientMember.getName(), clientMember.getId(), clientMember.getPhoneNumber());
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);

                card.setOnMouseClicked(e -> {
                    int tot = 0;
                    for (Integer i : clientMember.getTransactionHistory()){
                        Bill b = tc.getBillById(clientMember.getId());
                        tot += b.getTotalPrice();
                    }

                    DetailMember newDetail = new DetailMember(clientMember.getName(),clientMember.getId(), clientMember, clientMember.getTransactionHistory().size(),"",tot);

                    this.getChildren().remove(this.detailMember);
                    this.detailMember = newDetail;
                    this.detailMember.getKanan().setOnMouseClicked(e2 -> {
                        clientMember.setInactive();
                    });
                    this.detailMember.getKiri().setOnMouseClicked(e2 -> {
                        clientMember.makeClientAVIP(clientMember.getName(),clientMember.getPhoneNumber(), clientMember.getPoint(), clientMember.getActiveStatus());
                    });
                    this.detailMember.setLayout(770,180);
                    this.getChildren().add(this.detailMember);
                });
            }

        });
        this.buttonMember.setLayout(522, 130);


        this.buttonDeactivated = new CustomButton("Deactivated", 16, "#FFFFFF", "#867070", "bold", 0,10,10,0);
        this.buttonDeactivated.setOnMouseClicked(event -> {
            cardContainer.getChildren().clear();
            List<Client> deactivatedList = name.stream().filter(client ->
                    ((client.getType() instanceof VIP && !client.getActiveStatus())
                            || (client.getType() instanceof Member && !client.getActiveStatus())
                    )
            ).toList();

            for (Client deactivated : deactivatedList) {
                CardMember card = new CardMember(deactivated.getName(), deactivated.getId(), deactivated.getPhoneNumber());
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);

                card.setOnMouseClicked(e -> {
                    int tot = 0;
                    for (Integer i : deactivated.getTransactionHistory()){
                        Bill b = tc.getBillById(deactivated.getId());
                        tot += b.getTotalPrice();
                    }

                    DetailMember newDetail = new DetailMember(deactivated.getName(),deactivated.getId(), deactivated, deactivated.getTransactionHistory().size(),"",tot);

                    this.getChildren().remove(this.detailMember);
                    this.detailMember = newDetail;
                    this.detailMember.getKanan().setOnMouseClicked(e2 -> {
                        deactivated.setActive();
                        deactivated.makeClientAVIP(deactivated.getName(),deactivated.getPhoneNumber(),deactivated.getPoint(),deactivated.getActiveStatus());
                    });
                    this.detailMember.getKiri().setOnMouseClicked(e2 -> {
                        deactivated.setActive();
                        deactivated.makeClientAMember(deactivated.getName(),deactivated.getPhoneNumber(), deactivated.getPoint(), deactivated.getActiveStatus());
                    });
                    this.detailMember.setLayout(770,180);
                    this.getChildren().add(this.detailMember);
                });
            }

        });
        this.buttonDeactivated.setLayout(605,130);

        this.cardContainer = new VBox(10);
        this.cardContainer.setAlignment(Pos.CENTER);
        this.cardContainer.setPrefWidth(660);
        this.cardContainer.setLayoutX(55);
        this.cardContainer.setLayoutY(200);

        for (Client client : this.name) {
            if (client.getActiveStatus() != null && client.getActiveStatus()){
                CardMember card = new CardMember(client.getName(), client.getId(), client.getPhoneNumber());
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);

                card.setOnMouseClicked(event -> {
                    Bill a = tc.getBillById(client.getId());
                    Time date = a.getTransactionTime();
                    int tot = 0;

                    for (Integer i : client.getTransactionHistory()){
                        Bill b = tc.getBillById(client.getId());
                        tot += b.getTotalPrice();
                    }


                    DetailMember newDetail = new DetailMember(client.getName(),client.getId(), client, client.getTransactionHistory().size(),date.getStringTime(),tot);

                    this.getChildren().remove(this.detailMember);
                    this.detailMember = newDetail;

                    if(client.getType() instanceof Member){
                        this.detailMember.getKanan().setOnMouseClicked(e2 -> {
                            client.setInactive();
                        });
                        this.detailMember.getKiri().setOnMouseClicked(e2 -> {
                            client.makeClientAVIP(client.getName(),client.getPhoneNumber(), client.getPoint(), client.getActiveStatus());
                        });
                    }
                    else {
                        this.detailMember.getKanan().setOnMouseClicked(e2 -> {
                            client.setInactive();
                        });
                        this.detailMember.getKiri().setOnMouseClicked(e2 -> {
                            client.makeClientAMember(client.getName(),client.getPhoneNumber(), client.getPoint(), client.getActiveStatus());
                        });
                    }
                    this.detailMember.setLayout(770,180);
                    this.getChildren().add(this.detailMember);
                });
            }
        }
        this.searchBar = new NewField("Search", Screen.getPrimary().getVisualBounds().getWidth() * 3 / 16, 40);
        this.searchBar.setLayoutX(100);
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

                            DetailMember newDetail = new DetailMember(client.getName(),client.getId(), client, client.getTransactionHistory().size(),"",tot);

                            this.getChildren().remove(this.detailMember);
                            this.detailMember = newDetail;
                            if(client.getType() instanceof Member){
                                this.detailMember.getKanan().setOnMouseClicked(e2 -> {
                                    client.setInactive();
                                });
                                this.detailMember.getKiri().setOnMouseClicked(e2 -> {
                                    client.makeClientAVIP(client.getName(),client.getPhoneNumber(), client.getPoint(), client.getActiveStatus());
                                });
                            }
                            else {
                                this.detailMember.getKanan().setOnMouseClicked(e2 -> {
                                    client.setInactive();
                                });
                                this.detailMember.getKiri().setOnMouseClicked(e2 -> {
                                    client.makeClientAMember(client.getName(),client.getPhoneNumber(), client.getPoint(), client.getActiveStatus());
                                });
                            }
                            this.detailMember.setLayout(770,180);
                            this.getChildren().add(this.detailMember);
                        });
                    }
                }
            }


        });
        this.scrollPane = new ScrollPane();
        this.scrollPane.setContent(cardContainer);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setStyle("-fx-background:white;-fx-background-color:transparent;");
        this.scrollPane.setPrefViewportHeight(520);
        this.scrollPane.setLayoutX(55);
        this.scrollPane.setLayoutY(180);


        this.getChildren().addAll(this.label,
                this.buttonVIP,
                this.buttonMember,
                this.buttonDeactivated,
                this.scrollPane,
                this.searchBar
        );
    }
}