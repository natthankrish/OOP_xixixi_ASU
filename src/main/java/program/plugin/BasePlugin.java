package program.plugin;


import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Screen;
import program.components.Background;

public class BasePlugin {
    public static Group page;
    private static boolean loaded = false;
    // show empty page
    public static void showEmptyPage() {
        Background background;
        background = new Background(Screen.getPrimary().getVisualBounds().getWidth() * 4 / 5, Screen.getPrimary().getVisualBounds().getHeight() * 14 / 15, "#FFFFFF");
        page = null;
        page.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 5);
        page.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 15);
        page.getChildren().add(background);
        loaded = true;
    }

    public static void addLineChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        // Create the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("My Chart");

        // Add data to the chart
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("My Data");
        series.getData().add(new XYChart.Data<>(1, 2));
        series.getData().add(new XYChart.Data<>(2, 3));
        series.getData().add(new XYChart.Data<>(3, 1));
        lineChart.getData().add(series);
        page.getChildren().add(lineChart);
    }
    public static void addBarChart() {
        // add bar chart to the page
        System.out.println("adding bar chart");
    }
    public static void addPieChart() {
        // add the pie chart to the page
        System.out.println("adding pie chart");
    }

    public static boolean hasBeenLoaded() {
        return loaded;
    }
}
