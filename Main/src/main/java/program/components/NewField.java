package program.components;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class NewField extends Button {
    private Label label;
    private ComboBox<String> comboBox;
    private VBox itemList;

    public NewField(String labelText, String[] items) {
        this.label = new Label(labelText);
        this.comboBox = new ComboBox<String>(FXCollections.observableArrayList(items));
        this.itemList = new VBox();
        this.itemList.setAlignment(Pos.CENTER_LEFT);
        this.itemList.setSpacing(10);
        this.itemList.setPadding(new Insets(10));

        // create some items to add to the list
        for (int i = 1; i <= 20; i++) {
            Label item = new Label("Item " + i);
            itemList.getChildren().add(item);
        }

        // create the button that will display the dropdown
        Button showDropdownBtn = new Button("▼");
        showDropdownBtn.setOnAction(e -> {
            // create a scroll pane to hold the list of items
            ScrollPane scrollPane = new ScrollPane(itemList);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefViewportWidth(300);
            scrollPane.setPrefViewportHeight(200);

            // create a new stage to display the scroll pane
            Stage dropdownStage = new Stage();
            dropdownStage.setScene(new Scene(scrollPane));
            dropdownStage.show();
        });

        // add components to the layout
        this.getChildren().addAll(label, comboBox, showDropdownBtn);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(10));
    }

    public void setSelectedItem(String item) {
        this.comboBox.setValue(item);
    }

    public String getSelectedItem() {
        return this.comboBox.getValue();
    }
    // example on how to call
    // NewButton button = new NewButton("Click me!", 100, 50);
    // button.setStyle();
}

