package program.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class SearchBar extends VBox {
    private TextField textField;
    private ListView<String> listView;
    private NewLabel label;
    private ObservableList<String> data;
    private List<String> itemList;

    public SearchBar(String labelText, List<String> itemList) {
        super();
        this.itemList = itemList;
        data = FXCollections.observableArrayList(itemList);
        label = new NewLabel(labelText, 16, "#000000", 400);
        textField = new TextField();
        textField.setPromptText("Search...");
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<String> filteredList = new ArrayList<String>();
                for (String item : itemList) {
                    if (item.toLowerCase().startsWith(newValue.toLowerCase())) {
                        filteredList.add(item);
                    }
                }
                data = FXCollections.observableArrayList(filteredList);
                listView.setItems(data);
            }
        });
        listView = new ListView<String>();
        listView.setItems(data);
        listView.setMaxHeight(100);
        setSpacing(5);
        getChildren().addAll(label, textField, listView);
        setStyle();
    }

    public void setStyle() {
        String style = "-fx-background-color: #F5EBEB; "
                + "-fx-border-color: #000000; "
                + "-fx-border-radius: 20; "
                + "-fx-padding: 5 10; "
                + "-fx-font-family: Inter; "
                + "-fx-font-size: 16px;"
                + "-fx-pref-height: 30px;"
                + "-fx-pref-width: 340px ";
        setStyle(style);
        label.setStyle();
    }
}
