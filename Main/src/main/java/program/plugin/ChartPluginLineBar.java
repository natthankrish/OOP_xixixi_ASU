package program.plugin;

import javafx.scene.Group;

import java.util.Map;

public interface ChartPluginLineBar {
    public Group showLineChart(String x, String y, String name, String seriesName, Map<String, Double> map);
    public Group showBarChart(String x, String y, String name, String seriesName, Map<String, Double> map);
}
