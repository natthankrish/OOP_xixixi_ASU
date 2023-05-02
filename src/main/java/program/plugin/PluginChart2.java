package program.plugin;

public class PluginChart2 implements ChartPlugin {
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
        // showing pie chart
    }
}
