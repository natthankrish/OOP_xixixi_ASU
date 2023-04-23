package program.sidebar;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import lombok.*;

@Getter
public class SideContainer extends Group {
    private Rectangle component;
    public SideContainer() {
        this.component = new Rectangle();
        this.component.setWidth(Screen.getPrimary().getVisualBounds().getWidth() / 5);
        this.component.setHeight(Screen.getPrimary().getVisualBounds().getHeight() * 14 / 15);
        this.component.setY(Screen.getPrimary().getVisualBounds().getHeight() / 15);
        this.component.setFill(Color.valueOf("#F5EBEB"));
        this.getChildren().add(this.component);
    }
}
