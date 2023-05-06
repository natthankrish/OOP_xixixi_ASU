package program.containers;


import lombok.*;
import program.entities.Bill;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "ClientContainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionContainer implements Serializable {
    @XmlAnyElement(lax = true)
    private List<Bill> buffer;
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

    public Bill getProductById(Integer id){
        for (Bill obj : buffer){
            Integer tempID = obj.getIdBill();
            if (tempID.equals(id)){
                return obj;
            }
        }
        return null;
    }

    public void addProduct(Bill obj){
        buffer.add(obj);
        amount++;
    }

//    public void removeProduct(Integer id){
//        int idx = 0;
//        for (Bill obj : buffer){
//            Integer tempID = obj.getIdBill();
//            if (tempID.equals(id)){
//                buffer.remove(idx);
//                amount--;
//                break;
//            }
//            idx++;
//        }
//    }

    public Integer getMaxID() {
        Integer max = 0;
        boolean first = true;
        for (Bill obj: buffer){
            Integer tempID = obj.getIdBill();
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
