package program.topbar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopbarFunctional extends MenuBar {

    public TopbarFunctional() {
        // "Go To Page" menu
        Menu goToPageMenu = new Menu("Go To Page");
        MenuItem page1Item = new MenuItem("Page 1");
        MenuItem page2Item = new MenuItem("Page 2");
        MenuItem page3Item = new MenuItem("Page 3");
        goToPageMenu.getItems().addAll(page1Item, page2Item, page3Item);

        // "Settings" menu
        Menu settingsMenu = new Menu("Settings");
        MenuItem settingsItem = new MenuItem("Open Settings");
        settingsMenu.getItems().addAll(settingsItem);

        // "Report" menu
        Menu reportMenu = new Menu("Report");
        MenuItem reportItem = new MenuItem("Generate Report");
        reportMenu.getItems().addAll(reportItem);

        // ACTIONS
        page1Item.setOnAction(event -> {
            // Code to navigate to Page 1
            System.out.println("Navigating to Page 1...");
        });
        page2Item.setOnAction(event -> {
            // Code to navigate to Page 2
            System.out.println("Navigating to Page 2...");
        });
        page3Item.setOnAction(event -> {
            // Code to navigate to Page 3
            System.out.println("Navigating to Page 3...");
        });

        settingsItem.setOnAction(event -> {
            // Code to open settings
            System.out.println("Opening Settings...");
        });

        reportItem.setOnAction(event -> {
            // Code to generate report
            System.out.println("Generating Report...");
        });

        // Add menus to menu bar
        this.getMenus().addAll(goToPageMenu, settingsMenu, reportMenu);
    }
}