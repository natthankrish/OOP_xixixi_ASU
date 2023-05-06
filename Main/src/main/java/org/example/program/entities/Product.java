package org.example.program.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Product")
@XmlAccessorType (XmlAccessType.FIELD)

public class Product implements Serializable {
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
}
