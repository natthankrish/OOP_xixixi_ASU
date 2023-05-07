package org.example.program.containers;


import lombok.*;
import org.example.program.entities.bills.Bill;

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

    public Bill getBillById(Integer id){
        for (Bill obj : buffer){
            Integer tempID = obj.getIdBill();
            if (tempID.equals(id)){
                return obj;
            }
        }
        return null;
    }

    public void addBill(Bill obj){
        buffer.add(obj);
        amount++;
    }

//    public void removeBill(Integer id){
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
