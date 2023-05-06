//package program.page;
//import program.components.NewLabel;
//import program.components.SearchBar;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//
//public class RegisterMember extends BasePage {
//    private NewLabel label;
//    private SearchBar searchBar;
//
//    public RegisterMember() {
//        this.changeBackground("#FFFFFF");
//        this.label = new NewLabel("Register Member", 48, "#867070", 700);
//        this.label.setLayout(45, 35);
//
//        List<String> itemList = new ArrayList<String>();
//        itemList.add("Item 1");
//        itemList.add("Item 2");
//        itemList.add("Item 3");
//        itemList.add("Item 4");
//        this.searchBar = new SearchBar(itemList);
//        this.searchBar.setLayout(255,140);
//
//
//        this.getChildren().addAll(this.label, this.searchBar);
//
//    }
//}



package org.example.program.page;

import org.example.program.components.NewLabel;
import org.example.program.components.SearchBar;
import java.util.ArrayList;
import java.util.List;

public class RegisterMember extends BasePage {
    private NewLabel label;
    private SearchBar searchBar;

    public RegisterMember() {
        this.changeBackground("#FFFFFF");
        this.label = new NewLabel("Register Member", 48, "#867070", 700);
        this.label.setLayout(45, 35);

        List<String> itemList = new ArrayList<String>();
        itemList.add("kon 1");
        itemList.add("kon 2");
        itemList.add("kon 3");
        itemList.add("kon 4");
        itemList.add("kon 5");
        itemList.add("kon 6");
        itemList.add("kon 7");
        itemList.add("kon 8");

        this.searchBar = new SearchBar(itemList);
        this.searchBar.setLayout(255,140);

        this.getChildren().addAll(this.label, this.searchBar);
    }
}
