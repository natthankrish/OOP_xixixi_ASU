package program.topbar;

import javafx.scene.Group;
import javafx.stage.Screen;

import lombok.*;
import program.components.Background;
import program.components.NewImage;

@Getter
public class TopContainer extends Group {
    private Background component;
    private NewImage logo;
    private LogoThread logoThread;
    private  TopbarFunctional topbar;
    public TopContainer() {
        this.component = new Rectangle();
        this.component.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        this.component.setHeight(Screen.getPrimary().getVisualBounds().getHeight() / 15);
        this.component.setFill(Color.valueOf("#E4D0D0"));
        this.getChildren().add(this.component);
    }
}
