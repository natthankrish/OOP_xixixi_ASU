package plugin;
import javafx.scene.chart.*;
import javafx.stage.Screen;
import org.example.program.entities.commodities.Commodity;
import org.example.program.containers.InventoryContainer;
import org.example.program.containers.Manager;
import org.example.program.containers.TransactionContainer;
import org.example.program.entities.bills.Bill;
import org.example.program.entities.bills.ReceiptInfo;
import org.example.program.page.BasePage;

import java.util.HashMap;
import java.util.Map;

public class BasePlugin {
    public static BasePage page;
    private static boolean loaded = false;
    // show empty page
    public static void showEmptyPage() {
        Background background;
        background = new Background(Screen.getPrimary().getVisualBounds().getWidth() * 4 / 5, Screen.getPrimary().getVisualBounds().getHeight() * 14 / 15, "#FFFFFF");
        page = new BasePage();
        page.getChildren().add(background);
        loaded = true;
    }

    public static void addLineChart(String x, String y, String title, String seriesName, Manager manager) {
        Map<String, Double> map = new HashMap<>();
        TransactionContainer tc = manager.getTransactionContainer();
        InventoryContainer ic = manager.getInventoryContainer();
        for (Bill b : tc.getBuffer()) {
            for (ReceiptInfo r : b.getReceipt()) {
                Commodity food = ic.getProductById(r.getProductID());
                String foodName = food.getName();
                Double value = r.getSubtotal();
                if (map.containsKey(foodName)) {
                    Double newValue = map.get(foodName) + value;
                    map.replace(foodName, map.get(foodName), newValue);
                } else {
                    map.put(foodName, value);
                }
            }
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String foodName = entry.getKey();
            Double value = entry.getValue();
            series.getData().add(new XYChart.Data<>(foodName, value));
        }

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(x);
        yAxis.setLabel(y);

        // Create the line chart
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(title);

        // Add data to the chart

        lineChart.setPrefSize(800,300);
        lineChart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 0.5 / 5);
        lineChart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 15);
        lineChart.getData().add(series);
        page.getChildren().add(lineChart);
    }
    public static void addBarChart(String x, String y, String title, String seriesName, Manager manager) {
        // add bar chart to the page
        // Create the line chart
        CategoryAxis xAxis1 = new CategoryAxis();
        NumberAxis yAxis1 = new NumberAxis();
        xAxis1.setLabel(x);
        yAxis1.setLabel(y);
        BarChart<String, Number> barChart = new BarChart<>(xAxis1, yAxis1);
        barChart.setTitle(title);

        Map<String, Double> map = new HashMap<>();
        TransactionContainer tc = manager.getTransactionContainer();
        InventoryContainer ic = manager.getInventoryContainer();
        for (Bill b : tc.getBuffer()) {
            for (ReceiptInfo r : b.getReceipt()) {
                String foodName = ic.getProductById(r.getProductID()).getName();
                Double value = r.getSubtotal();
                if (map.containsKey(foodName)) {
                    Double newValue = map.get(foodName) + value;
                    map.replace(foodName, map.get(foodName), newValue);
                } else {
                    map.put(foodName, value);
                }
            }
        }

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
