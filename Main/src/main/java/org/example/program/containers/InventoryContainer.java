package org.example.program.containers;

import org.example.program.entities.commodities.Commodity;
import org.example.program.entities.commodities.Product;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "InventoryContainer")
@XmlAccessorType (XmlAccessType.FIELD)

public class InventoryContainer implements Serializable {
    @XmlElement(name = "Product")
    private List<Commodity> buffer;
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

    public Commodity getProductById(Integer id){
        for (Commodity obj : buffer){
            Integer tempID = obj.getId();
            if (tempID.equals(id)){
                return obj;
            }
        }
        return null;
    }

    public void addProduct(Commodity obj){
        buffer.add(obj);
        amount++;
    }

    public void removeProduct(Integer id){
        for (Commodity obj : buffer){
            Integer tempID = obj.getId();
            if (tempID.equals(id)){
                obj.setActive(false);
                break;
            }
        }
    }

    public Integer getMaxID() {
        Integer max = 0;
        boolean first = true;
        for (Commodity obj: buffer){
            Integer tempID = obj.getId();
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
