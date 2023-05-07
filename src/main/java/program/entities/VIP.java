package program.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "VIP")
@XmlAccessorType(XmlAccessType.FIELD)

public class VIP extends RegisteredCustomer{

    public static Double FIXED_POINT_RATE = 0.01;
    public static Double FIXED_DISCOUNT_RATE = 0.1;
    public VIP(Integer newID, String name, String phoneNumber, Double point, Boolean active){
        super(newID, name, phoneNumber, point, active);
    }


    @Override
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
        System.out.println("ID: "+id+", Name: "+name);
        System.out.println(transactionHistory);
        System.out.println("Phone Number: "+ phoneNumber);
        System.out.println("Point: "+ point+", Active: "+ active);
    }
}