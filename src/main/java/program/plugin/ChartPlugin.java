package program.plugin;

public abstract class ChartPlugin {
    private boolean loaded = false;
    public abstract void showLineChart();
    public abstract void showBarChart();
    public abstract void showPieChart();

    public boolean hasBeenLoaded() {
        return loaded;
    }
}
