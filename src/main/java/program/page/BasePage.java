package program.page;

import javafx.scene.Group;
import javafx.stage.Screen;
import program.components.Background;

public class BasePage extends Group {
    Background background;

    public BasePage() {
        this.background = new Background(Screen.getPrimary().getVisualBounds().getWidth() * 4 / 5, Screen.getPrimary().getVisualBounds().getHeight() * 14 / 15, "#FFFFFF");
        this.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 5);
        this.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 15);
        this.getChildren().add(this.background);
    }

    public void changeBackground(String hex) {
        this.background.changeBackground(hex);
    }
}
