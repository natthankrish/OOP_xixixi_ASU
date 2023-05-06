package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.chart.Chart;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.containers.Manager;
import program.adapter.Adapter;
import program.adapter.JSONAdapter;
import program.adapter.OBJAdapter;
import program.adapter.XMLAdapter;
import program.page.*;
import program.plugin.ChartPlugin;
import program.plugin.Loader;
import program.sidebar.ClockThread;
import program.topbar.LogoThread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.io.*;


public class App extends Application {

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

        ArrayList<Class<?>> classes = loadPlugin("C:/Users/gitac/Desktop/GIts/OOP/TUBES 2 OOP/OOP_xixixi_ASU/test/PluginChart-1.0.0.jar");
        ChartPlugin chartPlugin1 = null;
        Object pluginClass = null;
        Method method = null;
        for (Class<?> clazz: classes) {
            ArrayList<String> interfaceName = getInterfaceName(clazz);
            for (String interfacez : interfaceName) {
                if (interfacez.equals("ChartPlugin")) {
                    pluginClass = clazz.getDeclaredConstructor().newInstance();
                    method = clazz.getMethod("showLineChart");
                    break;
                }
            }
        }

//        ChartPlugin chartPlugin = (ChartPlugin) pluginClass;
        Group basepluginpage = (Group) method.invoke(pluginClass);

        App.root.getChildren().add(basepluginpage);

//        SideContainer sideContainer = new SideContainer();
//        App.root.getChildren().add(sideContainer);

//        TopContainer topContainer = new TopContainer(sideContainer.getTabsContainer());
//        App.root.getChildren().add(topContainer);

        Scene scene = new Scene(App.root);
//        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
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

    public ArrayList<String> getInterfaceName(Class clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        ArrayList<String> interfaceName = new ArrayList<>();
        for (Class interfacez : interfaces) {
            int index = interfacez.getName().lastIndexOf(".");
            if (index >= 0) {
                String newName = interfacez.getName().substring(index + 1);
                interfaceName.add(newName);
            } else {
                interfaceName.add(interfacez.getName());
            }
        }
        return interfaceName;
    }
    public ArrayList<Class<?>> loadPlugin(String jarPath) throws Exception {
        Loader jarLoader = new Loader();
        return jarLoader.loadJarFile(jarPath);
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
        Manager manager = Manager.getInstance();
        String config = readConfig();

        if (config.equals("json")){
            this.adapter = new JSONAdapter();
        } else if (config.equals("xml")){
            this.adapter = new XMLAdapter();
        } else if (config.equals("obj")){
            this.adapter = new OBJAdapter();
        }
        this.adapter.readDataClient(manager.getClientContainer());
        this.adapter.readDataInventory(manager.getInventoryContainer());
        this.adapter.readDataTransaction(manager.getTransactionContainer());

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
        Manager manager = Manager.getInstance();
        if (adapter instanceof JSONAdapter){
            writeConfig("json");
        } else if (adapter instanceof XMLAdapter){
            writeConfig("xml");
        } else if (adapter instanceof OBJAdapter){
            writeConfig("obj");
        }
        adapter.writeDataClient(manager.getClientContainer());
        adapter.writeDataInventory(manager.getInventoryContainer());
        adapter.writeDataTransaction(manager.getTransactionContainer());
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

}
