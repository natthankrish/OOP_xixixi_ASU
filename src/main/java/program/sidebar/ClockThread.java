package program.sidebar;

import java.util.Date;
import java.text.SimpleDateFormat;
import javafx.application.Platform;
import program.components.Clock;

public class ClockThread {
    private Thread timer;
    public volatile static boolean appClosed = false;

    // this is timer thread which will update out time view every second
    public ClockThread (Clock clock) {
        this.timer = new Thread(() -> {
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
        });
        this.timer.start();
    }
}
