package program.components;

import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class VerticalTabPane extends TabPane {
    public VerticalTabPane() {
        super();
    }
    public void setVertical() {
        setSide(Side.LEFT);
        setRotateGraphic(true);
        Label l = null;
        StackPane stp = null;
        for(Tab t : getTabs()){
            l = new Label(t.getText());
            l.setRotate(90);
            stp = new StackPane(new Group(l));
            stp.setRotate(90);
            t.setGraphic(stp);
            t.setText("");
        }

        setTabMinHeight(100);
        setTabMaxHeight(100);
    }
}
