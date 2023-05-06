package program.entities.clients;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Member")
@XmlAccessorType(XmlAccessType.FIELD)

public class Member implements ClientType {
    private String name;
    private String phoneNumber;
    private Double point;
    private Boolean active;
    public static Double FIXED_POINT_RATE = 0.01;

    public void addPoint(Double p){
        this.point += p;
    }

    public void subtractPoint(Double p){
        this.point -= p;
    }


    public Double payment(Double n, Double point) {
        Double tempPoint = n*FIXED_POINT_RATE;
        if ((point > getPoint()) || (point < 0)){
            return null;
        } else if (point > n) {
            subtractPoint(n);
            addPoint(tempPoint);
            return 0.0;
        } else {
            subtractPoint(point);
            addPoint(tempPoint);
            return n-point;
        }
    }

    public void display(){
        System.out.println("Member");
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
