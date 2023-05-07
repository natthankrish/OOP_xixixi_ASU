package org.example.program.page;

import org.example.program.components.NewLabel;
import org.example.program.components.SearchBar;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import org.example.program.components.AddRegister;
import org.example.program.components.CardRegister;
import org.example.program.components.DetailRegister;
import org.example.program.components.NewLabel;
import org.example.program.components.SearchBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.program.containers.Manager;
import org.example.program.containers.TransactionContainer;
import org.example.program.entities.Bill;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.ClientType;

import java.util.ArrayList;
import java.util.List;

public class RegisterMember extends BasePage {
    private NewLabel label;
    private SearchBar searchBar;
    private VBox cardContainer;
    private ScrollPane scrollPane;
    private DetailRegister currentDetails;
    private AddRegister addRegister;


    public RegisterMember() {
        this.changeBackground("#FFFFFF");
        this.label = new NewLabel("Register Member", 48, "#867070", 700);
        this.label.setLayout(45, 35);

        Manager m = Manager.getInstance();
        TransactionContainer tc = m.getTransactionContainer();
        List<Client> name = m.getClientContainer().getBuffer();

        this.cardContainer = new VBox(10);
        this.cardContainer.setAlignment(Pos.CENTER);
        this.cardContainer.setPrefWidth(660);
        this.cardContainer.setLayoutX(55);
        this.cardContainer.setLayoutY(200);

        for (Client client : name) {
            CardRegister card = new CardRegister(client.getName(),
                    client.getId(),
                    client.getPhoneNumber());

            card.setOnMouseClicked(event -> {
                String nama = client.getName();
                int id = client.getId();
                String pune = client.getPhoneNumber();
                ClientType membership = client.getType();

                int count = client.getTransactionHistory().size();
                String tglTransaksi = "";

                int tot = 0;
                // Iterate to calculate total spend
                for (Integer i : client.getTransactionHistory()){
                    Bill b = tc.getBillById(id);
                    tot += b.getTotalPrice();
                }

                DetailRegister newDetails = new DetailRegister(nama,id,pune,membership,count,tglTransaksi,tot);
                this.getChildren().remove(this.currentDetails);
                this.currentDetails= newDetails;
                this.currentDetails.setLayout(770,180);
                this.getChildren().add(this.currentDetails);

                this.currentDetails.getRegisterButton().setOnMouseClicked(event1 -> {
                    this.addRegister = new AddRegister("Customer Name", "Phone Number");
//                    this.getChildren().remove(this.addRegister);
                    this.addRegister.setLayout(770,500);
                    this.getChildren().add(this.addRegister);
                });
            });
            card.setLayout(55, 200);
            cardContainer.getChildren().add(card);
        }

        // Mengubah VBox menjadi ScrollPane dan menambahkan cardContainer ke dalam ScrollPane
        this.scrollPane = new ScrollPane();
        this.scrollPane.setContent(cardContainer);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setStyle("-fx-background:white;-fx-background-color:transparent;");
        this.scrollPane.setPrefViewportHeight(520);
        this.scrollPane.setLayoutX(55);
        this.scrollPane.setLayoutY(180);

        List<String> itemList = new ArrayList<String>();
        itemList.add("kon 1");
        itemList.add("kon 2");
        itemList.add("kon 3");
        itemList.add("kon 4");
        itemList.add("kon 5");
        itemList.add("kon 6");
        itemList.add("kon 7");
        itemList.add("kon 8");

        this.searchBar = new SearchBar(itemList);
        this.searchBar.setLayout(477,140);

        this.getChildren().addAll(this.label,
                this.scrollPane,
                this.searchBar
        );
    }

    public void changeCurrentDetails(DetailRegister newDetails){
        this.getChildren().remove(this.currentDetails);
        this.currentDetails = newDetails;
        this.getChildren().add(this.currentDetails);
    }
}