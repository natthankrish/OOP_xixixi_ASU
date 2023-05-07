package org.example.program.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Product")
@XmlAccessorType (XmlAccessType.FIELD)

public class Product extends Observable implements Serializable, Subject {
    private List<Observer> observers;
    private Integer id;
    private Integer stock;
    private String name;
    private Double price;
    private Double purchasePrice;
    private String category;
    private String image;
    private Boolean active;

    public void display(){
        if (active) {
            System.out.println("ACTIVE PRODUCT");
        } else {
            System.out.println("INACTIVE PRODUCT");
        }
        System.out.println("ID: "+id+", Stock: "+stock);
        System.out.println("Name: "+name);
        System.out.println("Price: "+price +", PurchasePrice: "+purchasePrice);
        System.out.println("Category: "+ category+", Image: "+ image );
    }

    public void increaseStock(Integer n) {
        setStock(getStock()+n);
    }

    public void decreaseStock(Integer n){
        setStock(getStock()-n);
    }

    public void setActive() {
        this.active = true;
    }
    public void setInactive() {
        this.active = false;
    }

    public void setStock(Integer newStock){
        this.stock = newStock;
        notifyObservers();
    }

    public void setPrice(Double newPrice){
        this.price = newPrice;
        notifyObservers();
    }

    public void setPurchasePrice(Double newPurchasePrice){
        this.purchasePrice = newPurchasePrice;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
