package program.page;

import javafx.scene.Group;
import javafx.scene.Node;
import program.App;
import javafx.stage.Screen;
import program.components.*;

import java.util.ArrayList;
import java.util.List;

public class ItemDirectory extends BasePage {
    private Group detailpage;
    private Group addItemPage;
    private NewButton backToItemDetails;
    private NewButton addItemButton;
    private List<String> data;
    private ScrollPanel buffer;
    private SearchBar searchBar;
    private ProductDetails currentDetails;

    public ItemDirectory () {
        this.detailpage = new Group();
        this.addItemPage = new Group();
        this.getChildren().add(this.detailpage);

        NewLabel head = new NewLabel("Item Directory", 60, "#867070", 700);
        head.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        head.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 20);
        this.detailpage.getChildren().add(head);

        this.addItemButton = new NewButton("Add New Item", 140, 30);
        this.addItemButton.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.addItemButton.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.detailpage.getChildren().add(addItemButton);
        this.addItemButton.setOnMouseClicked(event -> {
            this.getChildren().remove(this.detailpage);
            this.getChildren().add(this.addItemPage);
        });

        NewImage search = new NewImage("assets/search.png");
        double dim = Screen.getPrimary().getVisualBounds().getHeight() * 1 / 45;
        search.setDimension(dim, dim);
        search.setPosition(Screen.getPrimary().getVisualBounds().getWidth() * 4 / 20, Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.detailpage.getChildren().add(search);

        this.data = new ArrayList();
        this.data.add("haha");
        this.searchBar = new SearchBar(this.data);
        this.searchBar.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 5 / 20);
        this.searchBar.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.detailpage.getChildren().add(searchBar);

        this.buffer = new ScrollPanel(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8, 500);
        this.buffer.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.buffer.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 9 / 40);
        this.detailpage.getChildren().add(buffer);

        this.addProductItem("Halo");
        this.addProductItem("xixi");
        this.addProductItem("wmldm");

        NewLabel headAdd = new NewLabel("Add Item To Directory", 60, "#867070", 700);
        headAdd.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        headAdd.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()*2/ 20);

        AddItemBuffer addItemBuffer = new AddItemBuffer();

        this.addItemPage.getChildren().addAll(headAdd, addItemBuffer);
        addItemBuffer.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        addItemBuffer.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()*4/ 20);

        this.backToItemDetails = new NewButton("Cancel", 140, 30);
        this.backToItemDetails.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()*5/ 40);
        this.backToItemDetails.setLayoutX(addItemBuffer.getBackground().getWidth() - this.backToItemDetails.getPrefWidth());
        this.addItemPage.getChildren().add(this.backToItemDetails);
        this.backToItemDetails.setOnMouseClicked(event -> {
            this.getChildren().remove(this.addItemPage);
            this.getChildren().add(this.detailpage);
        });

    }

    public void changeCurrentDetails(ProductDetails newDetails) {
        this.detailpage.getChildren().remove(this.currentDetails);
        this.currentDetails = newDetails;
        this.detailpage.getChildren().add(this.currentDetails);
    }

    public void addProductItem(String name) {
        ProductItem productItem = new ProductItem(name);
        productItem.setOnMouseClicked(event -> {
            this.changeCurrentDetails(productItem.getDetails());
        });
        this.buffer.addItem(productItem);
    }


}
