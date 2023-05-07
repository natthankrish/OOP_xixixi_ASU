package org.example.program.sidebar;

import javafx.scene.Group;
import javafx.stage.Screen;
import lombok.*;
import org.example.program.components.Background;
import org.example.program.components.Clock;
import org.example.program.components.CloseAllButton;
import org.example.program.components.NewLabel;

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

        this.closeAllButton = new CloseAllButton();
        this.closeAllButton.setLayout(this.component.getWidth() * 11/15, this.component.getWidth()*2/25);
        this.getChildren().add(this.closeAllButton);

        this.clock = new Clock(this.component);
        this.clock.setLayout(this.component.getWidth() * 1 / 10, this.component.getHeight() * 69 / 80);
        this.getChildren().add(this.clock);

        this.tabsContainer = new ScrollTabPane(this.component.getWidth(), this.component.getHeight() * 60 /80, this.closeAllButton);
        this.getChildren().add(this.tabsContainer);
        this.tabsContainer.setLayoutY(this.component.getWidth()*5/20);

        new ClockThread(this.clock).start();
    }
}
