package org.example.program.page;

import javafx.scene.Group;
import javafx.stage.Screen;
import org.example.program.components.*;
import org.example.program.containers.Manager;
import org.example.program.entities.commodities.Commodity;
import org.example.program.entities.commodities.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemDirectory extends BasePage {
    private Group detailpage;
    private Group addItemPage;
    public AddItemBuffer addItemBuffer;
    private NewButton backToItemDetails;
    private NewButton addItemButton;
    private List<Commodity> data;

    private ScrollPanel buffer;
    private NewField searchBar;
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

        NewImage search = new NewImage("Main/assets/search.png");
        double dim = Screen.getPrimary().getVisualBounds().getHeight() * 1 / 45;
        search.setDimension(dim, dim);
        search.setPosition(Screen.getPrimary().getVisualBounds().getWidth() * 4 / 20, Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.detailpage.getChildren().add(search);

        this.searchBar = new NewField("Search", Screen.getPrimary().getVisualBounds().getWidth() * 3 / 16, 40);
        this.searchBar.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 9 / 40);
        this.searchBar.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.searchBar.setOnKeyReleased(event -> {
            this.buffer.removeAll();
            boolean change = false;
            Manager m = Manager.getInstance();
            for (Commodity item: this.data) {
                if (item.getActive() && (item.getName().toLowerCase().contains(this.searchBar.getText().toLowerCase()) || item.getCategory().toLowerCase().contains(this.searchBar.getText().toLowerCase()) || String.valueOf(item.getId()).contains(this.searchBar.getText()) || String.valueOf(item.getPrice()).contains(this.searchBar.getText()))) {
                    this.addProductItem(item);
                    if (item.getId() == this.itemDetails) {
                        change = true;
                    }
                }
            }
            if (!change) {
                this.changeCurrentDetails(null);
            }
        });

        this.detailpage.getChildren().add(searchBar);

        this.buffer = new ScrollPanel(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8, Screen.getPrimary().getVisualBounds().getHeight() * 5 / 8);
        this.buffer.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.buffer.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 9 / 40);
        this.detailpage.getChildren().add(buffer);

        Manager m = Manager.getInstance();
        this.data = m.getInventoryContainer().getBuffer();
        for (Commodity item: this.data) {
            if (item.getActive()) {
                this.addProductItem(item);
            }
        }

        NewLabel headAdd = new NewLabel("Add Item To Directory", 60, "#867070", 700);
        headAdd.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        headAdd.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()*2/ 20);

        this.addItemBuffer = new AddItemBuffer();

        this.addItemPage.getChildren().addAll(headAdd, this.addItemBuffer);
        this.addItemBuffer.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        this.addItemBuffer.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()*4/ 20);

        this.backToItemDetails = new NewButton("Cancel", 140, 30);
        this.backToItemDetails.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight()*5/ 40);
        this.backToItemDetails.setLayoutX(this.addItemBuffer.getBg().getWidth() - this.backToItemDetails.getPrefWidth());
        this.addItemPage.getChildren().add(this.backToItemDetails);
        this.backToItemDetails.setOnMouseClicked(event -> {
            this.getChildren().remove(this.addItemPage);
            this.getChildren().add(this.detailpage);
            this.resetAddItemBuffer();
        });

        this.addItemBuffer.getSubmitButton().setOnMouseClicked(event -> {
            this.testItem();
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

    public void resetAddItemBuffer() {
        this.addItemBuffer.getImage().changeImage("Main/assets/products/No_Available_Image.png");
        this.addItemBuffer.getName().getTextField().clear();
        this.addItemBuffer.getCategory().getTextField().clear();
        this.addItemBuffer.getSellingPrice().getTextField().clear();
        this.addItemBuffer.getPurchasedPrice().getTextField().clear();
        this.addItemBuffer.getStock().getTextField().clear();
    }

    public void testItem() {
        boolean change = true;
        int newStock = 0;
        double newSellingPrice = 0, newPurchasedPrice = 0;
        if (!this.addItemBuffer.getStock().getTextField().getText().isEmpty()) {
            try {
                newStock = Integer.parseInt(this.addItemBuffer.getStock().getTextField().getText());
            } catch (NumberFormatException e) {
                change = false;
            }
        } else {
            change = false;
        }

        if (!this.addItemBuffer.getSellingPrice().getTextField().getText().isEmpty()) {
            try {
                newSellingPrice = Double.parseDouble(this.addItemBuffer.getSellingPrice().getTextField().getText());
            } catch (NumberFormatException e) {
                change = false;
            }
        } else {
            change = false;
        }

        if (!this.addItemBuffer.getPurchasedPrice().getTextField().getText().isEmpty()) {
            try {
                newPurchasedPrice = Double.parseDouble(this.addItemBuffer.getPurchasedPrice().getTextField().getText());
            } catch (NumberFormatException e) {
                change = false;
            }
        } else {
            change = false;
        }

        if (this.addItemBuffer.getName().getTextField().getText().isEmpty() || this.addItemBuffer.getCategory().getTextField().getText().isEmpty()) {
            change = false;
        }


        if (change) {
            Manager m = Manager.getInstance();
            int id = m.getInventoryContainer().getMaxID();
            Commodity product = new Product(new ArrayList<>(), id+1, newStock, this.addItemBuffer.getName().getTextField().getText(), newSellingPrice, newPurchasedPrice, this.addItemBuffer.getCategory().getTextField().getText(), this.addItemBuffer.getImage().getPath(), true);
            m.getInventoryContainer().addProduct(product);
            this.changeCurrentDetails(null);
            this.getChildren().remove(this.addItemPage);
            this.getChildren().add(this.detailpage);
            this.resetAddItemBuffer();
            this.refreshData();
        }
    }

    public void addProductItem(Commodity product) {
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
                m.getInventoryContainer().getProductById(productItem.getDetails().getProductid()).setImage(productItem.getDetails().getImage().getPath());

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
        for (Commodity item: this.data) {
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
