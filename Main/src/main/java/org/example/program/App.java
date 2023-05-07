package org.example.program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.program.adapter.Adapter;
import org.example.program.adapter.JSONAdapter;
import org.example.program.adapter.OBJAdapter;
import org.example.program.adapter.XMLAdapter;
import org.example.program.containers.Manager;
import org.example.program.page.HomePage;
import org.example.program.plugin.Loader;
import org.example.program.sidebar.ClockThread;
import org.example.program.sidebar.SideContainer;
import org.example.program.topbar.LogoThread;
import org.example.program.topbar.TopContainer;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class App extends Application {
    @Setter
    @Getter

    private static Adapter adapter;
    
    private static Group root;
    private static Group page;
    @Override
    public void start(Stage stage) throws Exception {

        // Setup DB
        setupDB();

        // Setting Scene Buffer
        App.root = new Group();

        App.page = new HomePage();
        root.getChildren().add(App.page);

        String cwd = System.getProperty("user.dir");
        ArrayList<Class<?>> classes = loadPlugin(cwd + "/Main/test/PluginChart-1.0.0.jar");
//        ChartPlugin chartPlugin1 = null;
//        ChartPluginLineBar chartLine = null;
        Map<String, Double> map = new HashMap<>();
        map.put("Caramel Latte", 50000.0 * 3);
        map.put("Vanilla Late", 55000.0 * 3);
        map.put("Pisang Goreng", 2000.0);
        map.put("Pisang Rebus", 2000.0);
        map.put("Pisang Bakar", 2000.0);
        map.put("Pisang Ijo", 2000.0);
        map.put("Pisang Kukus", 2000.0);
        map.put("Pisang Cincin", 2000.0);
        map.put("Pisang Kolek", 2000.0);

        Object pluginClass = null;
        Method method = null;
        Method method2 = null;
        for (Class<?> clazz: classes) {
            ArrayList<String> interfaceName = getInterfaceName(clazz);
            for (String interfacez : interfaceName) {
                if (interfacez.equals("ChartPluginLineBar")) {
                    System.out.println(clazz.getName());
                    pluginClass = clazz.getDeclaredConstructor().newInstance();
                    method = clazz.getMethod("showLineChart", String.class, String.class, String.class, String.class, Map.class);
                    method2 = clazz.getMethod("showBarChart", String.class, String.class, String.class, String.class, Map.class);
                    break;
                }
            }
        }


        method.invoke(pluginClass, "Product Name", "Total Sales", "Food Income", "Food ID", map);
        Group basepluginpage = (Group) method2.invoke(pluginClass, "Product Name", "Total Sales", "Food Income", "Food ID", map);

//        ChartPlugin chartPlugin = (ChartPlugin) pluginClass;

//        App.root.getChildren().add(basepluginpage);

        SideContainer sideContainer = new SideContainer();
        App.root.getChildren().add(sideContainer);

        TopContainer topContainer = new TopContainer(sideContainer.getTabsContainer());
        topContainer.getTopbar().addMenuITem("Plugin 1", basepluginpage);
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

    public static void setPageBuffer(Group newPage) {
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
            String configurePath = new java.io.File("").getAbsolutePath() + "\\Main\\src\\main\\datastore\\configure.txt";
            System.out.println(configurePath);
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
            String configurePath = new java.io.File("").getAbsolutePath() + "\\Main\\src\\main\\datastore\\configure.txt";

            FileWriter fw = new FileWriter(configurePath);
            fw.write(config);
            fw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
