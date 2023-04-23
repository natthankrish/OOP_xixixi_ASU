package program.components;

import javafx.scene.control.Tab;
import lombok.Setter;

@Setter
public class NewTab extends Tab {
    private String tabText;
    private int size; // in pixel
    private String color;
    private String bgColor;
    private int weight;
    public NewTab() {
        super("");
    }
    public NewTab(String tabText, int size, String color, String bgColor, int weight) {
        super("");
        this.tabText = tabText;
        this.size = size;
        this.color = color;
        this.bgColor = bgColor;
        this.weight = weight;
        setStyle();
    }

    public void setStyle() {
        String colorStyle = "";
        String sizeStyle = "";
        String weightStyle = "";
        String bgStyle = "";
        if (this.tabText != null) {
            setText(this.tabText);
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
}
