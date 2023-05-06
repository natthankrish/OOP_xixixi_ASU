package plugin;

import javafx.scene.Group;
import program.plugin.ChartPluginLineBar;

import java.util.Map;
public class PluginChartLineBar implements ChartPluginLineBar {
    public PluginChartLineBar() {}

    public Group showLineChart(String x, String y, String name, String seriesName, Map<String, Double> map)  {
        if (!BasePlugin.hasBeenLoaded()) {
            BasePlugin.showEmptyPage();
        }
        BasePlugin.addLineChart(x, y, name, seriesName, map);
        System.out.println("showing line chart");
        return BasePlugin.page;
    }

    public Group showBarChart(String x, String y, String name, String seriesName, Map<String, Double> map)  {
        if (!BasePlugin.hasBeenLoaded()) {
            BasePlugin.showEmptyPage();
        }
        BasePlugin.addBarChart(x, y, name, seriesName, map);
        System.out.println("showing bar chart");
        return BasePlugin.page;
    }
}
