package program.components;
import javafx.scene.control.Button;

public class CloseAllButton extends Button {
    public CloseAllButton() {
        setStyle("""
        -fx-text-fill: #F5EBEB;
        -fx-background-color: #867070;
        -fx-pref-height: 23.3;
        -fx-pref-width: 72;
        -fx-font-size: 8;
        -fx-font-weight: bold;
        -fx-background-radius: 9;
        
        """);
        this.setText("Close All");
    }

    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
