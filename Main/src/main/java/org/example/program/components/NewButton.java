package org.example.program.components;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class NewButton extends Button {
    public NewButton(String text, double width, double height) {
        setStyle("""
            -fx-text-fill: #F5EBEB;
            -fx-background-color: #867070;
            -fx-font-size: 15;
            -fx-font-weight: bold;
            -fx-background-radius: 9;
        """);
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.setText(text);;
    }

    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
