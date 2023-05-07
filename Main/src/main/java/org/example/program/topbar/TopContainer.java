package org.example.program.topbar;

import javafx.scene.Group;
import javafx.stage.Screen;
import lombok.*;
import org.example.program.components.Background;
import org.example.program.components.NewImage;
import org.example.program.sidebar.ScrollTabPane;

@Getter
public class TopContainer extends Group {
    private TopbarFunctional topbar;
    private Background component;
    private NewImage logo;

    public TopContainer(ScrollTabPane tab) {
        this.topbar = new TopbarFunctional(tab);
        this.component = new Background(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight() / 15, "#E4D0D0");
        this.getChildren().addAll(this.component, this.topbar);

        this.logo = new NewImage("assets/logo/1.png");
        this.logo.setDimension(this.component.getHeight() * 32 / 27, this.component.getHeight() * 2 / 3);
        this.logo.setPosition(this.component.getWidth() - (this.logo.getFitWidth() * 1.5), this.component.getHeight()/2 - this.logo.getFitHeight()/2);
        this.getChildren().add(this.logo);

        new LogoThread(this.logo).start();
    }
}