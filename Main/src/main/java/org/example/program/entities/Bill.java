package org.example.program.entities;

import java.io.Serializable;
import java.util.Observer;
import java.util.*;
import lombok.*;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Bill")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bill implements Serializable {
    private Integer idBill;
    private Integer idClient;
    private List<ReceiptInfo> receipt;
    private Double totalPrice;
    private Boolean isFixedBill;
    private Time transactionTime;


    public void addBillItem(Integer id, Integer quantity, Double subtotal){
        // subtotal is the value of product's price * quantity
        // search for it first from the product's list before insert is as the parameter
        if (!isFixedBill){
            ReceiptInfo info = new ReceiptInfo(id, quantity, subtotal);
            this.receipt.add(info);
            updateTransactionTime();
            recalculateTotalPrice();
        }
    }

    public void updateBillItem(Integer id, Integer quantity, Double subtotal){
        // Only insert the parameter for the changing element, else will be inserted as null
        // e.g. if you want to ONLY change the quantity, insert (id, quantity, null)
        if (!isFixedBill){
            ReceiptInfo info = null;
            for (ReceiptInfo i: receipt) {
                if (i.getProductID().equals(id)){
                    info = i;
                }
            }
            if (!info.equals(null)){
                info.setQuantity(quantity);
                info.setSubtotal(subtotal);
                updateTransactionTime();
                recalculateTotalPrice();
            }
        }
    }

    public void removeBillItem(Integer id){
        // Delete item based on the product id
        if (!isFixedBill){
            int idx = 0;
            for (ReceiptInfo i: receipt) {
                if (i.getProductID().equals(id)){
                    receipt.remove(idx);
                    updateTransactionTime();
                    recalculateTotalPrice();
                    break;
                }
                idx++;
            }

        }
    }

    public boolean isProductIDInReceipt(int id){
        // Check whether a product with certain ID is already in the receipt
        for (ReceiptInfo i: receipt) {
            if (i.getProductID().equals(id)){
                return true;
            }
        }
        return false;
    }


    public void setFixed() {
        // Make temporary bill to be a fixed one
        this.isFixedBill = true;
    }

    public void updateTransactionTime(){
        // updating transaction time everytime the receipt is changed
        transactionTime.updateCurrentTime();
    }

    public void display() {
        System.out.println("ID: " + idBill + ", IDClient: " + idClient);
        for (ReceiptInfo i : receipt) {
            System.out.println("[ " + i.getProductID() + ", " + i.getQuantity() + ", " + i.getSubtotal() + " ]");
        }
        System.out.println("Total price: " + totalPrice);
        System.out.println("Fix status: " + isFixedBill + ", Time: " + transactionTime.getStringTime());

    }

    public void recalculateTotalPrice(){
        Double res = 0.0;
        for ( ReceiptInfo i: receipt) {
            res += (Double) i.getSubtotal();
        }
        this.totalPrice = res;
    }

}
