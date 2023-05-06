package org.example.program.page;

import org.example.program.components.CustomButton;
import org.example.program.components.NewLabel;
import org.example.program.components.SearchBar;
import java.util.ArrayList;
import java.util.List;


public class MemberDirectory extends BasePage {
    private CustomButton buttonVIP;
    private CustomButton buttonMember;
    private CustomButton buttonDeactivated;
    private NewLabel label;
    private SearchBar searchBar;
    public MemberDirectory() {

        this.changeBackground("#FFFFFF");
        this.label = new NewLabel("Member Directory", 48, "#867070", 700);
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
        this.searchBar.setLayout(55,140);

        this.buttonVIP = new CustomButton("VIP", 12, "#FFFFFF", "#867070", "bold", 10,0,0,10);
        this.buttonVIP.setLayout(355,142);

        this.buttonMember = new CustomButton("Member", 12, "#FFFFFF", "#867070", "bold",1,1,1,1);
        this.buttonMember.setLayout(388, 142);

        this.buttonDeactivated = new CustomButton("Deactivated", 12, "#FFFFFF", "#867070", "bold", 0,10,10,0);
        this.buttonDeactivated.setLayout(448,142);

        this.getChildren().addAll(this.label, this.searchBar, this.buttonVIP, this.buttonMember, this.buttonDeactivated);
    }
}
