package org.example.program.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NewField extends VBox {
    private TextField textField;
    private StringProperty textProperty;
    double width;
    String bgColor;
    String color;
    int radius;
    double height;
    public NewField(double width, double height, String bgColor, String color, int radius) {
        super();
        this.height = height;
        this.width = width;
        this.color = color;
        this.bgColor = bgColor;
        this.radius = radius;
        textProperty = new SimpleStringProperty("");
        textField = new TextField();
        textField.setPromptText("Field...");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            textProperty.set(newValue);
        });

        getChildren().add(textField);
        setSpacing(5);
        setStyle();
    }
    public NewField(double width, double height){
        super();
        this.height = height;
        this.width = width;
        textProperty = new SimpleStringProperty("");
        textField = new TextField();
        textField.setPromptText("Field...");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            textProperty.set(newValue);
        });

        getChildren().add(textField);
        setSpacing(5);
        setStyle();
    }

    public NewField(String prompt, double width, double height){
        super();
        this.height = height;
        this.width = width;
        textProperty = new SimpleStringProperty("");
        textField = new TextField();
        textField.setPromptText(prompt);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            textProperty.set(newValue);
        });

        getChildren().add(textField);
        setSpacing(5);
        setStyle();
    }

    public String getText() {
        return textProperty.get();
    }

    public StringProperty textProperty() {
        return textProperty;
    }

    public void setStyle(){
        String width = "";
        String height = "";
        String bgColor = "-fx-background-color: #F5EBEB;";
        String color = "-fx-text-fill: #867070;";
        String radius = "-fx-background-radius: 20px;";
        if (this.height != 0){
            height = String.format("-fx-pref-height: %f; ", this.height);
        }
        if (this.width != 0){
            width = String.format("-fx-pref-width: %f;", this.width);
        }
        if (this.bgColor != null){
            bgColor = String.format("-fx-background-color: %s; ", this.bgColor);
        }
        if (this.color != null){
            color = String.format("-fx-text-fill: %s ;",this.color);
        }
        if (this.radius != 0){
            radius = String.format("-fx-background-radius: %dpx", this.radius);
        }

        String style = width + height + bgColor + color + radius + "-fx-border-color: #867070; " + "-fx-border-radius: 10px; ";
        textField.setStyle(style);
        setStyle(style);

    }
    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public TextField getTextField() {
        return textField;
    }
}


