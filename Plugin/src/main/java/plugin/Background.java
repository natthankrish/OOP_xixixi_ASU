package plugin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Background extends Rectangle {

    public Background (double x, double y) {
        this.setWidth(x);
        this.setHeight(y);
    }

    public Background (double x, double y, String color) {
        this.setWidth(x);
        this.setHeight(y);
        this.setFill(Color.valueOf(color));
    }

    public Background (double x, double y, String color, double arc, double posx, double posy) {
        this.setWidth(x);
        this.setHeight(y);
        this.setFill(Color.valueOf(color));
        this.setArcWidth(arc);
        this.setArcHeight(arc);
        this.setLayoutX(posx);
        this.setLayoutY(posy);
    }

    public void changeColor (String color) {
        this.setFill(Color.valueOf(color));
    }

    public void setPosition (double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void setArc (double x) {
        this.setArcWidth(x);
        this.setArcHeight(x);
    }

    public void changeBackground(String hex) {
        this.setFill(Color.valueOf(hex));
    }
}
