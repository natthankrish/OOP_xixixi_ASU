package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.components.NewTab;
import program.page.*;
import program.page.BillHistory;
import program.page.Settings;
import program.topbar.TopContainer;
import program.sidebar.SideContainer;
import program.sidebar.ClockThread;
import program.topbar.LogoThread;
import program.components.SearchBar;
import javafx.scene.layout.StackPane;
import java.util.Arrays;
import java.util.List;

public class App extends Application {

    private static Group root;
    private static BasePage page;
    @Override
    public void start(Stage stage) throws Exception {
        // Setting Scene Buffer
        App.root = new Group();

        App.page = new BasePage();
        root.getChildren().add(App.page);

        SideContainer sideContainer = new SideContainer();
        App.root.getChildren().add(sideContainer);

        TopContainer topContainer = new TopContainer(sideContainer.getTabsContainer());
        App.root.getChildren().add(topContainer);

        Scene scene = new Scene(App.root);
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

    public static void setPageBuffer(BasePage newPage) {
        App.root.getChildren().remove(App.page);
        App.page = newPage;
        App.root.getChildren().add(App.page);
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        ClockThread.appClosed = true;
        LogoThread.appClosed = true;
    }
}
