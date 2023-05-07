package org.example.program.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import lombok.Getter;
import org.example.program.containers.Manager;
import org.example.program.entities.bills.Time;
import org.example.program.page.BillHistory;

public class BillCard extends BorderPane {

    @Getter
    private int idBill;
    @Getter
    private int idCustomer;
    @Getter
    private Time time;
    private BillHistory master;


    public BillCard(int idBill, int idCustomer, Time time, BillHistory master){
        this.setStyle("""
        -fx-background-color: #F5EBEB ;
        -fx-background-radius: 15;
        -fx-padding: 10;
        -fx-pref-width: 457;
        -fx-pref-height: 80;
        """);
        this.idBill = idBill;
        this.idCustomer = idCustomer;
        this.time = time;
        NewLabel idBillLabel = new NewLabel("ID BILL      : "+idBill, 24, "#867070", 700);
        NewLabel idCustomerLabel = new NewLabel("ID CLIENT : "+idCustomer, 24, "#867070", 700);
        NewLabel dateLabel = new NewLabel(time.getDate() + "/" +time.getMonth() + "/" + time.getYear(), 24, "#867070", 700);

        VBox leftBox = new VBox(5, idBillLabel, idCustomerLabel);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setMinWidth(this.getPrefWidth()/2);

        VBox rightBox = new VBox(2, dateLabel);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        rightBox.setMinWidth(this.getPrefWidth()/2);

        HBox layout = new HBox(50, leftBox, rightBox);
        StackPane contentPane = new StackPane();
        contentPane.getChildren().add(layout);
        setCenter(contentPane);

        setOnMouseClicked(event -> {
            master.refreshInfo(this.idBill);
        });
    }

    public String stringify() {
        Manager m = Manager.getInstance();
        return "Client ID: " + idCustomer + "/" + m.getClientContainer().getClientById(idCustomer).getName() + "\n"
                + "Bill ID: "+ idBill + "   "  + time.getStringTime();
    }

}
