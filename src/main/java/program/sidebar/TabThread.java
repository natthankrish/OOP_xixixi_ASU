package program.sidebar;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.VBox;
import program.components.NewLabel;
import program.components.NewTab;

public class TabThread extends NewTab {

    private Thread tabThread;
    private final Task<Void> task;
    public TabThread(String tabText, int size, String color, String bgColor, int weight) {
        super(tabText, size, color, bgColor, weight);
        NewLabel progressLabel = new NewLabel("Hello", 16, "#867070", "#D5B4B466", 700);
        VBox vbox = new VBox(progressLabel);
        setContent(vbox);
        this.task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // Do some time-consuming work here
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000); // turns off the thread every 1 seconds
                    final String message = String.format("Test %d", i);
                    Platform.runLater(() -> progressLabel.setText(message));
                }
                return null;
            }
        };

        progressLabel.textProperty().bind(task.messageProperty());

        setOnSelectionChanged(event -> {
            if (isSelected() && !this.task.isRunning()) {
                this.tabThread = new Thread(this.task);
                this.tabThread.start();
            }
        });
    }
}
