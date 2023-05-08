package org.example.program.components;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class CloseAllButton extends Button {
    public CloseAllButton() {
        setStyle("""
            -fx-text-fill: #F5EBEB;
            -fx-background-color: #867070;
            -fx-pref-height: 35;
            -fx-pref-width: 80;
            -fx-font-size: 12;
            -fx-font-weight: bold;
            -fx-background-radius: 14;   
        """);
        this.setText("Close All");
        ScaleTransition scaleTransition = new ScaleTransition(new Duration(0.2), this);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(0.8);
        scaleTransition.setToY(0.8);
        scaleTransition.setAutoReverse(true);
        this.setOnMousePressed(event -> scaleTransition.playFromStart());
        this.setOnMouseReleased(event -> {
            scaleTransition.stop();
            this.setScaleX(1);
            this.setScaleY(1);
        });

    }

    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
