package PluginPie;
import javafx.scene.Group;
import javafx.scene.chart.*;
import javafx.stage.Screen;
import javafx.util.Pair;
import org.example.program.topbar.TopContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.program.page.BasePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.program.containers.*;
import org.example.program.entities.Bill;
import org.example.program.entities.ReceiptInfo;
import org.example.program.entities.clients.Client;

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

    public static void addPieChartProduct(Manager manager) {
        // add the pie chart to the page
        Map<String, Double> map = new HashMap<>();
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Food Category Total Sales");
        TransactionContainer tc = manager.getTransactionContainer();
        InventoryContainer ic = manager.getInventoryContainer();
        for (Bill b : tc.getBuffer()) {
            for (ReceiptInfo r : b.getReceipt()) {
                String category = ic.getProductById(r.getProductID()).getCategory();
                Double value = r.getSubtotal();
                if (map.containsKey(category)) {
                    Double newValue = map.get(category) + value;
                    map.replace(category, map.get(category), newValue);
                } else {
                    map.put(category, value);
                }
            }
        }
//
        // Add data to the chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String foodName = entry.getKey();
            Double value = entry.getValue();
            pieChartData.add(new PieChart.Data(foodName, value));
        }
        pieChart.getData().addAll(pieChartData);
        pieChart.setPrefSize(800,500);
        pieChart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 0);
        pieChart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        page.getChildren().add(pieChart);
        System.out.println("adding pie chart");
    }

    public static void addPieChartClient(Manager manager) {
        // add the pie chart to the page

        List<Pair<String, Double>> purchaseList = new ArrayList<>();
        TransactionContainer tc = manager.getTransactionContainer();
        ClientContainer cc = manager.getClientContainer();
        for (Client c : cc.getBuffer()) {
            String clientName = c.getName();
            Double value = 0.0;
            for (Integer i : c.getTransactionHistory()) {
                Bill b = tc.getBillById(i);
                value += b.getTotalPrice();
            }
            purchaseList.add(new Pair<>(clientName, value));
        }

        // Get top 5
        List<Pair<String, Double>> top5 = new ArrayList<>();
        int iterate = 0;
        if (purchaseList.size() < 5){
            iterate = purchaseList.size();
        } else {
            iterate = 5;
        }
        for (int i = 0; i < )
        for (int i = 0; i < purchaseList.size(); i++){
            if (){}
        }



        PieChart pieChart = new PieChart();
        pieChart.setTitle("Top Client's Purchase");

        // Add data to the chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String foodName = entry.getKey();
            Double value = entry.getValue();
            pieChartData.add(new PieChart.Data(foodName, value));
        }
        pieChart.getData().addAll(pieChartData);
        pieChart.setPrefSize(800,500);
        pieChart.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 0);
        pieChart.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 2 / 15);
        page.getChildren().add(pieChart);
        System.out.println("adding pie chart");
    }

    public static boolean hasBeenLoaded() {
        return loaded;
    }
}
