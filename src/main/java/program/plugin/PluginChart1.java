package program.plugin;

public class PluginChart1 implements ChartPlugin{

    @Override
    public void showLineChart() {

    }

    @Override
    public void showBarChart() {

    }

    @Override
    public void showPieChart() {
        throw new UnsupportedOperationException("PluginChart1 does not support Pie chart.");
    }
}
