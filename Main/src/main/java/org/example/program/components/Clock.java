package org.example.program.components;

import javafx.scene.Group;
import lombok.*;

@Getter
public class Clock extends Group {
    private Background background;
    private NewLabel time;
    private NewLabel date;
    private NewLabel month;
    private NewLabel year;

    public Clock(Background rec) {
        this.time = new NewLabel("23:59:59", 36, "#E4D0D0", 700);
        this.time.setLayout(rec.getWidth() * 3 / 100, rec.getHeight() * 9 / 400);

        this.background = new Background(165, 45, "#867070");
        this.background.setPosition(0,rec.getHeight() * 5 / 200);
        this.background.setArc(20);

        this.date = new NewLabel("23", 60, "#867070", 700);
        this.date.setLayout(rec.getWidth() * 45 / 100, 0);

        this.month = new NewLabel("DEC", 24, "#867070", 700);
        this.month.setLayout(rec.getWidth() * 65 / 100, rec.getHeight() * 5 / 500);

        this.year = new NewLabel("9999", 24, "#867070", 700);
        this.year.setLayout(rec.getWidth() * 65 / 100, rec.getHeight() * 20 / 500);

        this.getChildren().add(this.background);
        this.getChildren().add(this.time);
        this.getChildren().add(this.date);
        this.getChildren().add(this.month);
        this.getChildren().add(this.year);
    }

    public void setLayout(double x, double y) {
        this.setLayoutY(y);
        this.setLayoutX(x);
    }
}
