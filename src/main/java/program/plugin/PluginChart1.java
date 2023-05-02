package program.plugin;

public class PluginChart1 extends ChartPlugin{

    @Override
    public void showLineChart() {
        if (!this.hasBeenLoaded()) {
            BasePlugin.showEmptyPage();
        }
        BasePlugin.addLineChart();
    }

    @Override
    public void showBarChart() {
        if (!this.hasBeenLoaded()) {
            BasePlugin.showEmptyPage();
        }
        BasePlugin.addBarChart();
    }

    @Override
    public void showPieChart() {
        throw new UnsupportedOperationException("PluginChart1 does not support Pie chart.");
    }

}
