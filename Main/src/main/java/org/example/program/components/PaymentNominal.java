package org.example.program.components;

import org.example.program.entities.bills.Bill;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.VIP;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaymentNominal extends BorderPane {
    private Bill bill;
    private Client client;
    private Double customerPayNominal;
    private NewLabel customerPayNominalLabel;
    private Double customerGetPoints = 0.0;
    private NewLabel customerGetPointsLabel;
    private NewLabel grandTotalNominal;
    
    public PaymentNominal(Bill bill, Client client, Double reducePoint){
        // set the labels;
        this.bill = bill;
        this.client = client;
        this.grandTotalNominal = new NewLabel("IDR " + Double.toString(this.bill.getTotalPrice()), 60, "#867070", 700 );
        
        if (this.client!=null){
            if (this.client.getType() instanceof VIP){
                this.customerPayNominal = this.bill.getTotalPrice() * 0.9;
            }
        }

        this.customerPayNominalLabel = new NewLabel("IDR " +  Double.toString(bill.getTotalPrice()), 60, "#867070", 700 );
        this.customerGetPointsLabel = new NewLabel("CUSTOMER GET " + Double.toString(this.customerGetPoints) + " POINTS", 24, "WHITE", 700 );

        NewLabel grandTotalLabel = new NewLabel("Grand Total", 24, "#867070", 700 );
        NewLabel customerPayLabel = new NewLabel("Customer Pay", 24, "#867070", 700 );

        CustomButton completePaymentButton = new CustomButton("Complete Payment", 24, "#F5EBEB", "#867070", "bold", 10, 10, 10, 10  );

        // make border pane for each part
        BorderPane grandTotalPane = new BorderPane();
        VBox grandTotalBox = new VBox(5, grandTotalLabel, grandTotalNominal);
        grandTotalPane.setCenter(grandTotalBox);
        grandTotalPane.setPadding(new Insets(20));
        grandTotalPane.setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        grandTotalPane.setPrefWidth(470);
        
        BorderPane customerPayPane = new BorderPane();
        VBox customerPayBox = new VBox(5, customerPayLabel, customerPayNominalLabel);
        customerPayPane.setCenter(customerPayBox);
        customerPayPane.setPadding(new Insets(20));
        customerPayPane.setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        customerPayPane.setPrefWidth(470);

        BorderPane pointsPane = new BorderPane();
        VBox pointsBox = new VBox(5, customerGetPointsLabel);
        pointsPane.setCenter(pointsBox);
        pointsPane.setPadding(new Insets(20));
        pointsPane.setBackground(new Background(new BackgroundFill(Color.web("#D5B4B4"), new CornerRadii(10), Insets.EMPTY)));
        pointsPane.setPrefWidth(470);

        BorderPane paymentCompletedPane = new BorderPane();
        VBox paymentCompletedBox = new VBox(5, completePaymentButton);
        paymentCompletedPane.setCenter(paymentCompletedBox);
        paymentCompletedPane.setPadding(new Insets(20));
        paymentCompletedPane.setBackground(new Background(new BackgroundFill(Color.web("#867070"), new CornerRadii(10), Insets.EMPTY)));
        paymentCompletedPane.setPrefWidth(470);
        
        VBox mainVBox = new VBox(10, grandTotalPane, customerPayPane, pointsPane, paymentCompletedPane );
        setCenter(mainVBox);

    }

    public void setGrandTotalNominal(Double newNominal){
        this.grandTotalNominal.setText("IDR " + Double.toString(newNominal));
    }   

    public void reduceCustomerPayNominal(Double number) {
        this.customerPayNominal = this.customerPayNominal - number;
        this.customerPayNominalLabel.setText("IDR " + Double.toString(this.customerPayNominal));
        System.out.println("tesdalam");
    }
    public void setCustomerPayNominal(Double newNominal){
        this.customerPayNominal = newNominal;
        this.customerPayNominalLabel.setText("IDR " + Double.toString(customerPayNominal));
    }
    public Double getCustomerPayNominal(){
        return this.customerPayNominal;
    }

    public void setCustomerGetPoints(Double newPoints){
        this.customerGetPoints = newPoints;
        this.customerGetPointsLabel.setText("CUSTOMER GET " + Double.toString(this.customerGetPoints) + " POINTS");
    };
}
