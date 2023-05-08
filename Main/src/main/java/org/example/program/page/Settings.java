package org.example.program.page;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.tools.Borders;
import org.example.program.containers.Manager;
import org.example.program.plugin.Loader;
import org.example.program.plugin.Plugin;
import org.example.program.topbar.TopContainer;
import org.json.XML;
import org.example.program.App;
import org.example.program.adapter.Adapter;
import org.example.program.adapter.JSONAdapter;
import org.example.program.adapter.OBJAdapter;
import org.example.program.adapter.XMLAdapter;
import org.example.program.components.BillCard;
import org.example.program.components.DropDown;
import org.example.program.components.NewLabel;
import javafx.stage.FileChooser;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Settings extends BasePage {
    private BotRight botRight;
    public Settings() {
        this.changeBackground("white");
        NewLabel title = new NewLabel("Settings", 60, "#867070", 700);
        title.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 20);

        TopRight topRight = new TopRight();
        topRight.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 17/40);
        topRight.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 40);

        TopLeft topLeft = new TopLeft();
        topLeft.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        topLeft.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 40);

        NewLabel pluginTitle = new NewLabel("Customize", 40, "#867070", 700);
        pluginTitle.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        pluginTitle.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 19/ 40);

        BotLeft botLeft = new BotLeft(this);
        botLeft.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        botLeft.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 11/ 20);

        this.botRight = new BotRight();
        this.botRight.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() * 17/40);
        this.botRight.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 11/ 20);

        this.getChildren().addAll(title, topLeft, topRight, pluginTitle, botLeft, botRight);
    }

    public void instantiatePluginSetting(String pluginName) {
        if (this.botRight.getContent().getChildren().stream().count() != 0) {
            this.botRight.getContent().getChildren().remove(0);
        }
        this.botRight.getContent().getChildren().add(new PluginSetting(pluginName));
    }

}


class TopRight extends GridPane {
    public TopRight() {
        this.setStyle("""
            -fx-background-color: #F5EBEB;
            -fx-background-radius: 14;
        """);

        this.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 20);
        this.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);

        NewLabel title = new NewLabel("Add New Plugin", 40, "#867070", 700);
        title.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 25);

        NewLabel pathLabel = new NewLabel("Path", 22, "#867070", 700);
        pathLabel.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 80);
        ChooseFileButton pathButton = new ChooseFileButton("Choose Plugin File");

//        NewLabel pluginLabel = new NewLabel("Plugin Name", 22, "#867070", 700);
//        pluginLabel.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 80);

        SettingsTextField pluginName = new SettingsTextField("Enter plugin name here");
        pluginName.setPrefWidth(pathButton.getPrefWidth());

        SettingButton addButton = new SettingButton("Add Plugin");
        addButton.setOnMouseClicked(event -> {
            String path = pathButton.getPath();
            if (path == null) {
                return;
            }

            for (Node node : App.getRoot().getChildren()) {
                if (node instanceof TopContainer) {
                    try {
                        loadPlugin(path, (TopContainer) node, Manager.getInstance());
                        break;
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                }
            }
        });

        HBox body = new HBox(10, pathLabel, pathButton);
//        HBox body2 = new HBox(10, pluginLabel, pluginName);

        VBox container = new VBox(10, title, body, addButton);
        container.setAlignment(Pos.CENTER);
        container.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);
        container.setSpacing(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 200);
        container.setPadding(new Insets(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 200));

        this.add(container, 0, 0);
    }
    public ArrayList<Class<?>> loadPlugin(String jarPath, TopContainer topContainer, Manager manager) throws Exception {
        Loader jarLoader = new Loader();
        ArrayList<Class<?>> classes = jarLoader.loadJarFile(jarPath);
        Object pluginClass = null;
        for (Class<?> clazz: classes) {
            ArrayList<String> interfaceName = getInterfaceName(clazz);
            for (String interfacez : interfaceName) {
                if (interfacez.equals("Plugin")) {
                    System.out.println("Equal Plugin Class  :  " + clazz.getName());
                    pluginClass = clazz.getDeclaredConstructor().newInstance();
                    break;
                }
            }
        }
        if (pluginClass == null) {
            System.out.println("Jar file not found");
        } else {
            Plugin pluginInstance = (Plugin) pluginClass;
            pluginInstance.setup(topContainer, manager);
        }
        return jarLoader.loadJarFile(jarPath);
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
}

class TopLeft extends BorderPane {
    public TopLeft() {
        this.setStyle("""
            -fx-background-color: #F5EBEB;
            -fx-background-radius: 14;
        """);

        this.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 20);
        this.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);

        NewLabel title = new NewLabel("Data Store", 40, "#867070", 700);
        title.setPadding(new Insets(10));
        title.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 25);

        NewLabel dataStoreLabel = new NewLabel("Data Store Type", 22, "#867070", 700);
        dataStoreLabel.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 80);

        DropDown dataStoreType = new DropDown(new String[]{"XML", "JSON", "OBJ"});
        dataStoreType.setPrefSize(254.11, 27.46);
        dataStoreType.setMaxSize(254.11, 27.46);
        dataStoreType.setPromptText("Select Datastore Type");

        SettingButton confirmButton = new SettingButton("Confirm Changes");
        confirmButton.setOnMouseClicked(event -> {
            String datatype = dataStoreType.getValue().toString();
            if (datatype == "XML") {
                App.setAdapter(new XMLAdapter());
            } else if (datatype == "OBJ") {
                App.setAdapter(new OBJAdapter());
            } else if (datatype == "JSON") {
                App.setAdapter(new JSONAdapter());
            }
            System.out.println(App.getAdapter().getClass().toString());
        });

        HBox setup = new HBox(10, dataStoreLabel, dataStoreType);

        VBox layout = new VBox(10, title, setup, confirmButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);
        layout.setSpacing(Screen.getPrimary().getVisualBounds().getHeight() * 12/ 200);
        layout.setPadding(new Insets(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 200));

        setCenter(layout);
    }
}

class BotLeft extends GridPane {
    private Settings master;
    public BotLeft(Settings master) {
        this.setStyle("""
            -fx-background-color: WHITE;
            -fx-background-radius: 14;
        """);
        this.master = master;

        this.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 20);
        this.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);

        List pluginCards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PluginCard card  = new PluginCard("Plugin " + i);
            card.getEditButton().setOnMouseClicked(event -> {
                master.instantiatePluginSetting(card.getPluginName());
            });
            pluginCards.add(card);
        }

        PluginContainer pluginContainer = new PluginContainer(pluginCards);

        this.getChildren().add(pluginContainer);
    }
}
class BotRight extends GridPane {
    @Getter
    @Setter
    private VBox content;
    public BotRight() {
        this.setStyle("""
            -fx-background-color: transparent;
            -fx-background-radius: 14;
        """);
        this.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 20);
        this.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);
        this.content = new VBox();
        this.getChildren().add(content);
    }

}

class PluginSetting extends VBox {
    public PluginSetting(String PluginName){
        this.setStyle("""
            -fx-background-color: #F5EBEB;
            -fx-background-radius: 14;
        """);

        this.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 20);
        this.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);

        NewLabel pluginName = new NewLabel(PluginName, 40, "#867070", 700);
        NewLabel pathLabel = new NewLabel("Path", 16, "#867070", 700);
        pathLabel.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 80);

        ChooseFileButton pathButton = new ChooseFileButton("Choose Plugin Path");

        pathButton.setPrefSize(254.11, 27.46);

        SettingButton confirm = new SettingButton("Confirm Changes");
        SettingButton remove = new SettingButton("Remove Plugin");

        HBox hb1 = new HBox(pathLabel, pathButton);
        HBox hbutton = new HBox(confirm, remove);
        hbutton.setAlignment(Pos.CENTER);
        hbutton.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);
        hbutton.setSpacing(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 80);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(pluginName, hb1, hbutton);
        this.setSpacing(Screen.getPrimary().getVisualBounds().getHeight() * 12 / 200);
        this.setPadding(new Insets(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 200));
    }


}

class ChooseFileButton extends Button {
    @Getter
    @Setter
    private String path;
    ChooseFileButton(String placeHolder) {
        this.setText(placeHolder);
        this.setStyle("""
                -fx-font-family: Inter;
                -fx-font-size: 12;
                -fx-font-weight: bold;
                -fx-background-color: #D5B4B4;
                -fx-background-radius: 9;
                -fx-border-radius: 9;
                -fx-border-color: BLACK;
                -fx-border-width: 1;
        """);
        this.setPrefSize(254.11, 27.46);
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        this.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                this.path = selectedFile.getPath();
                this.setText(selectedFile.getPath());
            }
        });
    }
}



class SettingButton extends Button {
    public SettingButton(String title) {
        this.setStyle("""
            -fx-text-fill: #F5EBEB;
            -fx-background-color: #867070;
            -fx-pref-height: 31.12;
            -fx-pref-width: 149.77;
            -fx-font-size: 13;
            -fx-font-weight: bold;
            -fx-background-radius: 9;
        """);
        this.setText(title);
        ScaleTransition scaleTransition = new ScaleTransition(new Duration(0.2), this);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(0.8);
        scaleTransition.setToY(0.8);
        scaleTransition.setAutoReverse(true);
        this.setOnMousePressed(event -> scaleTransition.playFromStart());
        this.setOnMouseReleased(event -> {
            scaleTransition.stop();
            this.setScaleX(1);
            this.setScaleY(1);
        });
    }
}


class SettingsTextField extends TextField {
    public SettingsTextField(String placeHolder) {
        this.setPromptText(placeHolder);
        this.setStyle("""
                -fx-font-family: Inter;
                -fx-font-size: 12;
                -fx-font-weight: bold;
                -fx-background-color: #D5B4B4;
                -fx-pref-height: 27.46;
                -fx-background-radius: 9;
                -fx-border-radius: 9;
                -fx-border-color: BLACK;
                -fx-border-width: 1;
                -fx-prompt-text-fill: black;
        """);
    }
}

class PluginCard extends GridPane {
    @Setter
    @Getter
    private String pluginName;
    @Getter
    private Button editButton;
    public PluginCard(String pluginName) {
        this.setStyle("""
            -fx-background-color: #F5EBEB;
            -fx-background-radius: 14;
        """);
        this.setPrefHeight(70);
        this.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);
        this.pluginName = pluginName;

        NewLabel title = new NewLabel(pluginName, 24, "#867070", 700);
        this.editButton = new SettingButton("Edit Plugin");

        this.add(title, 0, 0);
        this.add(editButton, 1, 0);

        this.setAlignment(Pos.CENTER_LEFT);
        this.setHgrow(title, Priority.ALWAYS);
        this.setHalignment(editButton, HPos.RIGHT);
        this.setPadding(new Insets(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 200));
    }
}
class PluginContainer extends BorderPane {
    private VBox contentPane;

    public PluginContainer(List<PluginCard> cards) {
        this.contentPane = new VBox(10);
        this.contentPane.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);
        this.contentPane.getChildren().addAll(cards);

        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 6/ 20);
        scrollPane.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 13/ 40);
        scrollPane.setStyle("-fx-background:white;-fx-background-color:transparent;");
        scrollPane.setFitToWidth(true);

        VBox container = new VBox(scrollPane);
        container.setAlignment(Pos.TOP_LEFT);
        this.setCenter(container);
    }

}

