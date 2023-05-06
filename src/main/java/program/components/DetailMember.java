package program.components;

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
import program.page.Transaction;

public class DetailMember extends BorderPane {
    private NewLabel name;
    private int id;
    private String phone;
    private CustomButton statusButton;
    private CustomButton makeVIP;
    private CustomButton deactivated;
    private int transaksiCount;

    private String terakhirTransaksi;

    private String pelanggan;

    private int total;
    public DetailMember(String name,
                          int id,
                          String phone,
                          String pelanggan,
                          int transaksiCount,
                          String terakhirTransaksi,
                          int total) {
        this.name = new NewLabel(name, 24, "#867070", 700);
        this.id = id;
        this.phone = phone;
        this.statusButton = new CustomButton(pelanggan, 12,"#FFFFFF","#BEB2B2","bold",10,10,10,10);
        this.makeVIP = new CustomButton("Make VIP", 16, "#FFFFFF", "#867070", "bold", 10,10,10,10);
        this.deactivated = new CustomButton("Deactivated", 16, "#FFFFFF", "#867070", "bold", 10,10,10,10);
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
        Text phoneText = new Text(String.valueOf(phone));
        phoneText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15) );
        phoneText.setFill(Color.web("#867070"));

        Text idText = new Text("ID " + String.valueOf(id) + " - " + String.valueOf(phone));
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
                this.makeVIP,
                this.deactivated
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
        setOnMouseClicked(event -> {
            System.out.println("customer clicked!");
            // Add code here to perform the desired action when the card is click
        });

        // Add event handlers for mouse enter and exit
        setOnMouseEntered(event -> {
            setCursor(Cursor.HAND);
            setBackground(new Background(new BackgroundFill(Color.web("EAD7D7"), new CornerRadii(10), Insets.EMPTY)));
        });

        setOnMouseExited(event -> {
            setCursor(Cursor.DEFAULT);
            setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        });
    }


    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}

