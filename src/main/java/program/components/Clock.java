package program.components;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lombok.*;

@Getter
public class Clock extends Group {
    private Background background;
    private NewLabel time;
    private NewLabel date;
    private NewLabel month;
    private NewLabel year;

    public Clock(Background rec) {
        this.setLayoutY(rec.getHeight() * 69 / 80);
        this.setLayoutX(rec.getWidth() * 1 / 10);

        this.time = new NewLabel("23:59:59", 36, "#E4D0D0", 700);
//        this.time.setText("23:59:59");
        this.time.setLayoutX(rec.getWidth() * 3 / 100);
        this.time.setLayoutY(rec.getHeight() * 9 / 400);
//        this.time.setTextFill(Color.valueOf("#E4D0D0"));
//        this.time.setFont(Font.font("Inter", FontWeight.BOLD, 36));

        this.background = new Background(165, 45, "#867070");
        this.background.setLayoutY(rec.getHeight() * 5 / 200);
        this.background.setArc(20);

        this.date = new NewLabel();
        this.date.setText("23");
        this.date.setLayoutX(rec.getWidth() * 45 / 100);
        this.date.setTextFill(Color.valueOf("#867070"));
        this.date.setFont(Font.font("Inter", FontWeight.BOLD, 60));

        this.month = new NewLabel();
        this.month.setText("AUG");
        this.month.setLayoutX(rec.getWidth() * 65 / 100);
        this.month.setLayoutY(rec.getHeight() * 5 / 500);
        this.month.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        this.month.setTextFill(Color.valueOf("#867070"));

        this.year = new NewLabel();
        this.year.setText("2023");
        this.year.setLayoutX(rec.getWidth() * 65 / 100);
        this.year.setLayoutY(rec.getHeight() * 20 / 500);
        this.year.setTextFill(Color.valueOf("#867070"));
        this.year.setFont(Font.font("Inter", FontWeight.BOLD, 24));

        this.getChildren().add(this.background);
        this.getChildren().add(this.time);
        this.getChildren().add(this.date);
        this.getChildren().add(this.month);
        this.getChildren().add(this.year);
    }
}
