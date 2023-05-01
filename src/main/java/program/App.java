package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import program.adapter.Adapter;
import program.adapter.JSONAdapter;
import program.adapter.OBJAdapter;
import program.adapter.XMLAdapter;
import program.components.NewTab;
import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;
import program.entities.Product;
import program.page.*;
import program.page.BillHistory;
import program.page.Settings;
import program.topbar.TopContainer;
import program.sidebar.SideContainer;
import program.sidebar.ClockThread;
import program.topbar.LogoThread;
import program.components.SearchBar;
import javafx.scene.layout.StackPane;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.io.*;


public class App extends Application {

    private static ClientContainer cc;
    private static InventoryContainer ic;
    private static TransactionContainer tc;
    private static Adapter adapter;
    private static Group root;
    private static BasePage page;
    @Override
    public void start(Stage stage) throws Exception {
        // Setup DB
        setupDB();

        // Setting Scene Buffer
        App.root = new Group();

        App.page = new HomePage();
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
        updateDB();
        ClockThread.appClosed = true;
        LogoThread.appClosed = true;
    }

    public void setupDB() {
        String config = readConfig();
        if (config.equals("json")){
            this.adapter = new JSONAdapter();
        } else if (config.equals("xml")){
            this.adapter = new XMLAdapter();
        } else if (config.equals("obj")){
            this.adapter = new OBJAdapter();
        }
        cc = new ClientContainer();
        ic = new InventoryContainer();
        tc = new TransactionContainer();
        this.adapter.readDataClient(cc);
        this.adapter.readDataInventory(ic);
        this.adapter.readDataTransaction(tc);

//        for (Product p:
//             ic.getBuffer()) {
//            p.display();
//        }
    }

    public String readConfig() {
        try {
            String configurePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\configure.txt";
            File file = new File(configurePath);

            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    public void updateDB(){
        if (adapter instanceof JSONAdapter){
            writeConfig("json");
        } else if (adapter instanceof XMLAdapter){
            writeConfig("xml");
        } else if (adapter instanceof OBJAdapter){
            writeConfig("obj");
        }
        adapter.writeDataClient(cc);
        adapter.writeDataInventory(ic);
        adapter.writeDataTransaction(tc);
    }

    public void writeConfig(String config){
        try{
            String configurePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\configure.txt";

            FileWriter fw = new FileWriter(configurePath);
            fw.write(config);
            fw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public ClientContainer getClientContainer(){
        return cc;
    }
    public InventoryContainer getInventoryContainer(){
        return ic;
    }
    public TransactionContainer getTransactionContainer(){
        return tc;
    }
}
