package org.example.program.components;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class CustomButton extends Button {
    private String text;
    private int fontSize; // in pixel
    private String textColor;
    private String bgColor;
    private String fontWeight;

    public CustomButton() {
        super();
        this.fontSize = 0;
        this.fontWeight = "bold";
//        setStyle();
        setHoverStyle();
    }

    public CustomButton(String text, int fontSize, String textColor, String bgColor, String fontWeight, int radiuskiriatas, int radiuskananatas, int radiuskananbawah, int radiuskiribawah) {
        super(text);
        this.text = text;
        this.fontSize = fontSize;
        this.textColor = textColor;
        this.bgColor = bgColor;
        this.fontWeight = fontWeight;
        setStyle(radiuskiriatas,radiuskananatas,radiuskananbawah,radiuskiribawah);
        setHoverStyle();
    }

//    public CustomButton(String text, int fontSize, String textColor, int fontWeight) {
//        super(text);
//        this.text = text;
//        this.fontSize = fontSize;
//        this.textColor = textColor;
//        this.fontWeight = fontWeight;
////        setStyle();
//        setHoverStyle();
//    }

//    public void setText(String text) {
//        this.text = text;
//        setText(text);
//        setStyle();
////    }
//
//    public void setTextColor(String textColor) {
//        this.textColor = textColor;
//        setStyle();
//    }
//
//    public void setFontSize(int fontSize) {
//        this.fontSize = fontSize;
//        setStyle();
//    }
//
//    public void setBgColor(String bgColor) {
//        this.bgColor = bgColor;
//        setStyle();
//    }
//
//    public void setFontWeight(int fontWeight) {
//        this.fontWeight = fontWeight;
//        setStyle();
//    }

    public void setStyle(int radiuskiriatas, int radiuskananatas, int radiuskananbawah, int radiuskiribawah) {
        String textColorStyle = "";
        String fontSizeStyle = "";
        String fontWeightStyle = "";
        String bgColorStyle = "";
        String set = String.format("-fx-background-radius: %dpx %dpx %dpx %dpx;", radiuskiriatas, radiuskananatas, radiuskananbawah, radiuskiribawah);


        if (this.textColor != null) {
            textColorStyle = String.format("-fx-text-fill: %s ;", this.textColor);
        }
        if (this.fontSize != 0) {
            fontSizeStyle = String.format("-fx-font-family: Inter; -fx-font-size: %dpx; ", this.fontSize);
        }
        if (this.fontWeight != null) {
            fontWeightStyle = String.format("-fx-font-weight: %s; ", this.fontWeight);
        }
        if (this.bgColor != null) {
            bgColorStyle = String.format("-fx-background-color: %s; ", this.bgColor);
        }


        String style = textColorStyle + fontSizeStyle + fontWeightStyle + bgColorStyle + set;
        setStyle(style);
    }

    public void setHoverStyle() {
        String hoverStyle = "-fx-background-color: #CCCCCC;";
        setOnMouseEntered(event -> {
            setCursor(Cursor.HAND);
            setStyle(getStyle() + hoverStyle);

        });
        setOnMouseExited(e -> setStyle(getStyle().replace(hoverStyle, "")));
        ;}

    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public String getDefaultStyle() {
        return "-fx-background-color: #867070; " +
                "-fx-text-fill: #FFFFFF ;" +
                "-fx-font-family: Inter; " +
                "-fx-font-size: 12px; " +
                "-fx-font-weight: bold; " +
                "fx-background-radius: 10px 10px 10px 10px;"
                ;
    }

    // Example usage:
    // CustomButton btn = new CustomButton();
    // btn.setText("Click me");
    // btn.setTextColor("#FFFFFF");
    // btn.setBgColor("#000000");
    // btn.setFontSize(16);
    // btn.setFontWeight(700);
    // btn.setStyle();
    // CustomButton btn2 = new CustomButton("Click me too", 16, "#FFFFFF", "#000000", 700);
    // btn2.setStyle();
}