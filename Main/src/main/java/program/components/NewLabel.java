package program.components;
import javafx.scene.control.Label;

public class NewLabel extends Label {
    private String labelText;
    private int size; // in pixel
    private String color;
    private String bgColor;
    private int weight;

    public NewLabel() {
        super("");
        this.size = 0;
        this.weight = 0;
        setStyle();
    }

    public NewLabel(String text, int size, String color, String bgColor, int weight) {
        super(text);
        this.labelText = text;
        this.size = size;
        this.color = color;
        this.bgColor = bgColor;
        this.weight = weight;
        setStyle();
    }
    public NewLabel(String text, int size, String color, int weight) {
        super(text);
        this.labelText = text;
        this.size = size;
        this.color = color;
        this.weight = weight;
        setStyle();
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public void setColor(String color) {
        this.color = color;
        setStyle();
    }

    public  void setSize (int size) {
        this.size = size;
        setStyle();
    }

    public void setBgColor (String bgColor) {
        this.bgColor = bgColor;
        setStyle();
    }

    public void setWeight (int weight) {
        this.weight = weight;
        setStyle();
    }

    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void setStyle() {
        String colorStyle = "";
        String sizeStyle = "";
        String weightStyle = "";
        String bgStyle = "";
        if (this.color != null) {
            colorStyle = String.format("-fx-text-fill: %s ;", this.color);
        }
        if (this.size != 0) {
            sizeStyle = String.format("-fx-font-family: Inter; -fx-font-size: %dpx; ", this.size);
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
