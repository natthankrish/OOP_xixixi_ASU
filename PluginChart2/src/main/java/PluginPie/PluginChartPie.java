package PluginPie;
import org.example.program.plugin.Plugin;
import org.example.program.topbar.TopContainer;
import javafx.scene.Group;
import org.example.program.containers.Manager;
import org.example.program.page.BasePage;


import javax.net.ssl.ManagerFactoryParameters;
import java.util.Map;

public class PluginChartPie implements Plugin {
    public PluginChartPie() {}

    public void setup(TopContainer topContainer, Manager manager) {
        BasePage page = this.showPieChart(manager);
        topContainer.getTopbar().addMenuITem("Plugin Pie Chart", page);
    }
    public BasePage showPieChart(Manager manager) {
        if (!BasePlugin.hasBeenLoaded()) {
            BasePlugin.showEmptyPage();
        }
        BasePlugin.addPieChartProduct(manager);
        BasePlugin.addPieChartClient(manager);
        System.out.println("showing line chart");
        return BasePlugin.page;
    }

    public void postSetup() {
        System.out.println("done setup");
    }
}
