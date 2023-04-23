package program.components;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

public class NewLabel extends Label {
    public NewLabel(String text, @NotNull String header, String color) {
        super(text);
        String style = "";
        if (header.equals("h1")) {
            if (color.equals("brown")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 48px; -fx-text-fill: #867070;";
            } else if (color.equals("white")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 48px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("h2")) {
            if (color.equals("brown")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 32px; -fx-text-fill: #867070;";
            } else if (color.equals("white")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 32px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("h3")) {
            if (color.equals("brown")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 24px; -fx-text-fill: #867070;";
            } else if (color.equals("white")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 24px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("h4")) {
            if (color.equals("brown")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 19px; -fx-text-fill: #867070;";
            } else if (color.equals("putih")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 19px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("text2")) {
            if (color.equals("brown")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 12px; -fx-text-fill: #867070;";
            } else if (color.equals("white")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 12px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("text1")) {
            if (color.equals("brown")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 15px; -fx-text-fill: #867070;";
            } else if (color.equals("white")) {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 15px; -fx-text-fill: #FFFFFF;";
            }
        }
        super.setStyle(style);
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
