package plugin;

import javafx.scene.Group;
import org.example.program.containers.Manager;
import org.example.program.page.BasePage;
import org.example.program.plugin.Plugin;
import org.example.program.topbar.TopContainer;

import java.util.Map;
public class PluginChartLineBar implements Plugin {
    public PluginChartLineBar() {}

    public void setup(TopContainer topContainer, Manager manager) {
        this.showLineChart("Product Name", "Total Sales", "Food Income", "Food ID", manager);
        BasePage page = this.showBarChart("Product Name", "Total Sales", "Food Income", "Food ID", manager);
        topContainer.getTopbar().addMenuITem("Plugin Line Bar Chart", page);
    }

    public BasePage showLineChart(String x, String y, String name, String seriesName, Manager manager)  {
        if (!BasePlugin.hasBeenLoaded()) {
            BasePlugin.showEmptyPage();
        }
        BasePlugin.addLineChart(x, y, name, seriesName, manager);
        System.out.println("showing line chart");
        return BasePlugin.page;
    }

    public BasePage showBarChart(String x, String y, String name, String seriesName, Manager manager)  {
        if (!BasePlugin.hasBeenLoaded()) {
            BasePlugin.showEmptyPage();
        }
        BasePlugin.addBarChart(x, y, name, seriesName, manager);
        System.out.println("showing bar chart");
        return BasePlugin.page;
    }

    public void postSetup() {
        System.out.println("post setup");
    }
}
