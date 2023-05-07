package org.example.program.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;

@Getter
public class NewImage extends ImageView {
    private Image source;
    private String path;

    public NewImage(String path) {
        this.source = new Image("file:" + path);
        this.setImage(this.source);
        this.path = path;
    }


    public void changeImage(String newPath) {
        this.source = new Image("file:" + newPath);
        this.setImage(this.source);
        this.path = newPath;
    }

    public void setDimension(double width, double height) {
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public void setPosition(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

}
