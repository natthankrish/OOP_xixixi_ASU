package program.topbar;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import lombok.*;

@Getter
public class TopContainer extends Group {
    private Rectangle component;
    public TopContainer() {
        this.component = new Rectangle();
        this.component.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        this.component.setHeight(Screen.getPrimary().getVisualBounds().getHeight() / 15);
        this.component.setFill(Color.valueOf("#E4D0D0"));
        this.getChildren().add(this.component);
    }
}
