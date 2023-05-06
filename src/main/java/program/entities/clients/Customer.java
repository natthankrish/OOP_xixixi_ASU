package program.entities.clients;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import program.entities.clients.Client;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Customer")
@XmlAccessorType (XmlAccessType.FIELD)

public class Customer implements ClientType {

    // Methods
    public Double payment(Double n, Double point) {
        return n;
    }

    public void display(){
        System.out.println("Customer");
    }
    public void deactivate(){}

    public void activate(){}

}
