package org.example.program.page;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import org.example.program.components.*;
import org.example.program.containers.TransactionContainer;
import org.example.program.entities.Bill;
import org.example.program.containers.Manager;
import org.example.program.entities.ReceiptInfo;

//import com.itextpdf.text.*;


public class BillHistory extends BasePage {
    private TransactionContainer tc;

    private BillContainer billContainer;
    private BillInfo info;

    private VBox rightLayout;
    private VBox leftLayout;

    private BillSearhBar searchBar;

    public BillHistory () {
        this.tc = Manager.getInstance().getTransactionContainer();
        NewLabel title = new NewLabel("Bill History", 60, "#867070", 700);

        this.billContainer = new BillContainer(new ArrayList<>());
        this.searchBar = new BillSearhBar("Enter Customer ID here");
        this.searchBar.setOnKeyReleased(event -> {
            String inputText = searchBar.getText();
            if (inputText != null && !inputText.isEmpty()) {
                this.refreshContainer(Integer.parseInt(inputText));
                System.out.println(inputText);
            } else {
                this.refreshContainer(-1);
            }
        });

        leftLayout = new VBox(10, title, this.searchBar, this.billContainer);


        info = new BillInfo();
        rightLayout = new VBox(10, info);
        refreshContainer(-1);
        HBox layout = new HBox(40, leftLayout, rightLayout);
        layout.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth()/20);
        this.getChildren().add(layout);
    }

    private void refreshContainer(int idCustomer) {
        if (idCustomer == -1) {
            for (Bill bill: tc.getBuffer()) {
                BillCard billCard = new BillCard(bill.getIdBill(), bill.getIdClient(), bill.getTransactionTime(), this);
                this.billContainer.addBillCard(billCard);
            }
            return;
        }
        this.billContainer.removeAll();
        for (Bill bill: tc.getBuffer()) {
            if (bill.getIdClient() == idCustomer) {
                System.out.println(idCustomer);
                this.billContainer.addBillCard(new BillCard(bill.getIdBill(), bill.getIdClient(), bill.getTransactionTime(), this));
            }
        }
    }

    public void refreshInfo(int idBill) {
        this.rightLayout.getChildren().remove(this.info);
        this.info = new BillInfo(idBill);
        this.rightLayout.getChildren().add(this.info);
    }

}
class DownloadButton extends Button {
    public DownloadButton() {
        setStyle("""
        -fx-text-fill: #F5EBEB;
        -fx-background-color: #867070;
        -fx-pref-height: 23.3;
        -fx-pref-width: 72;
        -fx-font-size: 8;
        -fx-font-weight: bold;
        -fx-background-radius: 9;
        
        """);
        this.setText("Download");
    }
}
class BillInfo extends BorderPane {
    private Bill bill;
    private BillCard head;
    private ReceiptScroll body;
    public BillInfo() {
        this.setStyle("""
            -fx-background-color: transparent;
            -fx-pref-height: 518;
            -fx-pref-width: 432;
        """);
    }
    public BillInfo(int idBill) {
        this.setStyle("""
            -fx-background-color: #F5EBEB;
            -fx-pref-height: 518;
            -fx-pref-width: 432;
        """);

        this.bill = Manager.getInstance().getTransactionContainer().getBillById(idBill);

        this.head = new BillCard(this.bill.getIdBill(), this.bill.getIdClient(), this.bill.getTransactionTime(), null);

        List<ReceiptInfo> receiptInfo = bill.getReceipt();
        List<ReceiptCard> receipts = new ArrayList<>();
        for (ReceiptInfo receipt: receiptInfo) {
            ReceiptCard receiptCard = new ReceiptCard(receipt);
            receipts.add(receiptCard);
        }
        this.body = new ReceiptScroll(receipts);
        DownloadButton downloadButton = new DownloadButton();
        downloadButton.setOnAction(e -> {
            System.out.println(this.stringify());
                });
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            File selectedDirectory = directoryChooser.showDialog(null);
//            if (selectedDirectory != null) {
//                String filePath = selectedDirectory.getPath() + File.separator + "invoice" + this.bill.getIdBill() + ".pdf";
//                Document document = new Document(PageSize.A4, 36, 72, 108, 180);
//                try {
//                    PdfWriter.getInstance(document,new FileOutputStream(filePath));
//                    document.open();
//                    document.add(new Paragraph(this.stringify()));
//                    System.out.println("Text is inserted into pdf file");
//                    document.close();
//                } catch (FileNotFoundException er) {
//                    System.out.println("PPP");
//                } catch (DocumentException ed) {
//                    System.out.println("PPP");
//                }
//            }
//        });

        PriceCard bottom = new PriceCard(bill.getTotalPrice() * 0.1, bill.getTotalPrice() * 0.1);

        VBox layout = new VBox(0, head, body, bottom, downloadButton);

        setTop(layout);
        setMargin(layout, new Insets(0));
        setAlignment(layout, Pos.CENTER);
    }
    public String stringify() {
        String result =
                "BILL ID       : " + this.head.getIdBill() + "\n"
                        +"CLIENT ID     : " + this.head.getIdCustomer() + "\n"
                        +"CUSTOMER NAME : " + Manager.getInstance().getClientContainer().getClientById(this.head.getIdCustomer()).getName()
                        +"\n\n";

        List<List<String>> data = this.body.stringify();
        result += String.format("%-4s%-25s%-15s%-15s\n", "#", "Item Name", "Quantity", "Subtotal");
        for (int i = 0; i < data.size(); i++) {
            List<String> row = data.get(i);
            result += String.format("%-4d%-25s%-15s%-15s\n", i + 1, row.get(0), row.get(1), row.get(2));
        }
        result += "\nTOTAL PRICE   : " + String.valueOf(this.bill.getTotalPrice())
                +"\nDATE/TIME     : " + this.head.getTime().getStringTime();
        System.out.println(result);
        return result;
    }
}

class ReceiptCard extends BorderPane {
    private ReceiptInfo receiptInfo;
    public ReceiptCard(ReceiptInfo receiptInfo) {
        this.receiptInfo = receiptInfo;
        Manager m = Manager.getInstance();
        NewLabel itemName = new NewLabel(m.getInventoryContainer().getProductById(receiptInfo.getProductID()).getName() + "", 24, "#867070", 700);
        NewLabel itemQuantity = new NewLabel("Quantity: " + receiptInfo.getQuantity(), 24, "#867070", 700);
        NewLabel subTotal = new NewLabel(String.format("%.2f", receiptInfo.getSubtotal()), 24, "#867070", 700);

        setBackground(new javafx.scene.layout.Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        setPrefSize(457, 80);

        setPadding(new Insets(10));

        VBox leftBox = new VBox(5, itemName, itemQuantity);
        leftBox.setPrefWidth(this.getPrefWidth() *2/ 3);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        VBox rightBox = new VBox(5, subTotal);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        rightBox.setPrefWidth(this.getPrefWidth()/ 3);

        HBox layout = new HBox(20, leftBox, rightBox);
        StackPane contentPane = new StackPane();
        contentPane.getChildren().add(layout);
        setCenter(contentPane);

    }
    public List<String> stringify() {
        Manager m = Manager.getInstance();
        ArrayList<String> res = new ArrayList<>(Arrays.asList(
                m.getInventoryContainer().getProductById(receiptInfo.getProductID()).getName(),
                String.valueOf(receiptInfo.getQuantity()),
                String.valueOf(receiptInfo.getSubtotal())
        ));
        return res;
    }

}

class ReceiptScroll extends BorderPane{
    private NewLabel titleLabel;
    List<ReceiptCard> cards;
    private VBox contentPane;

    public ReceiptScroll(){
        this.cards = new ArrayList<>();
        this.titleLabel = new NewLabel("", 44, "#867070", 700);

        this.contentPane = new VBox(10);

        ScrollPane scrollPane = new ScrollPane(contentPane);

        scrollPane.setPrefHeight(520);
        scrollPane.setFitToWidth(true);

        VBox container = new VBox(5, this.titleLabel, scrollPane);
        container.setAlignment(Pos.TOP_LEFT);

        setCenter(container);
        setPrefWidth(470);
    }

    public ReceiptScroll(List<ReceiptCard> cards) {
        this.contentPane = new VBox(10);

        this.cards = cards;

        contentPane.getChildren().addAll(cards);

        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.setPrefHeight(520);
        scrollPane.setFitToWidth(true);


        VBox container = new VBox(5, scrollPane);
        container.setAlignment(Pos.TOP_LEFT);

        setCenter(container);
        setPrefWidth(470);
    }

    public List<List<String>> stringify() {
        List<List<String>> res = new ArrayList<>();
        for (ReceiptCard obj: this.cards) {
            res.add(obj.stringify());
        }
        return res;
    }
}

class PriceCard extends BorderPane {
    public PriceCard(double tax, double serviceCharge){
        NewLabel taxLabel = new NewLabel("Tax (10%)", 24, "#867070", 700);
        NewLabel serviceChargeLabel = new NewLabel("Service Charge (10%)", 24, "#867070", 700);
        NewLabel numTax = new NewLabel(tax + "".toString(), 24, "#867070", 700);
        NewLabel numServiceCharge = new NewLabel(serviceCharge + "".toString(), 24, "#867070", 700);

        setBackground(new javafx.scene.layout.Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        setPrefSize(457, 80);
        setPadding(new Insets(10));

        VBox leftBox = new VBox(5, taxLabel, serviceChargeLabel);
        leftBox.setAlignment(Pos.TOP_LEFT);

        VBox rightBox = new VBox(5, numTax, numServiceCharge);
        rightBox.setAlignment(Pos.TOP_LEFT);

        HBox layout = new HBox(20, leftBox, rightBox);
        StackPane contentPane = new StackPane();
        contentPane.getChildren().add(layout);
        setCenter(contentPane);
    }

}
class BillSearhBar extends TextField {
    public BillSearhBar(String placeHolder) {
        this.setPromptText(placeHolder);
        this.setStyle("""
                -fx-font-family: Inter;
                -fx-font-size: 12;
                -fx-font-weight: bold;
                -fx-background-color: #D5B4B4;
                -fx-pref-height: 27.46;
                -fx-pref-width: 254.11;
                -fx-background-radius: 9;
                -fx-border-radius: 9;
                -fx-border-color: BLACK;
                -fx-border-width: 1;
                -fx-prompt-text-fill: black;
        """);
    }
}






