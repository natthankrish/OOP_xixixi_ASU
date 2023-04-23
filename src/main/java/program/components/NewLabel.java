package program.components;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewLabel extends Label{
    private String labelText;
    private int size; // in pixel
    private String color;
    private String bgColor;
    private int weight;

    public NewLabel() {
        super("");
        this.size = 0;
        this.weight = 0;
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
        if (this.labelText != null) {
            setText(this.labelText);
        }
        if (this.color != null) {
            Color color = Color.web(this.color);
            setTextFill(color);
        }
        if (this.size != 0) {
            int fontSizeInPoint = (int)(this.size * 0.75);
            setFont(new Font("Inter", fontSizeInPoint));
        }
        if (this.weight != 0) {
            setStyle(String.format("-fx-font-weight: %d;", this.weight));
        }
        if (this.bgColor != null) {
            setStyle(String.format("-fx-background-color: %s;", this.bgColor));
        }
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
