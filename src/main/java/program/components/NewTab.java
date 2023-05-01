package program.components;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;
import program.page.*;
import program.App;

@Setter
@Getter
public class NewTab extends Group {
    Button tabButton;
    String textButton;
    ObservableList<Node> otherButton;
    CloseButton closeButton;
    BasePage page;

    public NewTab(String tabText, ObservableList<Node> othernode, double width) {
        this.textButton = tabText;
        this.otherButton = othernode;
        this.closeButton = new CloseButton();
        this.tabButton = new Button();

        this.initializePage(tabText);

        this.tabButton.setPrefWidth(width);
        this.tabButton.setPrefHeight(35);
        this.tabButton.setStyle("""
                    -fx-text-fill: #867070;
                    -fx-background-color: rgba(213, 180, 180, 0.4);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
        this.tabButton.setText(this.textButton);

        this.tabButton.setOnMouseClicked(event -> {
            for (Node item: this.otherButton) {
                NewTab itemunit = (NewTab) item;
                if (this != item) {
                    itemunit.getTabButton().setStyle("""
                    -fx-text-fill: #867070;
                    -fx-background-color: rgba(213, 180, 180, 0.4);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
                } else {
                    App.setPageBuffer(this.page);
                    this.closeButton.show();
                    this.tabButton.setStyle("""
                    -fx-text-fill: #F5EBEB;
                    -fx-background-color: rgba(134, 112, 112, 0.6);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
                }
            }
        });

        this.getChildren().add(this.tabButton);
    }


    private void initializePage(String tabText) {
        if (tabText.equals("Transaction")) {
            this.page = new Transaction();
        } else if (tabText.equals("Register Member"))  {
            this.page = new RegisterMember();
        } else if (tabText.equals("Member Directory")) {
            this.page = new MemberDirectory();
        } else if (tabText.equals("Item Directory")) {
            this.page = new ItemDirectory();
        } else if (tabText.equals("Open Settings")) {
            this.textButton = "Settings";
            this.page = new Settings();
        } else if (tabText.equals("Sales Report")) {
            this.page = new SaleReport();
        } else if (tabText.equals("Bill History")) {
            this.page = new BillHistory();
        }
    }

    public void hideCloseButton() {
        this.closeButton.hide();
    }
}
