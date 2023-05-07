package org.example.program.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import org.example.program.containers.Manager;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceiptInfo implements Serializable, Observer {
    private Integer productID;
    private Integer quantity;
    private Double subtotal;
    private Boolean isValid = true;

    public void recalculate(Integer quantity){
        this.quantity = quantity;
        Manager m = Manager.getInstance();
        Double priceEach = m.getInventoryContainer().getProductById(productID).getPrice();
        this.subtotal = quantity * priceEach ;
        update();
    }

    @Override
    public void update(){
        Manager m = Manager.getInstance();
        if(m.getInventoryContainer().getProductById(productID).getStock() < quantity){
            this.isValid = false;
        } else {
            this.isValid = true;
        }
    }

}
