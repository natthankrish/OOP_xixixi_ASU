package program.containers;

// Access for XML Adapter
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import program.entities.clients.Client;
import program.entities.clients.Customer;
import program.entities.clients.Member;
import program.entities.clients.VIP;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "ClientContainer")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlSeeAlso({Customer.class, Member.class, VIP.class})
public class ClientContainer{
    @XmlElement(name = "Client")
    private List<Client> buffer;
    @XmlElement(name = "Amount")
    private int amount;

    public void reset() {
        buffer = new ArrayList<>();
        amount = 0;
    }
    public void increaseAmount(){
        amount++;
    }

    public void decreaseAmount(){
        amount--;
    }
    public Client getClientById(Integer id){
        for (Client c : buffer) {
            Integer tempID = c.getId();
            if (tempID.equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void addClient(Client c){
        buffer.add(c);
        amount++;
    }

    public void removeClient(Integer id){
        Integer tempID = 0;
        for (Client c : buffer){
            if (!(c.getType() instanceof Customer)) {
                tempID = c.getId();
                if (tempID.equals(id)) {
                    c.getType().deactivate();
                    break;
                }
            }
        }
    }

    public Integer getMaxID() {
        Integer max = 0;
        boolean first = true;
        for (Client c: buffer){
            Integer tempID = c.getId();
            if (first) {
                max = tempID;
                first = false;
            } else {
                if (tempID.intValue() > max.intValue()){
                    max = tempID;
                }
            }
        }
        return max;
    }


}
