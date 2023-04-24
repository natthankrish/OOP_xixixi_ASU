package program.components;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
class DropDown extends ComboBox {
    public DropDown (String[] contents) {
        super(FXCollections.observableArrayList(contents));
        this.setStyle("""
                .combo-box {
                -fx-font-family: Inter;
                -fx-font-size: 12;
                -fx-font-weight: bold;
                -fx-background-color: #D5B4B4;
                -fx-pref-height: 30;
                -fx-pref-width: 340;
                -fx-background-radius: 9;
                -fx-border-radius: 9;
                -fx-border-color: BLACK;
                -fx-border-width: 1;
                }  
        
        """);
        this.setPromptText("This Is Field");
        this.setCellFactory(listView -> new ListCell<String>() {
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setBackground(Background.EMPTY);
                } else {
                    int index = getIndex();
                    if (index % 2 == 0) {
                        setStyle("""
                            -fx-background-color: #F5EBEB;
                            -fx-text-fill: #867070;
                        """);
                    } else {
                        setStyle("""
                            -fx-background-color: #D5B4B4;
                            -fx-text-fill: #F5EBEB;
                        """);
                    }
                    setText(item);
                }
            }
        });
    }
}
