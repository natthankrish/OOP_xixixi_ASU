package program.sidebar;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import lombok.*;
import program.components.Clock;

@Getter
public class SideContainer extends Group {
    private Rectangle component;
    private Clock clock;
    private ClockThread clockThread;
    public SideContainer() {
        this.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 15);

        this.component = new Rectangle();
        this.component.setWidth(Screen.getPrimary().getVisualBounds().getWidth() / 5);
        this.component.setHeight(Screen.getPrimary().getVisualBounds().getHeight() * 14 / 15);
        this.component.setFill(Color.valueOf("#F5EBEB"));
        this.getChildren().add(this.component);

        this.clock = new Clock(this.component);
        this.getChildren().add(this.clock);

//        this.clockThread = new ClockThread(this.clock);
    }
}
