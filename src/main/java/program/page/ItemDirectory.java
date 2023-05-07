package program.page;

import javafx.scene.Group;
import javafx.scene.Node;
import program.App;
import javafx.stage.Screen;
import program.components.*;
import program.containers.Manager;
import program.entities.Product;
import lombok.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemDirectory extends BasePage {
    private Group detailpage;
    private Group addItemPage;
    private NewButton backToItemDetails;
    private NewButton addItemButton;
    private List<Product> data;

    private ScrollPanel buffer;
    private SearchBar searchBar;
    private ProductDetails currentDetails;
    private int itemDetails;

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

        List<String> hehe = new ArrayList();
        this.searchBar = new SearchBar(hehe);
        this.searchBar.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 5 / 20);
        this.searchBar.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.detailpage.getChildren().add(searchBar);

        this.buffer = new ScrollPanel(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8, Screen.getPrimary().getVisualBounds().getHeight() * 5 / 8);
        this.buffer.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.buffer.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 9 / 40);
        this.detailpage.getChildren().add(buffer);

        Manager m = Manager.getInstance();
        this.data = m.getInventoryContainer().getBuffer();
        for (Product item: this.data) {
            if (item.getActive()) {
                this.addProductItem(item);
            }
        }

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
        this.itemDetails = -1;
    }

    public void changeCurrentDetails(ProductDetails newDetails) {
        if (this.currentDetails != null) {
            this.detailpage.getChildren().remove(this.currentDetails);
        }
        this.currentDetails = newDetails;
        if (newDetails != null) {
            this.itemDetails = newDetails.getProductid();
            this.detailpage.getChildren().add(this.currentDetails);
        } else {
            this.itemDetails = -1;
        }
    }

    public void addProductItem(Product product) {
        ProductItem productItem = new ProductItem(product);
        if (product.getId().equals(this.itemDetails)) {
            this.changeCurrentDetails(productItem.getDetails());
        }
        productItem.setOnMouseClicked(event -> {
            this.changeCurrentDetails(productItem.getDetails());
        });
        productItem.getDetails().getNaAnymore().setOnMouseClicked(event -> {
            this.buffer.removeItem(productItem);
            this.changeCurrentDetails(null);
            Manager m = Manager.getInstance();
            m.getInventoryContainer().getProductById(product.getId()).setActive(false);
        });
        productItem.getDetails().getConfirmChanges().setOnMouseClicked(event -> {
            boolean change = true;
            int newStock = 0;
            double newSellingPrice = 0, newPurchasedPrice = 0;
            if (!productItem.getDetails().getStock().getTextField().getText().isEmpty()) {
                try {
                    newStock = Integer.parseInt(productItem.getDetails().getStock().getTextField().getText());
                } catch (NumberFormatException e) {
                    change = false;
                }
            }

            if (!productItem.getDetails().getSellingPrice().getTextField().getText().isEmpty()) {
                try {
                    newSellingPrice = Double.parseDouble(productItem.getDetails().getSellingPrice().getTextField().getText());
                } catch (NumberFormatException e) {
                    change = false;
                }
            }

            if (!productItem.getDetails().getPurchasedPrice().getTextField().getText().isEmpty()) {
                try {
                    newPurchasedPrice = Double.parseDouble(productItem.getDetails().getPurchasedPrice().getTextField().getText());
                } catch (NumberFormatException e) {
                    change = false;
                }
            }

            if (change) {
                Manager m = Manager.getInstance();
                if (!productItem.getDetails().getStock().getTextField().getText().isEmpty()) {
                    m.getInventoryContainer().getProductById(productItem.getDetails().getProductid()).setStock(newStock);
                    productItem.getDetails().getStock().getTextField().clear();
                }

                if (!productItem.getDetails().getSellingPrice().getTextField().getText().isEmpty()) {
                    m.getInventoryContainer().getProductById(productItem.getDetails().getProductid()).setPrice(newSellingPrice);
                    productItem.getDetails().getSellingPrice().getTextField().clear();
                }

                if (!productItem.getDetails().getPurchasedPrice().getTextField().getText().isEmpty()) {
                    m.getInventoryContainer().getProductById(productItem.getDetails().getProductid()).setPurchasePrice(newPurchasedPrice);
                    productItem.getDetails().getPurchasedPrice().getTextField().clear();
                }

            }
            this.changeCurrentDetails(null);
            this.refreshData();
        });
        this.buffer.addItem(productItem);
    }

    @Override
    public void refreshData() {
        this.buffer.removeAll();
        boolean change = false;
        Manager m = Manager.getInstance();
        for (Product item: this.data) {
            if (item.getActive()) {
                this.addProductItem(item);
                if (item.getId() == this.itemDetails) {
                    change = true;
                }
            }
        }
        if (!change) {
            this.changeCurrentDetails(null);
        }
    }



}
