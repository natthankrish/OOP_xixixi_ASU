package program;
import javafx.scene.control.Label;

public class NewLabel extends Label {
    public NewLabel(String text, String header, String color) {
        super(text);
        String style = "";
        if (header.equals("h1")) {
            if (color == "brown") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 48px; -fx-text-fill: #867070;";
            } else if (color == "white") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 48px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("h2")) {
            if (color == "brown") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 32px; -fx-text-fill: #867070;";
            } else if (color == "white") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 32px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header == "h3") {
            if (color == "brown") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 24px; -fx-text-fill: #867070;";
            } else if (color == "white") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 24px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("h4")) {
            if (color == "brown") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 19px; -fx-text-fill: #867070;";
            } else if (color == "putih") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 19px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("text2")) {
            if (color == "brown") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 12px; -fx-text-fill: #867070;";
            } else if (color == "white") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 12px; -fx-text-fill: #FFFFFF;";
            }
        } else if (header.equals("text1")) {
            if (color == "brown") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 15px; -fx-text-fill: #867070;";
            } else if (color == "white") {
                style = "-fx-font-family: Inter; -fx-font-style: normal; -fx-font-weight: 700; -fx-font-size: 15px; -fx-text-fill: #FFFFFF;";
            }
        }
        super.setStyle(style);
    }
}
