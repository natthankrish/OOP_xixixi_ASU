package program.page;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import program.components.*;

import java.util.ArrayList;
import java.util.List;

public class ItemDirectory extends BasePage {
    private NewButton addItemButton;
    private List<String> data;
    private ScrollPanel buffer;
    private SearchBar searchBar;
    private ProductDetails currentDetails;

    public ItemDirectory () {
        NewLabel head = new NewLabel("Item Directory", 60, "#867070", 700);
        head.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        head.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 20);
        this.getChildren().add(head);

        this.addItemButton = new NewButton("Add New Item", 140, 30);
        this.addItemButton.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.addItemButton.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.getChildren().add(addItemButton);

        NewImage search = new NewImage("assets/search.png");
        double dim = Screen.getPrimary().getVisualBounds().getHeight() * 1 / 45;
        search.setDimension(dim, dim);
        search.setPosition(Screen.getPrimary().getVisualBounds().getWidth() * 4 / 20, Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.getChildren().add(search);

        this.data = new ArrayList();
        this.data.add("haha");
        this.searchBar = new SearchBar(this.data);
        this.searchBar.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 5 / 20);
        this.searchBar.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.getChildren().add(searchBar);

        this.buffer = new ScrollPanel(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8, 500);
        this.buffer.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.buffer.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 9 / 40);
        this.getChildren().add(buffer);

        this.addProductItem("Halo");
        this.addProductItem("xixi");
        this.addProductItem("wmldm");
    }

    public void changeCurrentDetails(ProductDetails newDetails) {
        this.getChildren().remove(this.currentDetails);
        this.currentDetails = newDetails;
        this.getChildren().add(this.currentDetails);
    }

    public void addProductItem(String name) {
        ProductItem productItem = new ProductItem(name);
        productItem.setOnMouseClicked(event -> {
            this.changeCurrentDetails(productItem.getDetails());
        });
        this.buffer.addItem(productItem);
    }


}
