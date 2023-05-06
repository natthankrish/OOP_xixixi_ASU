package program.page;

import javafx.geometry.Pos;
import program.components.CardRegister;
import program.components.DetailRegister;
import program.components.NewLabel;
import program.components.SearchBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RegisterMember extends BasePage {
    private NewLabel label;
    private SearchBar searchBar;
    private VBox cardContainer;
    private ScrollPane scrollPane;
    private DetailRegister detailRegister;

    public RegisterMember() {
        this.changeBackground("#FFFFFF");
        this.label = new NewLabel("Register Member", 48, "#867070", 700);
        this.label.setLayout(45, 35);

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
            CardRegister card = new CardRegister(data[0], Integer.parseInt(data[1]), data[2]);
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

        this.detailRegister = new DetailRegister("Nama Customer",
                123,
                "12/12/2012",
                "Member",
                123,
                "20/20/2020",
                20000000
                );
        this.detailRegister.setLayout(770,180);

        this.getChildren().addAll(this.label, this.scrollPane, this.searchBar, this.detailRegister);
    }
}
