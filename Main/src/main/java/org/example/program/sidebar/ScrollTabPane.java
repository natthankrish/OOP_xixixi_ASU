package org.example.program.sidebar;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.example.program.components.CloseAllButton;
import org.example.program.components.NewTab;

@Getter
public class ScrollTabPane extends ScrollPane {
    private VBox buffer;
    private static CloseAllButton closeAllButton;
    public ScrollTabPane(double x, double y, CloseAllButton closeAllButton) {
        super();
        this.buffer = new VBox();
        ScrollTabPane.closeAllButton = closeAllButton;
        this.setBackground(Background.fill(Color.valueOf("#F5EBEB")));
        this.setStyle("-fx-background:#F5EBEB;-fx-background-color:transparent;");
        this.setPrefSize(9*x/10, y);
        this.setLayoutX(x/20);
        this.setContent(this.buffer);
        this.setFitToWidth(true);
        this.buffer.setSpacing(10);
    }

    public void addTab(String text) {
        this.buffer.getChildren().add(new NewTab(text, this.buffer.getChildren(), this.getPrefWidth(), ScrollTabPane.closeAllButton, this.buffer));
    }

    public void addTabPlugin(String text, Group page) {
        this.buffer.getChildren().add(new NewTab(text, this.buffer.getChildren(), this.getPrefWidth(), ScrollTabPane.closeAllButton, this.buffer, page));
    }

}
