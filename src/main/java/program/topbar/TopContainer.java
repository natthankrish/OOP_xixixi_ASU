package program.topbar;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import lombok.*;
import program.components.NewImage;

@Getter
public class TopContainer extends Group {
    private Rectangle component;
    private NewImage logo;
    private LogoThread logoThread;
    public TopContainer() {
        this.component = new Rectangle();
        this.component.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        this.component.setHeight(Screen.getPrimary().getVisualBounds().getHeight() / 15);
        this.component.setFill(Color.valueOf("#E4D0D0"));
        this.getChildren().add(this.component);

        this.logo = new NewImage("assets/logo/1.png");
        this.logo.setDimension(this.component.getHeight() * 32 / 27, this.component.getHeight() * 2 / 3);
        this.logo.setPosition(this.component.getWidth() - (this.logo.getFitWidth() * 1.5), this.component.getHeight()/2 - this.logo.getFitHeight()/2);
        this.getChildren().add(this.logo);

        this.logoThread = new LogoThread(this.logo);
    }
}
