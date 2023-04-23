package program.components;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lombok.*;
import program.components.NewLabel;

@Getter
public class Clock extends Group {
    private Rectangle background;
    private NewLabel time;
    private NewLabel date;
    private NewLabel month;
    private NewLabel year;

    public Clock() {
        this.setLayoutY(820);
        this.setLayoutX(40);

        this.time = new NewLabel();
        this.time.setText("23:59:59");
        this.time.setLayoutX(10);
        this.time.setLayoutY(20);
        this.time.setTextFill(Color.valueOf("#E4D0D0"));
        this.time.setFont(Font.font("Inter", FontWeight.BOLD, 36));

        this.background = new Rectangle();
        this.background.setWidth(165);
        this.background.setHeight(45);
        this.background.setFill(Color.valueOf("#867070"));
        this.background.setLayoutY(25);
        this.background.setArcHeight(20);
        this.background.setArcWidth(20);

        this.date = new NewLabel();
        this.date.setText("23");
        this.date.setLayoutX(170);
        this.date.setTextFill(Color.valueOf("#867070"));
        this.date.setFont(Font.font("Inter", FontWeight.BOLD, 60));

        this.month = new NewLabel();
        this.month.setText("AUG");
        this.month.setLayoutX(245);
        this.month.setLayoutY(15);
        this.month.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        this.month.setTextFill(Color.valueOf("#867070"));

        this.year = new NewLabel();
        this.year.setText("2023");
        this.year.setLayoutX(245);
        this.year.setLayoutY(38);
        this.year.setTextFill(Color.valueOf("#867070"));
        this.year.setFont(Font.font("Inter", FontWeight.BOLD, 24));

        this.getChildren().add(this.background);
        this.getChildren().add(this.time);
        this.getChildren().add(this.date);
        this.getChildren().add(this.month);
        this.getChildren().add(this.year);
    }
}
