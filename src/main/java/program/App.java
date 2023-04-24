package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.components.NewTab;
import program.plugin.PluginClassLoader;
import program.topbar.TopContainer;
import program.sidebar.SideContainer;
import program.sidebar.ClockThread;

import java.io.File;
import java.lang.reflect.Method;
public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        PluginClassLoader plugin = new PluginClassLoader(new File("./"));
        Class<?> pluginClass = plugin.findClass("Run");
        Object plugin2 = pluginClass.newInstance();

        // Call a method on the plugin instance
        Method method = pluginClass.getMethod("run");
        method.invoke(plugin2);

        // Setting Scene Buffer
        Group root = new Group();

        TopContainer topContainer = new TopContainer();
        root.getChildren().add(topContainer);

        SideContainer sideContainer = new SideContainer();
        NewTab tab1 = new NewTab("Inventory", 16, "#867070", "#D5B4B466", 700);
        NewTab tab2 = new NewTab("Member", 16, "#867070", "#D5B4B466", 700);
        sideContainer.getTabsContainer().getTabs().addAll(tab1, tab2);
        sideContainer.getTabsContainer().setVertical();
        root.getChildren().add(sideContainer);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);

        // Show Main Window
        Image applogo = new Image("file:assets/logo.png");
        stage.getIcons().add(applogo);
        stage.setTitle("BNMO");
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ClockThread.appClosed = true;
    }
}
