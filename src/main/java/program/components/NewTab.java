package program.components;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import lombok.Setter;
import program.page.*;
import program.App;

@Setter
public class NewTab extends Button {
    String textButton;
    ObservableList<Node> otherButton;
    CloseButton closeButton;
    BasePage page;

    public NewTab(String tabText, ObservableList<Node> othernode, double width) {
        this.textButton = tabText;
        this.otherButton = othernode;
        this.closeButton = new CloseButton();

        this.initializePage(tabText);

        this.setPrefWidth(width);
        this.setPrefHeight(35);
        this.setStyle("""
                    -fx-text-fill: #867070;
                    -fx-background-color: rgba(213, 180, 180, 0.4);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
        this.setText(this.textButton);

        this.setOnMouseClicked(event -> {
            for (Node item: this.otherButton) {
                if (this != item) {
                    item.setStyle("""
                    -fx-text-fill: #867070;
                    -fx-background-color: rgba(213, 180, 180, 0.4);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
                } else {
                    App.setPageBuffer(this.page);
                    this.closeButton.show();
                    this.setStyle("""
                    -fx-text-fill: #F5EBEB;
                    -fx-background-color: rgba(134, 112, 112, 0.6);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
                }
            }
        });
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
