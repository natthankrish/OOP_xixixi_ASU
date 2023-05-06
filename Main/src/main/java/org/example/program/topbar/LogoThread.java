package org.example.program.topbar;

import javafx.application.Platform;
import org.example.program.components.NewImage;

public class LogoThread extends Thread {
    private NewImage img;
    private int id;
    public volatile static boolean appClosed = false;

    public LogoThread(NewImage imgbuffer) {
        this.img = imgbuffer;
        this.id = 1;
    }

    public void nextImage() {
        this.id++;
        if (this.id == 13) {
            this.id = 1;
        }
        this.img.changeImage("assets/logo/" + this.id + ".png");
    }

    @Override
    public void run () {
        while(!appClosed) {
            try {
                Thread.sleep(83);
            } catch (InterruptedException ex) {}
            Platform.runLater(()-> {
                this.nextImage();
            });
        }
    }
}
