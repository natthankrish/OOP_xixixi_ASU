package org.example.program.components;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class SearchBar extends VBox {
    public TextField textField;
    private ListView<String> listView;
    private NewLabel label;
    private ObservableList<String> data;
    private List<String> itemList;

    public SearchBar(List<String> itemList) {
        super();
        this.itemList = itemList;
        data = FXCollections.observableArrayList(itemList);
        textField = new TextField();
        textField.setPromptText("Search...");
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<String> filteredList = new ArrayList<String>();
                for (String item : itemList) {
                    if (item.toLowerCase().contains(newValue.toLowerCase())) {
                        filteredList.add(item);
                    }
                }
                data = FXCollections.observableArrayList(filteredList);
                listView.setVisible(true);
                listView.setItems(data);
            }
        });
        listView = new ListView<String>();
        listView.setItems(data);
        listView.setMaxHeight(100);
        listView.setVisible(false);
        setSpacing(5);
        getChildren().addAll(textField, listView);
        setStyle();
    }

    public void setStyle() {
        String style = "-fx-background-color: transparent; "
                + "-fx-border-color: transparent; "
                + "-fx-border-radius: 20; "
                + "-fx-padding: 0 0; "
                + "-fx-font-family: Inter; "
                + "-fx-font-size: 12px;"
//                + "-fx-pref-height: auto;"
                + "-fx-font-weight:bold ;"
                + "-fx-pref-width: 235px ";

        String textBox = "-fx-background-color: #F5EBEB;"
                + "-fx-background-radius: 10px; "
                + "-fx-text-fill: #867070;"
                + "-fx-border-color: #867070; "
                + "-fx-border-radius: 10px; "
                + "-fx-pref-height: 20px;"
                ;

        String listViewStyle = "-fx-background-color: #F5EBEB; "
                + "-fx-background-radius: 10px; "
                ;
        listView.setCellFactory(list -> new ListCell<String>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: #F5EBEB;"
                            + "-fx-background-radius: 10px; "
                    );
                } else {
                    setText(item);
                    setStyle("-fx-background-color: #F5EBEB; -fx-text-fill: #867070; -fx-background-radius: 10px;");
                    setOnMouseEntered(event -> {
                        setStyle("-fx-background-color: #E8DADA; -fx-text-fill: #867070; -fx-background-radius: 10px;");
                    });
                    setOnMouseExited(event -> {
                        setStyle("-fx-background-color: #F5EBEB; -fx-text-fill: #867070; -fx-background-radius: 10px;");
                    });
                }
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textField.setText(newValue);
                listView.setVisible(false);
            }
        });


        listView.setStyle(listViewStyle);
        textField.setStyle(textBox);
        setStyle(style);
    }

    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
