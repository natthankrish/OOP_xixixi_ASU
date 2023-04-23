package program.topbar;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class TopContainer {
    private Group root;
    private Rectangle component;
    public TopContainer() {
        this.root = new Group();
        this.component = new Rectangle();
        this.component.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        this.component.setHeight(Screen.getPrimary().getVisualBounds().getHeight() / 15);
        this.component.setFill(Color.valueOf("#E4D0D0"));
        this.root.getChildren().add(this.component);
    }

    public Group getComponent() {
        return root;
    }
}
