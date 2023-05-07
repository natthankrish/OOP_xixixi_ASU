package org.example.program.page;

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
    private SearchBar searchBar;
    private CardMember card;
    private VBox cardContainer;
    private ScrollPane scrollPane;
    private DetailMember detailMember;
    private NewField newField;
    private CustomButton lastClickedButton;
    public MemberDirectory() {

        Manager m = Manager.getInstance();
        TransactionContainer tc = m.getTransactionContainer();
        List<Client> name = m.getClientContainer().getBuffer();

        this.changeBackground("#FFFFFF");
        this.label = new NewLabel("Member Directory", 48, "#867070", 700);
        this.label.setLayout(45, 35);

        List<String> itemList = new ArrayList<String>();
        itemList.add("kon 1");
        itemList.add("kon 2");
        itemList.add("kon 3");
        itemList.add("mem 1");
        itemList.add("mem 2");
        itemList.add("kim 1");
        itemList.add("kim 2");
        itemList.add("kim 3");

        this.searchBar = new SearchBar(itemList);
        this.searchBar.setLayout(55,140);

        this.buttonVIP = new CustomButton("VIP", 12, "#FFFFFF", "#867070", "bold", 10,0,0,10);
        this.buttonVIP.setOnMouseClicked(event -> {
            if (lastClickedButton != null) {
                lastClickedButton.setStyle(lastClickedButton.getDefaultStyle());
            }
            // update the last clicked button to the current button
            lastClickedButton = buttonVIP;

            buttonVIP.setStyle("-fx-background-color: #CCCCCC;" +
                    "-fx-text-fill: #121212 ;" +
                    "-fx-font-family: Inter;" +
                    "-fx-font-size: 12px; " +
                    "-fx-font-weight: bold; "
            );
            cardContainer.getChildren().clear();
            List<Client> vipClients = name.stream().filter(client -> client.getType() instanceof VIP
                    && client.getActiveStatus()).collect(Collectors.toList());

            for (Client clientVIP : vipClients) {
                CardMember card = new CardMember(clientVIP.getName(), clientVIP.getId(), clientVIP.getPhoneNumber());
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);

                card.setOnMouseClicked(e -> {
                    String nama = clientVIP.getName();
                    int id = clientVIP.getId();
                    int count = clientVIP.getTransactionHistory().size();
//                String terakhir = client.getTransactionHistory()
                    int tot = 0;
                    for (Integer i : clientVIP.getTransactionHistory()){
                        Bill b = tc.getBillById(id);
                        tot += b.getTotalPrice();
                    }

                    DetailMember newDetail = new DetailMember(nama,id, clientVIP, count,"",tot);

                    this.getChildren().remove(this.detailMember);
                    this.detailMember = newDetail;
                    this.detailMember.setLayout(770,180);
                    this.getChildren().add(this.detailMember);
                });
            }

        });
        this.buttonVIP.setLayout(330,142);

        this.buttonMember = new CustomButton("Member", 12, "#FFFFFF", "#867070", "bold",1,1,1,1);
        this.buttonMember.setOnMouseClicked(event -> {
            if (lastClickedButton != null) {
                lastClickedButton.setStyle(lastClickedButton.getDefaultStyle());
            }
            // update the last clicked button to the current button
            lastClickedButton = buttonMember;

            buttonMember.setStyle("-fx-background-color: #CCCCCC;" +
                    "-fx-text-fill: #121212 ;" +
                    "-fx-font-family: Inter;" +
                    "-fx-font-size: 12px; " +
                    "-fx-font-weight: bold; "
            );
            cardContainer.getChildren().clear();
            List<Client> memberClients = name.stream().filter(client -> client.getType() instanceof Member
                    && client.getActiveStatus()).collect(Collectors.toList());

            for (Client clientMember : memberClients) {
                CardMember card = new CardMember(clientMember.getName(), clientMember.getId(), clientMember.getPhoneNumber());
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);

                card.setOnMouseClicked(e -> {
                    String nama = clientMember.getName();
                    int id = clientMember.getId();
                    int count = clientMember.getTransactionHistory().size();
//                String terakhir = client.getTransactionHistory()
                    int tot = 0;
                    for (Integer i : clientMember.getTransactionHistory()){
                        Bill b = tc.getBillById(id);
                        tot += b.getTotalPrice();
                    }

                    DetailMember newDetail = new DetailMember(nama,id, clientMember, count,"",tot);

                    this.getChildren().remove(this.detailMember);
                    this.detailMember = newDetail;
                    this.detailMember.setLayout(770,180);
                    this.getChildren().add(this.detailMember);
                });
            }

        });
        this.buttonMember.setLayout(363, 142);

        this.buttonDeactivated = new CustomButton("Deactivated", 12, "#FFFFFF", "#867070", "bold", 0,10,10,0);
        this.buttonDeactivated.setOnMouseClicked(event -> {
            if (lastClickedButton != null) {
                lastClickedButton.setStyle(lastClickedButton.getDefaultStyle());
            }
            // update the last clicked button to the current button
            lastClickedButton = buttonDeactivated;

            buttonDeactivated.setStyle("-fx-background-color: #CCCCCC;" +
                    "-fx-text-fill: #121212 ;" +
                    "-fx-font-family: Inter;" +
                    "-fx-font-size: 12px; " +
                    "-fx-font-weight: bold; "
            );
            cardContainer.getChildren().clear();
            List<Client> deactivatedList = name.stream().filter(client ->
                    ((client.getType() instanceof VIP && !client.getActiveStatus())
                            || (client.getType() instanceof Member && !client.getActiveStatus())
                    )
            ).collect(Collectors.toList());

            for (Client deactivated : deactivatedList) {
                CardMember card = new CardMember(deactivated.getName(), deactivated.getId(), deactivated.getPhoneNumber());
                card.setLayout(55, 200);
                cardContainer.getChildren().add(card);

                card.setOnMouseClicked(e -> {
                    String nama = deactivated.getName();
                    int id = deactivated.getId();
                    int count = deactivated.getTransactionHistory().size();
//                String terakhir = client.getTransactionHistory()
                    int tot = 0;
                    for (Integer i : deactivated.getTransactionHistory()){
                        Bill b = tc.getBillById(id);
                        tot += b.getTotalPrice();
                    }

                    DetailMember newDetail = new DetailMember(nama,id, deactivated, count,"",tot);

                    this.getChildren().remove(this.detailMember);
                    this.detailMember = newDetail;
                    this.detailMember.setLayout(770,180);
                    this.getChildren().add(this.detailMember);
                });
            }

        });
        this.buttonDeactivated.setLayout(423,142);

        this.cardContainer = new VBox(10);
        this.cardContainer.setAlignment(Pos.CENTER);
        this.cardContainer.setPrefWidth(660);
        this.cardContainer.setLayoutX(55);
        this.cardContainer.setLayoutY(200);

        for (Client client : name) {
            CardMember card = new CardMember(client.getName(), client.getId(), client.getPhoneNumber());
            card.setLayout(55, 200);
            cardContainer.getChildren().add(card);

            card.setOnMouseClicked(event -> {
                String nama = client.getName();
                int id = client.getId();
                int count = client.getTransactionHistory().size();
//                String terakhir = client.getTransactionHistory()
                int tot = 0;
                for (Integer i : client.getTransactionHistory()){
                    Bill b = tc.getBillById(id);
                    tot += b.getTotalPrice();
                }

                DetailMember newDetail = new DetailMember(nama,id, client, count,"",tot);

                this.getChildren().remove(this.detailMember);
                this.detailMember = newDetail;
                this.detailMember.setLayout(770,180);
                this.getChildren().add(this.detailMember);
            });
        }

        // Mengubah VBox menjadi ScrollPane dan menambahkan cardContainer ke dalam ScrollPane
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