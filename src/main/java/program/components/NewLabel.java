package program.components;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import lombok.Setter;

@Setter
public class NewLabel extends Label {
    private String labelText;
    private int size; // in pixel
    private String color;
    private String bgColor;
    private int weight;

    public NewLabel() {
        super("");
        this.size = 36;
        this.weight = 700;
        setFont(new Font("Inter", 36));
    }

    public NewLabel(String text, int size, String color, String bgColor, int weight) {
        super("");
        this.labelText = text;
        this.size = size;
        this.color = color;
        this.bgColor = bgColor;
        this.weight = weight;
    }
    public void setStyle() {
        String colorStyle = "";
        String sizeStyle = "";
        String weightStyle = "";
        String bgStyle = "";
        if (this.labelText != null) {
            setText(this.labelText);
        }
        if (this.color != null) {
            colorStyle = String.format("-fx-text-fill: %s ", this.color);
        }
        if (this.size != 0) {
            int fontSizeInPoint = (int)(this.size * 0.75);
            sizeStyle = String.format("-fx-font-family: Inter; -fx-font-size: %dpx; ", fontSizeInPoint);
        }
        if (this.weight != 0) {
            weightStyle = String.format("-fx-font-weight: %d; ", this.weight);
        }
        if (this.bgColor != null) {
            bgStyle = String.format("-fx-background-color: %s; ", this.bgColor);
        }
        String style = colorStyle + sizeStyle + weightStyle + bgStyle;
        setStyle(style);
    }

    // example on how to call
    //    NewLabel label = new NewLabel();
    //        label.setColor("#867070");
    //        label.setLabelText("Hello");
    //        label.setBgColor("#FFFFF");
    //        label.setSize(32);
    //        label.setWeight(700);
    //        label.setStyle();
    //    NewLabel labelh1 = new NewLabel("Hello", 32, "#867070", "#FFFFFF", 700);
    //        labelh1.setStyle();
}
