package org.example.program.components;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import lombok.*;
import java.nio.file.Files;
import java.io.*;

import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class AddItemBuffer extends Group {
    private NewImage image;
    private NewButton changeimg;
    private NewField name;
    private NewField category;
    private NewField stock;
    private NewField sellingPrice;
    private NewField purchasedPrice;
    private NewButton submitButton;
    private Background bg;
    private String url;

    public AddItemBuffer() {
        this.bg = new Background(Screen.getPrimary().getVisualBounds().getWidth()*7/10, Screen.getPrimary().getVisualBounds().getHeight()/2, "#F5EBEB");
        this.getChildren().add(bg);
        this.image = new NewImage("Main/assets/products/No_Available_Image.jpg");

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(40));
        hbox.setSpacing(40);

        this.image.setDimension(bg.getHeight() * 7/ 10, bg.getHeight() * 7/ 10);
        this.changeimg = new NewButton("Change Image", 150, 40);
        this.changeimg.setOnMouseClicked(event -> {
            FileChooser fd = new FileChooser();
            File filename = fd.showOpenDialog(null);
            if (filename != null) {
                Path src = Paths.get(filename.getPath());
                Path dest = Paths.get("Main/assets/products/" + src.getFileName());
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


        VBox vBox = new VBox(this.image, this.changeimg);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        VBox vBox2 = new VBox();
        vBox2.setAlignment(Pos.CENTER_LEFT);
        vBox2.setSpacing(10);

        hbox.getChildren().addAll(vBox, vBox2);
        this.getChildren().add(hbox);

        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4 = new HBox();
        HBox hbox5 = new HBox();

        NewLabel label1 = new NewLabel("Category", 22, "#867070", 700);
        NewLabel label2 = new NewLabel("Stock", 22, "#867070", 700);
        NewLabel label3 = new NewLabel("Selling Price", 22, "#867070", 700);
        NewLabel label4 = new NewLabel("Purchased Price", 22, "#867070", 700);
        NewLabel label5 = new NewLabel("Name", 22, "#867070", 700);

        label1.setPrefWidth(bg.getWidth()/4);
        label2.setPrefWidth(bg.getWidth()/4);
        label3.setPrefWidth(bg.getWidth()/4);
        label4.setPrefWidth(bg.getWidth()/4);
        label5.setPrefWidth(bg.getWidth()/4);

        this.category = new NewField("Category", bg.getWidth()/3, 40);
        this.stock = new NewField("Stock", bg.getWidth()/3, 40);
        this.sellingPrice = new NewField("Selling Price", bg.getWidth()/3, 40);
        this.purchasedPrice = new NewField("Purchased price", bg.getWidth()/3, 40);
        this.name = new NewField("Name", bg.getWidth()/3, 40);

        hbox1.getChildren().addAll(label1, this.category);
        hbox2.getChildren().addAll(label2, this.stock);
        hbox3.getChildren().addAll(label3, this.sellingPrice);
        hbox4.getChildren().addAll(label4, this.purchasedPrice);
        hbox5.getChildren().addAll(label5, this.name);

        vBox2.getChildren().addAll(hbox5, hbox1, hbox2, hbox3, hbox4);

        this.submitButton = new NewButton("Add", 200, 35);

        HBox buttons = new HBox(this.submitButton);
        buttons.setPrefWidth(bg.getWidth());
        buttons.setAlignment(Pos.CENTER);

        buttons.setPadding(new Insets(20, 0, 0, 0));

        vBox2.getChildren().add(buttons);

    }

}
