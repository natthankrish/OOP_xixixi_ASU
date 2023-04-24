package program.topbar;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import program.components.NewImage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogoThread {
    private Thread thread;
    public volatile static boolean appClosed = false;

    // this is timer thread which will update out time view every second
    public LogoThread (NewImage img) {
        this.thread = new Thread(() -> {
            while(!appClosed) {
                try {
                    Thread.sleep(83);
                } catch (InterruptedException ex) {}
                Platform.runLater(()-> {
                    img.nextImage();
                });
            }
        });
        this.thread.start();
    }
}
