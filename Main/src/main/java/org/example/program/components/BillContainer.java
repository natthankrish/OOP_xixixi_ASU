package org.example.program.components;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;




public class BillContainer extends BorderPane{
    private NewLabel titleLabel;
    private VBox contentPane;

    public BillContainer(){
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

    public BillContainer(List<BillCard> cards) {
        this.titleLabel = new NewLabel("", 44, "#867070", 700);

        this.contentPane = new VBox(10);

        contentPane.getChildren().addAll(cards);

        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.setPrefHeight(520);
        scrollPane.setFitToWidth(true);


        VBox container = new VBox(5, this.titleLabel, scrollPane);
        container.setAlignment(Pos.TOP_LEFT);

        setCenter(container);
        setPrefWidth(470);
    }

    public void addBillCard(BillCard BillCard) {
        contentPane.getChildren().add(BillCard);
    }

    public void removeBillCard(BillCard BillCard) {
        contentPane.getChildren().remove(BillCard);
    }

    public void removeAll() {
        while (!this.contentPane.getChildren().isEmpty()) {
            this.contentPane.getChildren().remove(0);
        }
    }
}



