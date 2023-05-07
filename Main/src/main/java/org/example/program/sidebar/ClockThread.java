package org.example.program.sidebar;

import java.util.Date;
import java.text.SimpleDateFormat;
import javafx.application.Platform;
import org.example.program.components.Clock;

public class ClockThread extends Thread {
    private Clock clock;
    public volatile static boolean appClosed = false;

    public ClockThread(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void run () {
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat date = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        while(!appClosed) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
            Platform.runLater(()-> {
                clock.getTime().setText(time.format(new Date()));
                clock.getDate().setText(date.format(new Date()));
                clock.getMonth().setText(month.format(new Date()).toUpperCase());
                clock.getYear().setText(year.format(new Date()));
            });
        }
    }
}
