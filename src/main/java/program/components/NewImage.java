package program.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NewImage extends ImageView {
    private Image source;
    private int id;

    public NewImage(String path) {
        this.source = new Image("file:" + path);
        this.setImage(this.source);
    }

    public NewImage(String path, int id) {
        this.source = new Image("file:" + path);
        this.setImage(this.source);
        this.id = id;
    }

    public void changeImage(String newPath) {
        this.source = new Image("file:" + newPath);
        this.setImage(this.source);
    }

    public void setDimension(double width, double height) {
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public void setPosition(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public void nextImage() {
        this.id++;
        if (this.id == 13) {
            this.id = 1;
        }
        this.changeImage("assets/logo/" + this.id + ".png");
    }

}
