package program.sidebar;


import javafx.concurrent.Task;
import program.components.NewLabel;
import program.components.NewTab;

import java.lang.reflect.Constructor;

public class TabThread extends NewTab {
    private Thread tabThread;
    private Class<?> pluginClass;
    private Object pluginInstance;
    private Task<Void> task;
    public TabThread(String tabText, int size, String color, String bgColor, int weight, Class<?> pluginClass) {
        super(tabText, size, color, bgColor, weight);
        setEmpty();
        this.pluginClass = pluginClass;
        addPluginInstance();

        this.task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                pluginClass.getMethod("run").invoke(pluginInstance);
                return null;
            }
        };
        setOnSelectionChanged(event -> {
            if (this.task != null && isSelected() && !this.task.isRunning()) {
                this.tabThread = new Thread(this.task);
                this.tabThread.start();
            }
        });

        setOnClosed(event -> {
            if (this.task != null && this.task.isRunning()) {
                this.task.cancel();
                this.tabThread.interrupt();
            }
        });
    }

    public void addPluginInstance() {
        try {
            Constructor<?> constructor = this.pluginClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            pluginInstance = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setEmpty() {
        NewLabel empty = new NewLabel("This page is empty", 16, "#867070", "#D5B4B466", 700);
        setContent(empty);
    }
}
