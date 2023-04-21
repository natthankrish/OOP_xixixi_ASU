package program;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Text helloText = new Text("Hello");
        VBox root = new VBox(helloText);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Hello!");
        stage.show();
        TestLombok lombok = new TestLombok(1, 1);
        System.out.println(lombok.getId() + " " + lombok.getStock());
    }

    public static void main(String[] args) {
        launch();
    }
}