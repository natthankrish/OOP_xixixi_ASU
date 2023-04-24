package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.components.NewTab;
import program.topbar.TopContainer;
import program.sidebar.SideContainer;
import program.sidebar.ClockThread;
import program.topbar.LogoThread;
import program.components.SearchBar;
import javafx.scene.layout.StackPane;
import java.util.Arrays;
import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
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

        // search bar
//        List<String> itemList = Arrays.asList("Apple", "Banana", "Cherry", "Elderberry");
//        SearchBar searchBar = new SearchBar("Search", itemList);
//        StackPane rut = new StackPane(searchBar);
//        stage.setScene(new Scene(rut, 300, 250));
//        stage.show();

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
        LogoThread.appClosed = true;
    }
}
