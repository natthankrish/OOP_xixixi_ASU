package org.example.program.page;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.util.Duration;
import org.example.program.components.*;
import org.example.program.containers.TransactionContainer;
import org.example.program.entities.bills.Bill;
import org.example.program.containers.Manager;
import org.example.program.entities.bills.ReceiptInfo;
import com.itextpdf.layout.border.Border;


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

        leftLayout = new VBox(10, this.searchBar, this.billContainer);


        info = new BillInfo();
        rightLayout = new VBox(10, info);
        refreshContainer(-1);
        HBox hlayout = new HBox(40, leftLayout, rightLayout);
        VBox layout = new VBox(10, title, hlayout);
        layout.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() / 20);
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
    public DownloadButton(int i) {
        String style = ("""
        -fx-text-fill: #F5EBEB;
        -fx-pref-height: 25;
        -fx-pref-width: 90;
        -fx-font-size: 12;
        -fx-font-weight: bold;
        -fx-background-radius: 9;
        """);
        this.setStyle(style + "-fx-background-color: " + ((i == 0)? "#E57F78" : "#867070"));
        setTransition(i);
        this.setText("Download");
    }
    public void setTransition(int i){
        if (i == 0) {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(100), this);
            rotateTransition.setByAngle(10);
            rotateTransition.setCycleCount(2);
            rotateTransition.setAutoReverse(true);
            rotateTransition.setInterpolator(Interpolator.EASE_BOTH);
            this.setOnMousePressed(event -> rotateTransition.playFromStart());
        } else {
            ScaleTransition scaleTransition = new ScaleTransition(new Duration(0.2), this);
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(0.8);
            scaleTransition.setToY(0.8);
            scaleTransition.setAutoReverse(true);
            this.setOnMousePressed(event -> scaleTransition.playFromStart());
            this.setOnMouseReleased(event -> {
                scaleTransition.stop();
                this.setScaleX(1);
                this.setScaleY(1);
            });
        }
    }
}
class BillInfo extends BorderPane {
    private Bill bill;
    private BillCard head;
    private ReceiptScroll body;
    public BillInfo() {
        this.setStyle("""
            -fx-background-color: transparent;
        """);
        this.setPrefSize(Screen.getPrimary().getVisualBounds().getWidth() * 2 / 8, Screen.getPrimary().getVisualBounds().getHeight() * 5 / 8);
    }
    public BillInfo(int idBill) {
        this.setStyle("""
            -fx-background-color: #F5EBEB;
            -fx-background-radius: 16;
        """);
        this.setPrefSize(Screen.getPrimary().getVisualBounds().getWidth() * 2 / 8, Screen.getPrimary().getVisualBounds().getHeight() * 5 / 8);


        this.bill = Manager.getInstance().getTransactionContainer().getBillById(idBill);

        this.head = new BillCard(this.bill.getIdBill(), this.bill.getIdClient(), this.bill.getTransactionTime(), null);

        List<ReceiptInfo> receiptInfo = bill.getReceipt();
        List<ReceiptCard> receipts = new ArrayList<>();
        for (ReceiptInfo receipt: receiptInfo) {
            ReceiptCard receiptCard = new ReceiptCard(receipt);
            receipts.add(receiptCard);
        }
        this.body = new ReceiptScroll(receipts);
        DownloadButton downloadButton = new DownloadButton((this.bill.getIsFixedBill()? 1 : 0));
        downloadButton.setOnAction(e -> {
            if (!this.bill.getIsFixedBill()) {
                return;
            }
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(null);
            if (selectedDirectory != null) {
                String filePath = selectedDirectory.getPath() + File.separator + "invoice" + this.bill.getIdBill() + ".pdf";
                Thread printThread = new Thread(()->{
                    try {
                        Thread.sleep(10000);
                        this.export(filePath);
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                });
                printThread.start();

            }
        });

        PriceCard bottom = new PriceCard(String.format("%.2f",bill.getTotalPrice() * 0.1), String.format("%.2f",bill.getTotalPrice() * 0.1));

        VBox layout = new VBox(0, head, body, bottom, downloadButton);
        layout.setAlignment(Pos.TOP_CENTER);

        setTop(layout);
        setMargin(layout, new Insets(0));
        setAlignment(layout, Pos.CENTER);
    }
    private void export(String path) {
        try {
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            float col = 280.f;
            float columnWidth[] = {col, col};
            Table table = new Table(columnWidth);
            table.setBackgroundColor(new DeviceRgb(213, 180, 180))
                            .setFontColor(new DeviceRgb(0, 0, 0));

            table.addCell(new Cell().add("INVOICE")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER)
            );
            table.addCell(new Cell().add("Nama Perusahaan \n1234 Alamat Perusahaan\n+62 34567890")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setMarginRight(10f)
                    .setBorder(Border.NO_BORDER)
            );

            float colWidth[] = {80, 300, 100, 80};
            Table customerInfoTable = new Table(colWidth);

            customerInfoTable.addCell(new Cell (0, 4)
                    .add("Customer Information")
                    .setBold()
                    .setBorder(Border.NO_BORDER)
            );

            customerInfoTable.addCell(new Cell().add("Name").setBorder(Border.NO_BORDER));
            customerInfoTable.addCell(new Cell()
                    .add(Manager.
                            getInstance()
                            .getClientContainer()
                            .getClientById(this.head.getIdCustomer()).getName()
                    ).setBorder(Border.NO_BORDER)
            );
            customerInfoTable.addCell(new Cell().add("Invoice No.").setBorder(Border.NO_BORDER));
            customerInfoTable.addCell(new Cell().add(this.bill.getIdBill() + "").setBorder(Border.NO_BORDER));

            customerInfoTable.addCell(new Cell().add("Client No.").setBorder(Border.NO_BORDER));
            customerInfoTable.addCell(new Cell().add(this.bill.getIdClient() + "").setBorder(Border.NO_BORDER));
            customerInfoTable.addCell(new Cell().add("Date/Time").setBorder(Border.NO_BORDER));
            customerInfoTable.addCell(new Cell().add(this.bill.getTransactionTime().getStringTime()).setBorder(Border.NO_BORDER));

            float itemInfoColWidth[] = {140, 140, 140, 140};
            Table itemInfoTable = new Table(itemInfoColWidth);
            itemInfoTable.addCell(new Cell()
                    .add("Item Name")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            itemInfoTable.addCell(new Cell()
                    .add("Quantity")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            itemInfoTable.addCell(new Cell()
                    .add("Unit Price")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            itemInfoTable.addCell(new Cell()
                    .add("Subtotal")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            List<ReceiptInfo> receiptInfos = this.bill.getReceipt();
            for(ReceiptInfo elm: receiptInfos) {
                itemInfoTable.addCell(new Cell().add(Manager.getInstance().getInventoryContainer().getProductById(elm.getProductID()).getName()));
                itemInfoTable.addCell(new Cell().add(elm.getQuantity() + ""));
                itemInfoTable.addCell(new Cell().add(elm.getSubtotal()/ elm.getQuantity() + ""));
                itemInfoTable.addCell(new Cell().add(elm.getSubtotal() + ""));
            }
            itemInfoTable.addCell(new Cell().setBorder(Border.NO_BORDER)).addCell(new Cell().setBorder(Border.NO_BORDER));
            itemInfoTable.addCell(new Cell()
                    .add("Total Price")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );
            itemInfoTable.addCell(new Cell()
                    .add(this.bill.getTotalPrice() + "")
                    .setBackgroundColor(new DeviceRgb(134, 112, 112))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
            );

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInfoTable);
            document.add(new Paragraph("\n"));
            document.add(itemInfoTable);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\nAuthorised Signatory")
                    .setTextAlignment(TextAlignment.RIGHT)
            );
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Foobar");
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

        setPrefSize(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8, 80);

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
                String.valueOf(receiptInfo.getSubtotal()/receiptInfo.getQuantity()),
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

        this.contentPane = new VBox(2);

        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.setStyle("-fx-background:white;-fx-background-color:transparent;");

        scrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() * 5 / 8);
        scrollPane.setFitToWidth(true);

        VBox container = new VBox(5, this.titleLabel, scrollPane);
        container.setAlignment(Pos.TOP_LEFT);

        setCenter(container);
        setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8);
    }

    public ReceiptScroll(List<ReceiptCard> cards) {
        this.setStyle("""
        -fx-background-color: #F5EBEB;
        -fx-background: #F5EBEB;
        -fx-min-width: 432;
        -fx-max-width: 432;
        -fx-min-height: 400;
        -fx-max-height: 400;
        """);
        this.contentPane = new VBox(2);
        this.cards = cards;
        contentPane.getChildren().addAll(cards);

        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.setStyle("""
            -fx-background-color: transparent;
            -fx-background: transparent;
            """);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        VBox container = new VBox(0, scrollPane);
        container.setAlignment(Pos.TOP_LEFT);
        setCenter(container);
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
    public PriceCard(String tax, String serviceCharge){
        NewLabel taxLabel = new NewLabel("Tax (10%)", 24, "#867070", 700);
        NewLabel serviceChargeLabel = new NewLabel("Service Charge (10%)", 24, "#867070", 700);
        NewLabel numTax = new NewLabel(tax, 24, "#867070", 700);
        NewLabel numServiceCharge = new NewLabel(serviceCharge, 24, "#867070", 700);

        setBackground(new javafx.scene.layout.Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));

        setPrefSize(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8, 70);
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






