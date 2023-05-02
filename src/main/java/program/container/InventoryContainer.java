package program.container;

import program.entities.Product;


import lombok.*;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "InventoryContainer")
@XmlAccessorType (XmlAccessType.FIELD)

public class InventoryContainer {
    @XmlElement(name = "Product")
    private List<Product> buffer;
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

    public Product getProductById(Integer id){
        for (Product obj : buffer){
            Integer tempID = obj.getId();
            if (tempID.equals(id)){
                return obj;
            }
        }
        return null;
    }

    public void addProduct(Product obj){
        buffer.add(obj);
        amount++;
    }

    public void removeProduct(Integer id){
        for (Product obj : buffer){
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
        for (Product obj: buffer){
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
