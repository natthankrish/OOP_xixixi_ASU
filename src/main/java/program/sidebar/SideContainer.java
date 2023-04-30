package program.sidebar;

import javafx.scene.Group;
import javafx.stage.Screen;
import lombok.*;
import program.components.*;

@Getter
public class SideContainer extends Group {
    private Background component;
    private NewLabel head;
    private CloseAllButton closeAllButton;
    private Clock clock;
    private ScrollTabPane tabsContainer;

    public SideContainer() {
        this.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 15);

        this.component = new Background(Screen.getPrimary().getVisualBounds().getWidth() / 5, Screen.getPrimary().getVisualBounds().getHeight() * 14 / 15, "#F5EBEB");
        this.getChildren().add(this.component);

        this.head = new NewLabel("Tabs", 40, "#867070", 700);
        this.head.setLayout(this.component.getWidth()/15, this.component.getWidth()/25);
        this.getChildren().add(this.head);

        this.clock = new Clock(this.component);
        this.clock.setLayout(this.component.getHeight() * 69 / 80, this.component.getWidth() * 1 / 10);
        this.getChildren().add(this.clock);

        this.tabsContainer = new ScrollTabPane(this.component.getWidth(), this.component.getHeight());
        this.getChildren().add(this.tabsContainer);
        this.tabsContainer.setLayoutY(100);

        new ClockThread(this.clock).start();
    }
}
