package program.plugin;

public class PluginChart2 extends ChartPlugin {
    @Override
    public void showLineChart() {
        throw new UnsupportedOperationException("PluginChart2 does not support Pie chart.");
    }

    @Override
    public void showBarChart() {
        throw new UnsupportedOperationException("PluginChart2 does not support Pie chart.");
    }

    @Override
    public void showPieChart() {
        if (!this.hasBeenLoaded()) {
            BasePlugin.showEmptyPage();
        }
        // showing pie chart
        BasePlugin.addPieChart();
    }
}
