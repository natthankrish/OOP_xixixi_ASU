package org.example.program.components;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;


public class CloseButton extends Button {

    private ImageView img;
    public CloseButton() {
        super();
        this.img = new ImageView("file:assets/Close.png");
        this.setStyle("""
            -fx-border-color: transparent;
            -fx-border-width: 0;
            -fx-background-radius: 0;
            -fx-background-color: transparent;
        """);
        this.setBackground(Background.EMPTY);
        this.img.setFitWidth(35);
        this.img.setFitHeight(35);
        this.setGraphic(this.img);
    }

}