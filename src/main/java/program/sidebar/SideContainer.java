package program.sidebar;

import javafx.scene.Group;
import javafx.stage.Screen;
import lombok.*;
import program.components.Background;
import program.components.Clock;
import program.components.VerticalTabPane;

@Getter
public class SideContainer extends Group {
    private Background component;
    private Clock clock;
    private VerticalTabPane tabsContainer;

    public SideContainer() {
        this.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 15);

        this.component = new Background(Screen.getPrimary().getVisualBounds().getWidth() / 5, Screen.getPrimary().getVisualBounds().getHeight() * 14 / 15, "#F5EBEB");
        this.getChildren().add(this.component);

        this.clock = new Clock(this.component);
        this.getChildren().add(this.clock);

        this.tabsContainer = new VerticalTabPane();
        this.getChildren().add(this.tabsContainer);
        new ClockThread(this.clock).start();
    }
}
