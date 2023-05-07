package program.page;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import program.components.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


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
    public MemberDirectory() {

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
            setStyle("-fx-background-color: #121212;");
        });
        this.buttonVIP.setLayout(330,142);

        this.buttonMember = new CustomButton("Member", 12, "#FFFFFF", "#867070", "bold",1,1,1,1);
        this.buttonMember.setOnMouseClicked(event -> {
            setStyle("-fx-background-color: #121212;");
        });
        this.buttonMember.setLayout(363, 142);

        this.buttonDeactivated = new CustomButton("Deactivated", 12, "#FFFFFF", "#867070", "bold", 0,10,10,0);
        this.buttonDeactivated.setOnMouseClicked(event -> {
            setStyle("-fx-background-color: #121212;");
        });
        this.buttonDeactivated.setLayout(423,142);
        Manager m = Manager.getInstance();

        String[][] cardData = {
                {"nolan", "123", "29/20/2020"},
                {"mike", "456", "30/20/2020"},
                {"jane", "789", "01/21/2021"},
                {"june", "289", "01/21/2021"},
                {"dune", "729", "01/21/2021"},
                {"jake", "489", "01/21/2021"},
                {"jake", "489", "01/21/2021"},
                {"k", "489", "01/21/2021"},
                {"i", "489", "01/21/2021"},

        };

        this.cardContainer = new VBox(10);
        this.cardContainer.setAlignment(Pos.CENTER);
        this.cardContainer.setPrefWidth(660);
        this.cardContainer.setLayoutX(55);
        this.cardContainer.setLayoutY(200);

        for (String[] data : cardData) {
            CardMember card = new CardMember(data[0], Integer.parseInt(data[1]), data[2]);
            card.setLayout(55, 200);
            cardContainer.getChildren().add(card);

            card.setOnMouseClicked(event -> {
                String name = data[0];
                int id = Integer.parseInt(data[1]);
                String joinDate = data[2];

                DetailMember newDetail = new DetailMember(name,id,joinDate,"Member", 0,"",0);

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
