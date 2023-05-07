package org.example.program.components;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaymentNominal extends BorderPane {
    private NewLabel grandTotalNominal;
    private NewLabel customerPayNominal;
    private NewLabel pointsLabel;

    
    
    public PaymentNominal(int grandTotal, int customerPay, int points){
        // set the labels;
        this.grandTotalNominal = new NewLabel("IDR " + Integer.toString(grandTotal), 60, "#867070", 700 );
        this.customerPayNominal = new NewLabel("IDR " + Integer.toString(customerPay), 60, "#867070", 700 );
        this.pointsLabel = new NewLabel("CUSTOMER GET " + Integer.toString(points) + " POINTS", 24, "WHITE", 700 );

        NewLabel grandTotalLabel = new NewLabel("Grand Total", 24, "#867070", 700 );
        NewLabel customerPayLabel = new NewLabel("Customer Pay", 24, "#867070", 700 );

        NewLabel paymentCompletedLabel = new NewLabel("Payment Completed", 24, "WHITE", 700 );

        // make border pane for each part
        BorderPane grandTotalPane = new BorderPane();
        VBox grandTotalBox = new VBox(5, grandTotalLabel, grandTotalNominal);
        grandTotalPane.setCenter(grandTotalBox);
        grandTotalPane.setPadding(new Insets(20));
        grandTotalPane.setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        grandTotalPane.setPrefWidth(470);
        
        BorderPane customerPayPane = new BorderPane();
        VBox customerPayBox = new VBox(5, customerPayLabel, customerPayNominal);
        customerPayPane.setCenter(customerPayBox);
        customerPayPane.setPadding(new Insets(20));
        customerPayPane.setBackground(new Background(new BackgroundFill(Color.web("#F5EBEB"), new CornerRadii(10), Insets.EMPTY)));
        customerPayPane.setPrefWidth(470);

        BorderPane pointsPane = new BorderPane();
        VBox pointsBox = new VBox(5, pointsLabel);
        pointsPane.setCenter(pointsBox);
        pointsPane.setPadding(new Insets(20));
        pointsPane.setBackground(new Background(new BackgroundFill(Color.web("#D5B4B4"), new CornerRadii(10), Insets.EMPTY)));
        pointsPane.setPrefWidth(470);

        BorderPane paymentCompletedPane = new BorderPane();
        VBox paymentCompletedBox = new VBox(5, paymentCompletedLabel);
        paymentCompletedPane.setCenter(paymentCompletedBox);
        paymentCompletedPane.setPadding(new Insets(20));
        paymentCompletedPane.setBackground(new Background(new BackgroundFill(Color.web("#867070"), new CornerRadii(10), Insets.EMPTY)));
        paymentCompletedPane.setPrefWidth(470);
        
        VBox mainVBox = new VBox(10, grandTotalPane, customerPayPane, pointsPane, paymentCompletedPane );
        setCenter(mainVBox);
    }

    public void setGrandTotalNominal(int newNominal){
        this.grandTotalNominal.setText("IDR " + Integer.toString(newNominal));

    }   
    public void setCustomerPayNominal(int newNominal){
        this.customerPayNominal.setText("IDR " + Integer.toString(newNominal));
    }
    public void setPoints(int newPoints){
        this.pointsLabel.setText("CUSTOMER GET " + Integer.toString(newPoints) + " POINTS");
    }
    
    
}
