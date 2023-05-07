package plugin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.program.plugin.Plugin;
import org.example.program.topbar.TopContainer;

import java.util.HashMap;
import java.util.Map;

import static plugin.BasePlugin.page;


public class Main extends Application{

    public void start(Stage primaryStage) throws Exception {
        // Create the x and y axes
//        Plugin chartLine = new PluginChartLineBar();
//        Map<String, Double> map = new HashMap<>();
//        map.put("Caramel Latte", 50000.0 * 3);
//        map.put("Vanilla Late", 55000.0 * 3);
//        map.put("Pisang Goreng", 2000.0);
//        map.put("Pisang Rebus", 2000.0);
//        map.put("Pisang Bakar", 2000.0);
//        map.put("Pisang Ijo", 2000.0);
//        map.put("Pisang Kukus", 2000.0);
//        map.put("Pisang Cincin", 2000.0);
//        map.put("Pisang Kolek", 2000.0);
//
//        TopContainer topContainer = null;
//        chartLine.setup(topContainer, map);
//        // Add the chart to a StackPane
//
//        // Create the scene
//        Scene scene = new Scene(page, 400, 400);
//
//        // Set the stage
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}