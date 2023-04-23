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
        NewLabel labelh1 = new NewLabel("Transaction48px", "h1", "brown");
        NewLabel labelh2 = new NewLabel("Cart32px", "h2", "brown");
        NewLabel labelh3 = new NewLabel("Item Name24px", "h3", "brown");
        NewLabel labelh4 = new NewLabel("Transaction Tab19px", "h4", "brown");
        NewLabel labeltext1 = new NewLabel("Quantity15px", "text1", "brown");
        NewLabel labeltext2 = new NewLabel("This is field12px", "text2", "brown");
        VBox root = new VBox();
        root.getChildren().addAll(helloText, labelh1, labelh2, labelh3, labelh4, labeltext1, labeltext2);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Hello!");
        stage.show();
        TestLombok lombok = new TestLombok(0, 0);
        System.out.println(lombok.getId() + " " + lombok.getStock());
    }

    public static void main(String[] args) {
        launch(args);
    }
}