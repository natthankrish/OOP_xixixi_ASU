package program.container;

import program.entities.Product;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class InventoryContainer {
    public List<Product> buffer;
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
        int idx = 0;
        for (Product obj : buffer){
            Integer tempID = obj.getId();
            if (tempID.equals(id)){
                buffer.remove(idx);
                amount--;
                break;
            }
            idx++;
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
