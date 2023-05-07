package program.components;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import program.sidebar.ScrollTabPane;

public class ScrollPanel extends ScrollPane {
    protected VBox buffer;

    public ScrollPanel(double x, double y) {
        super();
        this.buffer = new VBox();
        this.setStyle("-fx-background:white;-fx-background-color:transparent;");
        this.setPrefSize(x, y);
        this.setLayoutX(x/20);
        this.setContent(this.buffer);
        this.setFitToWidth(true);
        this.buffer.setSpacing(10);
    }

    public void addItem(Node item) {
        this.buffer.getChildren().add(item);
    }
    public void removeItem(Node item) {
        this.buffer.getChildren().remove(item);
    }

    public void removeAll() {
        this.buffer.getChildren().clear();
    }

}
