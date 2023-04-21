package program;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Hello!");
        stage.show();
        TestLombok lombok = new TestLombok(1, 1);
        System.out.println(lombok.getId() + " " + lombok.getStock());
    }

    public static void main(String[] args) {
        launch();
    }
}