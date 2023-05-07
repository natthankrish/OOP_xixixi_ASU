package program.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import program.containers.Manager;
import program.entities.Product;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductDetails extends ScrollPanel {

    private int productid;
    private NewField stock;
    private NewLabel name;
    private NewField purchasedPrice;
    private NewField sellingPrice;
    private NewLabel category;
    private NewButton status;
    private NewImage image;

    private NewButton naAnymore;
    private NewButton changeImage;
    private NewButton confirmChanges;

    public ProductDetails(Product product) {
        super(Screen.getPrimary().getVisualBounds().getWidth() * 5 / 16, Screen.getPrimary().getVisualBounds().getHeight() * 5 / 8);
        this.productid = product.getId();

        this.buffer.setPadding(new Insets(30, this.getPrefWidth()/20, 30, this.getPrefWidth()/20));
        this.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 9 / 20);
        this.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 40);
        this.setStyle("-fx-background:#F5EBEB;-fx-background-color:transparent;");
        this.buffer.setAlignment(Pos.TOP_CENTER);

        this.image = new NewImage(product.getImage());
        this.image.setDimension(this.getPrefWidth()/2,this.getPrefWidth()/2);
        this.addItem(this.image);

        this.changeImage = new NewButton("Change Image", 150, 10);
        this.changeImage.setOnMouseClicked(event -> {
            FileChooser fd = new FileChooser();
            File filename = fd.showOpenDialog(null);
            if (filename != null) {
                Path src = Paths.get(filename.getPath());
                Path dest = Paths.get("assets/products/" + src.getFileName());
                File destFile = new File(dest.toString());
                if (!destFile.exists()) {
                    try {
                        Files.copy(src, dest);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                this.image.changeImage(dest.toString());
            }
        });
        this.addItem(this.changeImage);

        HBox titleHBox = new HBox();
        titleHBox.setPrefWidth(this.getPrefWidth());
        titleHBox.setAlignment(Pos.CENTER_LEFT);

        VBox name = new VBox();
        name.setAlignment(Pos.CENTER_LEFT);
        name.setPrefWidth(this.getPrefWidth() * 2 / 3);

        this.name = new NewLabel(product.getName(), 27, "#867070", 700);
        name.getChildren().add(this.name);
        name.getChildren().add(new NewLabel("ID"+ product.getId(), 20, "#867070", 700));

        this.status = new NewButton(product.getStock() == 0 ? "Sold" : "Available", 100, 40);
        this.status.setDisable(true);

        HBox statusbuf = new HBox(this.status);
        statusbuf.setPrefWidth(this.getPrefWidth()/ 3);
        statusbuf.setAlignment(Pos.CENTER);

        titleHBox.getChildren().addAll(name, statusbuf);
        this.addItem(titleHBox);

        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4 = new HBox();

        NewLabel label1 = new NewLabel("Category", 22, "#867070", 700);
        NewLabel label2 = new NewLabel("Stock", 22, "#867070", 700);
        NewLabel label3 = new NewLabel("Selling Price", 22, "#867070", 700);
        NewLabel label4 = new NewLabel("Purchased Price", 22, "#867070", 700);

        label1.setPrefWidth(this.getPrefWidth()/2);
        label2.setPrefWidth(this.getPrefWidth()/2);
        label3.setPrefWidth(this.getPrefWidth()/2);
        label4.setPrefWidth(this.getPrefWidth()/2);

        List<String> wkwk = new ArrayList<>();
        this.category = new NewLabel(product.getCategory(), 22, "#867070", 700);
        this.stock = new NewField(String.valueOf(product.getStock()), this.getPrefWidth()/3, 40);
        this.sellingPrice = new NewField(String.valueOf(product.getPrice()), this.getPrefWidth()/3, 40);
        this.purchasedPrice = new NewField(String.valueOf(product.getPurchasePrice()), this.getPrefWidth()/3, 40);

        hbox1.getChildren().addAll(label1, this.category);
        hbox2.getChildren().addAll(label2, this.stock);
        hbox3.getChildren().addAll(label3, this.sellingPrice);
        hbox4.getChildren().addAll(label4, this.purchasedPrice);

        this.addItem(hbox1);
        this.addItem(hbox2);
        this.addItem(hbox3);
        this.addItem(hbox4);

        HBox buttons = new HBox();
        buttons.setPrefWidth(this.getPrefWidth());
        buttons.setAlignment(Pos.CENTER);

        buttons.setPadding(new Insets(20, 0, 0, 0));

        this.naAnymore = new NewButton("Not Available Anymore", 200, 35);

        HBox naAnymorebuf = new HBox(this.naAnymore);
        naAnymorebuf.setPrefWidth(this.getPrefWidth()/ 2);
        naAnymorebuf.setAlignment(Pos.CENTER);

        this.confirmChanges = new NewButton("Confirm Changes", 200, 35);

        HBox confirmChangesBuf = new HBox(this.confirmChanges);
        confirmChangesBuf.setPrefWidth(this.getPrefWidth()/ 2);
        confirmChangesBuf.setAlignment(Pos.CENTER);

        buttons.getChildren().addAll(naAnymorebuf, confirmChangesBuf);

        this.addItem(buttons);

    }

}
