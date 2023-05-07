package org.example.program.topbar;
import javafx.scene.Group;
import javafx.scene.control.*;
import org.example.program.sidebar.ScrollTabPane;

import java.util.ArrayList;
import java.util.List;

public class TopbarFunctional extends MenuBar {

    List<Menu> menuList;
    List<List<MenuItem>> itemMenuList;
    ScrollTabPane tabBuffer;

    public TopbarFunctional(ScrollTabPane tab) {
        this.tabBuffer = tab;
        this.menuList = new ArrayList<>();
        this.itemMenuList = new ArrayList<>();

        // Menu List
        this.menuList.add(new Menu("Go To Page"));
        this.menuList.add(new Menu("Settings"));
        this.menuList.add(new Menu("Report"));
        for (int i = 0; i < 3; i++) {
            this.itemMenuList.add(new ArrayList<>());
        }

        // Item Menu List
        this.itemMenuList.get(0).add(new MenuItem("Transaction"));
        this.itemMenuList.get(0).add(new MenuItem("Register Member"));
        this.itemMenuList.get(0).add(new MenuItem("Member Directory"));
        this.itemMenuList.get(0).add(new MenuItem("Item Directory"));

        this.itemMenuList.get(1).add(new MenuItem("Open Settings"));

        this.itemMenuList.get(2).add(new MenuItem("Sales Report"));
        this.itemMenuList.get(2).add(new MenuItem("Bill History"));

        for (int i = 0; i < this.menuList.size(); i++) {
            for (MenuItem item : this.itemMenuList.get(i)) {
                this.menuList.get(i).getItems().add(item);
                item.setOnAction(event -> {
                    this.tabBuffer.addTab(item.getText());
                });
            }
            this.getMenus().add(this.menuList.get(i));
        }
    }

    public void addMenuITem(String text, Group page) {
        MenuItem item = new MenuItem(text);
        this.itemMenuList.get(0).add(item);
        this.menuList.get(0).getItems().add(item);
        item.setOnAction(event -> this.tabBuffer.addTabPlugin(item.getText(), page));
    }
}