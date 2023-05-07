package org.example.program.page;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.tools.Borders;
import org.json.XML;
import program.App;
import program.adapter.Adapter;
import program.adapter.JSONAdapter;
import program.adapter.OBJAdapter;
import program.adapter.XMLAdapter;
import program.components.BillCard;
import program.components.DropDown;
import program.components.NewLabel;
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
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 25);

        TopRight topRight = new TopRight();
        TopLeft topLeft = new TopLeft();
        HBox top = new HBox(10, topLeft, topRight);

        NewLabel pluginTitle = new NewLabel("Customize", 32, "#867070", 700);

        BotLeft botLeft = new BotLeft(this);
        this.botRight = new BotRight();
        HBox bot = new HBox(10, botLeft, this.botRight);


        VBox layout = new VBox(10, title, top, pluginTitle, bot);
        this.getChildren().add(layout);
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
            -fx-pref-width: 461;
            -fx-pref-height: 249;
        """);

        NewLabel title = new NewLabel("Add New Plugin", 32, "#867070", 700);
        title.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 25);

        NewLabel pathLabel = new NewLabel("Path", 16, "#867070", 700);
        ChooseFileButton pathButton = new ChooseFileButton("Choose Plugin File");



        NewLabel pluginLabel = new NewLabel("Plugin Name", 16, "#867070", 700);
        SettingsTextField pluginName = new SettingsTextField("Enter plugin name here");

        SettingButton addButton = new SettingButton("Add Plugin");

        VBox label = new VBox(18, pathLabel, pluginLabel);
        VBox setup = new VBox(10, pathButton, pluginName);
        HBox body = new HBox(10, label, setup);
        VBox container = new VBox(10, title, body, addButton);
        this.add(container, 0, 0);
    }
}

class TopLeft extends BorderPane {
    public TopLeft() {
        this.setStyle("""
            -fx-background-color: #F5EBEB;
            -fx-background-radius: 14;
            -fx-pref-width: 461;
            -fx-pref-height: 249;
        """);

        NewLabel title = new NewLabel("Data Store", 32, "#867070", 700);
        title.setPadding(new Insets(10));
        title.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 20);
        title.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 1 / 25);


        NewLabel dataStoreLabel = new NewLabel("Data Store Type", 16, "#867070", 700);

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

        setCenter(layout);
    }
}

class BotLeft extends GridPane {
    private Settings master;
    public BotLeft(Settings master) {
        this.setStyle("""
            -fx-background-color: WHITE;
            -fx-background-radius: 14;
            -fx-pref-width: 461;
            -fx-pref-height: 249;
        """);
        this.master = master;

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
            -fx-pref-width: 461;
            -fx-pref-height: 249;
        """);
        this.content = new VBox();
        this.getChildren().add(content);
    }
}

class PluginSetting extends GridPane {
    public PluginSetting(String PluginName){
        this.setStyle("""
            -fx-background-color: #F5EBEB;
            -fx-background-radius: 14;
            -fx-pref-width: 461;
            -fx-pref-height: 249;
        """);
        NewLabel pluginName = new NewLabel("Path", 32, "#867070", 700);
        NewLabel pathLabel = new NewLabel("Path", 16, "#867070", 700);
        ChooseFileButton pathButton = new ChooseFileButton("Choose Plugin Path");
        pathButton.setPrefSize(254.11, 27.46);

        SettingButton confirm = new SettingButton("Confirm Changes");
        SettingButton remove = new SettingButton("Remove Button");
        this.add(pluginName, 0, 0);
        this.add(pathLabel, 1, 1);
        this.add(pathButton, 2, 1);
        this.add(confirm, 2,2);
        this.add(remove, 1,2);
    }

}

class ChooseFileButton extends Button {
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
                -fx-pref-width: 254.11;
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
            -fx-pref-width: 456;
            -fx-pref-height: 80;
        """);
        this.pluginName = pluginName;
        NewLabel title = new NewLabel(pluginName, 24, "#867070", 700);
        this.editButton = new SettingButton("Edit Plugin");

        this.add(title, 0, 0);
        this.add(editButton, 1, 0);

        this.setAlignment(Pos.CENTER_LEFT);
        this.setHgrow(title, Priority.ALWAYS);
        this.setHalignment(editButton, HPos.RIGHT);
    }
}
class PluginContainer extends BorderPane {
    private VBox contentPane;

    public PluginContainer(List<PluginCard> cards) {
        this.setStyle("""
            -fx-pref-width: 460;
            -fx-pref-height: 258;
        """);
        this.contentPane = new VBox(10);

        this.contentPane.getChildren().addAll(cards);

        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.setPrefHeight(460);
        scrollPane.setFitToWidth(true);

        VBox container = new VBox(scrollPane);
        container.setAlignment(Pos.TOP_LEFT);
        this.setCenter(container);
    }

}

