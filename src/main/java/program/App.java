package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.topbar.TopContainer;
import program.sidebar.SideContainer;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Setting Scene Buffer
        Group root = new Group();

        TopContainer topContainer = new TopContainer();
        root.getChildren().add(topContainer.getComponent());

        SideContainer sideContainer = new SideContainer();
        root.getChildren().add(sideContainer.getComponent());

        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show Main Window
        Image applogo = new Image("file:assets/logo.png");
        stage.getIcons().add(applogo);
        stage.setTitle("BNMO");
        stage.setMaximized(true);
        stage.show();

//        Text helloText = new Text("Hello");
//        VBox root = new VBox(helloText);
//        root.setAlignment(Pos.CENTER);
//
//        stage.setScene(scene);
//        stage.show();
//        stage.setTitle("Hello!");
//        stage.show();
//        TestLombok lombok = new TestLombok(1, 1);
//        System.out.println(lombok.getId() + " " + lombok.getStock());
    }
}
