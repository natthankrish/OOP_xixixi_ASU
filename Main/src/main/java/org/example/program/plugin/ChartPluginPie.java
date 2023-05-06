package org.example.program.plugin;

import javafx.scene.Group;

import java.util.Map;

public interface ChartPluginPie {
    public Group showPieChart(String text, Map<String, Double> map);
}
