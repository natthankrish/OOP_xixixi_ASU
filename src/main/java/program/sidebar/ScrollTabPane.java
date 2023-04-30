package program.sidebar;

import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public class ScrollTabPane extends ScrollPane {
    TabPane tabContainer;
    public ScrollTabPane(double x, double y) {
        super();
        this.tabContainer = new TabPane();
        this.tabContainer.setBackground(Background.fill(Color.valueOf("#F5EBEB")));
        this.setBackground(Background.fill(Color.valueOf("#F5EBEB")));
        this.setStyle("-fx-background:#F5EBEB;-fx-background-color:transparent;");
        this.setContent(this.tabContainer);
        this.setPrefSize(9*x/10, 200);
        this.setVertical();

        this.setLayoutX(x/20);
    }
    public void setVertical() {
        this.tabContainer.setSide(Side.LEFT);
        this.tabContainer.setRotateGraphic(true);
        Label l = null;
        StackPane stp = null;
        for(Tab t : this.tabContainer.getTabs()){
            l = new Label(t.getText());
            l.setRotate(90);
            stp = new StackPane(new Group(l));
            stp.setRotate(90);
            t.setGraphic(stp);
            t.setText("");
        }
    }
}
