package org.example.program.entities.clients;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "VIP")
@XmlAccessorType(XmlAccessType.FIELD)

public class VIP implements ClientType, Serializable {
    private String name;
    private String phoneNumber;
    private Double point;
    private Boolean active;
    public static Double FIXED_POINT_RATE = 0.01;
    public static Double FIXED_DISCOUNT_RATE = 0.1;

    public void addPoint(Double p){
        this.point += p;
    }

    public void subtractPoint(Double p){
        this.point -= p;
    }

    public Double payment(Double n, Double point) {
        Double tempPoint = n* FIXED_POINT_RATE;
        Double tempPrice = n*(1-FIXED_DISCOUNT_RATE);
        if ((point > getPoint()) || (point < 0)){
            return null;
        } else if (point > tempPrice){
            subtractPoint(tempPrice);
            addPoint(tempPoint);
            return 0.0;
        } else {
            subtractPoint(point);
            addPoint(tempPoint);
            return tempPrice - point;
        }
    }

    public void display(){
        System.out.println("VIP");
        System.out.println("Name: "+name);
        System.out.println("Phone Number: "+ phoneNumber);
        System.out.println("Point: "+ point+", Active: "+ active);
    }

    public void deactivate(){
        this.active = false;
    }

    public void activate(){
        this.active = true;
    }
}
