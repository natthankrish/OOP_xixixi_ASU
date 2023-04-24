package program.components;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.io.File;

public class CloseButton extends Button {
    private ImageView logo;
    private static ImageView PathToIV(String path) {
        File imgFile = new File(path);
        return new ImageView(imgFile.toURI().toString());
    }
    public CloseButton() {
        super();
        this.logo = PathToIV("../../../../assets/Close.png");
        this.setStyle("""
            -fx-pref-height: 23;
            -fx-pref-width: 23;
        """);
    }
}