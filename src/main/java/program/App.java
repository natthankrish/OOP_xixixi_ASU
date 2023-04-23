package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.topbar.TopContainer;
import program.sidebar.SideContainer;
import program.sidebar.ClockThread;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Setting Scene Buffer
        Group root = new Group();

        TopContainer topContainer = new TopContainer();
        root.getChildren().add(topContainer);

        SideContainer sideContainer = new SideContainer();
        root.getChildren().add(sideContainer);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show Main Window
        Image applogo = new Image("file:assets/logo.png");
        stage.getIcons().add(applogo);
        stage.setTitle("BNMO");
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ClockThread.appClosed = true;
    }
}
