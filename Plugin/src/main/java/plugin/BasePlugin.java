package plugin;
import javafx.scene.Group;
import javafx.scene.chart.*;
import javafx.stage.Screen;
import org.example.program.topbar.TopContainer;

import java.util.Map;

public class BasePlugin {
    public static Group page;
    private static boolean loaded = false;
    // show empty page
    public static void showEmptyPage() {
        Background background;
        background = new Background(Screen.getPrimary().getVisualBounds().getWidth() * 4 / 5, Screen.getPrimary().getVisualBounds().getHeight() * 14 / 15, "#FFFFFF");
        page = new Group();
        page.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 5);
        page.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 15);
        page.getChildren().add(background);
        loaded = true;
    }

    public static void addLineChart(String x, String y, String title, String seriesName, Map<String, Double> map) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(x);
        yAxis.setLabel(y);

        // Create the line chart
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(title);

        // Add data to the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String foodName = entry.getKey();
            Double value = entry.getValue();
            series.getData().add(new XYChart.Data<>(foodName, value));
        }
        lineChart.setPrefSize(800,300);
        lineChart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 0.5 / 5);
        lineChart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 15);
        lineChart.getData().add(series);
        page.getChildren().add(lineChart);
    }
    public static void addBarChart(String x, String y, String title, String seriesName, Map<String, Double> map) {
        // add bar chart to the page
        // Create the line chart
        CategoryAxis xAxis1 = new CategoryAxis();
        NumberAxis yAxis1 = new NumberAxis();
        xAxis1.setLabel(x);
        yAxis1.setLabel(y);
        BarChart<String, Number> barChart = new BarChart<>(xAxis1, yAxis1);
        barChart.setTitle(title);

        // Add data to the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String foodName = entry.getKey();
            Double value = entry.getValue();
            series.getData().add(new XYChart.Data<>(foodName, value));
        }
        barChart.getData().add(series);
        barChart.setPrefSize(800,300);
        barChart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 0.5 / 5);
        barChart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 15);
        page.getChildren().add(barChart);
        System.out.println("adding bar chart");
    }

    public static boolean hasBeenLoaded() {
        return loaded;
    }
}
