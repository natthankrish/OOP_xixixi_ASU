package program.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Customer")
@XmlAccessorType (XmlAccessType.FIELD)

public class Customer extends Client{

    // Constructor
    public Customer(Integer newID) {
        super(newID);
    }

    // Methods
    @Override
    public Double payment(Double n, Double point) {
        return n;
    }

    public void display(){
        System.out.println("Customer");
        System.out.println("ID: "+id);
        System.out.println(transactionHistory);
    }

}
