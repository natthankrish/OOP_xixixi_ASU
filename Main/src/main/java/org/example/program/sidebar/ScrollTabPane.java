package org.example.program.sidebar;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.List;

import org.example.program.components.CloseAllButton;
import org.example.program.components.NewTab;
import org.example.program.containers.Manager;
import org.example.program.entities.bills.Bill;
import org.example.program.page.BasePage;
import org.example.program.page.Transaction;

@Getter
public class ScrollTabPane extends ScrollPane {
    private VBox buffer;
    private static CloseAllButton closeAllButton;
    public ScrollTabPane(double x, double y, CloseAllButton closeAllButton) {
        super();
        this.buffer = new VBox();
        ScrollTabPane.closeAllButton = closeAllButton;
        this.setBackground(Background.fill(Color.valueOf("#F5EBEB")));
        this.setStyle("-fx-background:#F5EBEB;-fx-background-color:transparent;");
        this.setPrefSize(9*x/10, y);
        this.setLayoutX(x/20);
        this.setContent(this.buffer);
        this.setFitToWidth(true);
        this.buffer.setSpacing(10);

        Manager m = Manager.getInstance();
        List<Bill> bills = m.getTransactionContainer().getBuffer();
        if (bills.size() != 0){
            for (Bill bill : bills) {
                if (!bill.getIsFixedBill()){
                    addTab("Transaction");
                    Transaction tab = (Transaction)((NewTab) this.buffer.getChildren().get(this.buffer.getChildren().size() - 1)).getPage();
                    // tab.setBill(bill);
                }
            }
        }
    }

    public void addTab(String text) {
        this.buffer.getChildren().add(new NewTab(text, this.buffer.getChildren(), this.getPrefWidth(), ScrollTabPane.closeAllButton, this.buffer));
    }

    public void addTabPlugin(String text, BasePage page) {
        this.buffer.getChildren().add(new NewTab(text, this.buffer.getChildren(), this.getPrefWidth(), ScrollTabPane.closeAllButton, this.buffer, page));
    }

}
