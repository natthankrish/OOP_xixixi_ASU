package org.example.program.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.example.program.entities.clients.ClientType;
import org.example.program.entities.clients.Customer;
import org.example.program.entities.clients.Member;


public class DetailRegister extends BorderPane {
    private NewLabel name;
    private int id;
    private String date;
    private CustomButton statusButton;
    private CustomButton registerButton;
    private int itemCount;

    private String tanggalTransaksi;

    private ClientType pelanggan;

    private AddRegister masukin;

    private int total;
    public DetailRegister(String name,
                          int id,
                          String date,
                          ClientType pelanggan,
                          int itemCount,
                          String tanggalTransaksi,
                          int total) {
        this.name = new NewLabel("Customer", 30, "#867070", 700);
        this.id = id;
        this.date = date;
        String valtype = "";
        if (pelanggan instanceof Customer){
            valtype = "Customer";
        } else {
            valtype = "VIP";
        }
        this.statusButton = new CustomButton(valtype, 12,"#FFFFFF","#BEB2B2","bold",10,10,10,10);
        this.registerButton = new CustomButton("Register", 16, "#FFFFFF", "#867070", "bold", 10,10,10,10);
        this.itemCount = itemCount;
        this.pelanggan = pelanggan;
        this.total = total;
        this.tanggalTransaksi=tanggalTransaksi;
        // Set up the card's layout
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        // Set the preferred width and height of the card
        setPrefSize(394, 310);



        // Set up the item name and quantity
        Text dateText = new Text(String.valueOf(date));
        dateText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15) );
        dateText.setFill(Color.web("#867070"));

        Text idText = new Text("ID" + String.valueOf(id));
        idText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        idText.setFill(Color.web("#867070"));

        Text countItem = new Text("Count Item");
        countItem.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        countItem.setFill(Color.web("#867070"));

        Text countItemData = new Text(String.valueOf(this.itemCount));
        countItemData.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        countItemData.setFill(Color.web("#867070"));

        Text transactionDate = new Text("Transaction Date");
        transactionDate.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        transactionDate.setFill(Color.web("#867070"));

        Text transactionDateData = new Text(String.valueOf(this.tanggalTransaksi));
        transactionDateData.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        transactionDateData.setFill(Color.web("#867070"));

        Text spendTotal = new Text("Total Spend");
        spendTotal.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        spendTotal.setFill(Color.web("#867070"));

        Text spendTotalData = new Text(String.valueOf(this.total));
        spendTotalData.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        spendTotalData.setFill(Color.web("#867070"));

        VBox bottom = new VBox(20,
                this.registerButton
        );
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        VBox data = new VBox(20,
                countItemData,
                transactionDateData,
                spendTotalData);
        data.setAlignment(Pos.CENTER_RIGHT);

        VBox isi = new VBox(20,
                countItem,
                transactionDate,
                spendTotal);
        isi.setAlignment(Pos.CENTER_LEFT);

        VBox judul = new VBox(20, this.name, idText);
        judul.setAlignment(Pos.TOP_LEFT);

        // Set up the close button and price
        VBox status = new VBox(5, this.statusButton);
        status.setAlignment(Pos.BASELINE_RIGHT);

        // Combine the nameQuantityBox and imageView in an HBox
        HBox topBox = new HBox(10, judul);
        topBox.setAlignment(Pos.TOP_LEFT);

        // Set up the main content of the card
        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(topBox, status,isi, data,bottom);
        setCenter(contentPane);
    }


    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public CustomButton getRegisterButton(){
        return this.registerButton;
    }
}
