package program.components;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import program.page.*;
import program.App;

@Setter
@Getter
public class NewTab extends HBox {
    private static CloseAllButton closeAllButton;
    private static ObservableList<Node> listTabs;
    private static NewTab currentTab;
    private Button tabButton;
    private String textButton;
    private CloseButton closeButton;
    private BasePage page;

    public NewTab(String tabText, ObservableList<Node> listTabs, double width, CloseAllButton closeAllButton) {
        NewTab.listTabs = listTabs;
        NewTab.closeAllButton = closeAllButton;
        this.textButton = tabText;
        this.closeButton = new CloseButton();
        this.tabButton = new Button();

        this.initializePage(tabText);

        this.tabButton.setPrefWidth(width-45);
        this.tabButton.setPrefHeight(35);
        this.tabButton.setStyle("""
                    -fx-text-fill: #F5EBEB;
                    -fx-background-color: rgba(134, 112, 112, 0.6);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
        App.setPageBuffer(this.page);

        this.process(width, true);
        NewTab.currentTab = this;
        this.tabButton.setText(this.textButton);

        this.getChildren().addAll(this.tabButton, this.closeButton);

        this.tabButton.setOnMouseClicked(event -> {
            NewTab.currentTab = this;
            this.process(width, true);
        });

        this.setOnMouseEntered(event -> {
            this.process(width, false);
        });

        this.setOnMouseExited(event -> {
            if (NewTab.currentTab != null) {
                NewTab.currentTab.process(width, false);
            }
        });

        this.closeButton.setOnMouseClicked(event -> {
            int idx = NewTab.listTabs.indexOf(this);
            if (idx == NewTab.listTabs.size() - 1) {
                if (idx - 1 < 0) {
                    NewTab.currentTab = null;
                    App.setPageBuffer(new BasePage());
                } else {
                    NewTab.currentTab = (NewTab) NewTab.listTabs.get(idx - 1);
                    NewTab.currentTab.process(width, true);
                }
                NewTab.listTabs.remove(this);
            } else {
                NewTab.listTabs.remove(this);
                NewTab.currentTab = (NewTab) NewTab.listTabs.get(idx);
                NewTab.currentTab.process(width, true);
            }

        });

        NewTab.closeAllButton.setOnMouseClicked(event -> {
            NewTab.listTabs.clear();
            NewTab.currentTab = null;
            App.setPageBuffer(new BasePage());
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

    private void process(double width, boolean changePage) {
        for (Node item: NewTab.listTabs) {
            NewTab itemunit = (NewTab) item;
            if (this != item) {
                itemunit.getTabButton().setPrefWidth(width);
                if (itemunit.getChildren().indexOf(itemunit.getCloseButton()) == 1) {
                    itemunit.getChildren().remove(itemunit.getCloseButton());
                }
                itemunit.getTabButton().setStyle("""
                    -fx-text-fill: #867070;
                    -fx-background-color: rgba(213, 180, 180, 0.4);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
            } else {
                if (changePage) {
                    App.setPageBuffer(this.page);
                }
                if (this.getChildren().indexOf(itemunit.getCloseButton()) == -1) {
                    this.getChildren().add(this.closeButton);
                }
                this.tabButton.setPrefWidth(width-45);
                this.tabButton.setStyle("""
                    -fx-text-fill: #F5EBEB;
                    -fx-background-color: rgba(134, 112, 112, 0.6);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
            }
        }
    }


}
