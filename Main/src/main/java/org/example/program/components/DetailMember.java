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
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.ClientType;
import org.example.program.entities.clients.Member;
import org.example.program.entities.clients.VIP;

public class DetailMember extends BorderPane {
    private NewLabel name;
    private int id;
    private CustomButton statusButton;
    private CustomButton kiri;
    private CustomButton kanan;
    private int transaksiCount;

    private String terakhirTransaksi;

    private Client pelanggan;

    private int total;
    public DetailMember(String name,
                        int id,
                        Client pelanggan,
                        int transaksiCount,
                        String terakhirTransaksi,
                        int total) {
        this.name = new NewLabel(name, 24, "#867070", 700);
        this.id = id;
        String valtype = "";
        if (pelanggan.getType() instanceof Member
                && pelanggan.getActiveStatus()){
            valtype = "Member";
        } else if (pelanggan.getType() instanceof VIP
                && pelanggan.getActiveStatus()){
            valtype = "VIP";
        }
        else{
            valtype = "Deactivated";
        }

        String buttonKiri = "";
        if(pelanggan.getType() instanceof Member
                && pelanggan.getActiveStatus()){
            buttonKiri = "Make VIP";
        }
        else if(pelanggan .getType() instanceof VIP
                && pelanggan.getActiveStatus()){
            buttonKiri = "Make Regular";
        }
        else {
            buttonKiri = "Activate as Member";
        }

        String buttonKanan = "";
        if (!pelanggan.getActiveStatus()){
            buttonKanan = "Activate as VIP";
        }
        else {
            buttonKanan = "Deactivated";
        }

        this.statusButton = new CustomButton(valtype, 12,"#FFFFFF","#BEB2B2","bold",10,10,10,10);
        this.kiri = new CustomButton(buttonKiri, 16, "#FFFFFF", "#867070", "bold", 10,10,10,10);
        this.kanan = new CustomButton(buttonKanan, 16, "#FFFFFF", "#867070", "bold", 10,10,10,10);
        this.transaksiCount = transaksiCount;
        this.pelanggan = pelanggan;
        this.total = total;
        this.terakhirTransaksi=terakhirTransaksi;
        // Set up the card's layout
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        // Set the preferred width and height of the card
        setPrefSize(394, 345);



        // Set up the item name and quantity
//        Text phoneText = new Text(this.date);
//        phoneText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15) );
//        phoneText.setFill(Color.web("#867070"));

        Text idText = new Text("ID " + String.valueOf(id));
        idText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        idText.setFill(Color.web("#867070"));

        Text countTransaction = new Text("Count Transaction");
        countTransaction.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        countTransaction.setFill(Color.web("#867070"));

        Text countTransactionData = new Text(String.valueOf(this.transaksiCount));
        countTransactionData.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        countTransactionData.setFill(Color.web("#867070"));

        Text terakhirDate = new Text("Last Transaction");
        terakhirDate.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        terakhirDate.setFill(Color.web("#867070"));

        Text terakhirDateData = new Text(String.valueOf(this.terakhirTransaksi));
        terakhirDateData.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        terakhirDateData.setFill(Color.web("#867070"));

        Text spendTotal = new Text("Total Spend");
        spendTotal.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        spendTotal.setFill(Color.web("#867070"));

        Text spendTotalData = new Text(String.valueOf(this.total));
        spendTotalData.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
        spendTotalData.setFill(Color.web("#867070"));

        HBox bottom = new HBox(20,
                this.kiri,
                this.kanan
        );
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        VBox data = new VBox(20,
                countTransactionData,
                terakhirDateData,
                spendTotalData);
        data.setAlignment(Pos.CENTER_RIGHT);

        VBox isi = new VBox(20,
                countTransaction,
                terakhirDate,
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
        contentPane.getChildren().addAll(topBox, status, isi, data,bottom);
        setCenter(contentPane);

        // Add event handler for mouse click
//        setOnMouseClicked(event -> {
//            System.out.println("customer clicked!");
//            // Add code here to perform the desired action when the card is click
//        });
//
//        // Add event handlers for mouse enter and exit
//        setOnMouseEntered(event -> {
//            setCursor(Cursor.HAND);
//            setBackground(new Background(new BackgroundFill(Color.web("EAD7D7"), new CornerRadii(10), Insets.EMPTY)));
//        });
//
//        setOnMouseExited(event -> {
//            setCursor(Cursor.DEFAULT);
//            setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
//        });
    }

//    public void setData(String name,
//                        int id,
//                        String phone,
//                        String pelanggan,
//                        int transaksiCount,
//                        String terakhirTransaksi,
//                        int total) {
//        // Set the data for the DetailMember object
//        // This code will depend on how you want to display the data in the DetailMember object
//        this.name.setText(name);
//        this.id.setText(String.valueOf(id));
//        this.phone.setText(phone);
//        this.pelanggan.setText(pelanggan);
//        this.transaksiCount.setText(String.valueOf(transaksiCount));
//        this.terakhirTransaksi.setText(terakhirTransaksi);
//        this.total.setText(String.valueOf(total));
//    }


    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}