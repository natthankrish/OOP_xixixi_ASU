package com.example.test;

import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 1200, 720);
        // stage.setScene(scene);
        stage.setTitle("Hello!");
        stage.show();
        TestLombok lombok = new TestLombok(1, 1);
        System.out.println(lombok.getId() + " " + lombok.getStock());
    }

    public static void main(String[] args) {
        launch();
    }
}