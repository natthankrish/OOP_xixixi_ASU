package program.entities;

import java.util.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Bill {
    private Integer idBill;
    private Integer idClient;
    private List<List<Object>> receipt;
    private Double totalPrice;
    private Double discount;
    private Boolean isFixedBill;
    private String transactionTime;

    public void addBillItem(Integer id, Integer quantity, Double subtotal){
        // subtotal is the value of product's price * quantity
        // search for it first from the product's list before insert is as the parameter
        if (!isFixedBill){
            Integer tempID = id;
            Integer tempQuantity = quantity;
            Double tempSubtotal = subtotal;
            List<Object> arr = new ArrayList<>();
            arr.add(tempID);
            arr.add(tempQuantity);
            arr.add(tempSubtotal);
            this.receipt.add(arr);
            updateTransactionTime();
            recalculateTotalPrice();
        }
    }

    public void updateBilLItem(Integer id, Integer quantity, Double subtotal){
        // Only insert the parameter for the changing element, else will be inserted as null
        // e.g. if you want to ONLY change the quantity, insert (id, quantity, null)
        if (!isFixedBill){
            List<Object> temp = null;
            for (List<Object> l: receipt) {
                if (l.get(0).equals(id)){
                    temp = l;
                }
            }
            if (!temp.equals(null)){
                if (!quantity.equals(null)){
                    temp.set(1, quantity);
                    updateTransactionTime();
                    recalculateTotalPrice();
                }
                if (!subtotal.equals(null)){
                    temp.set(2, subtotal);
                    updateTransactionTime();
                    recalculateTotalPrice();
                }
            }
        }
    }

    public void removeBillItem(Integer id){
        // Delete item based on the product id
        if (!isFixedBill){
            Integer idx = 0;
            for (List<Object> l: receipt) {
                if (l.get(0).equals(id)){
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
        for (List<Object> tuple: receipt) {
            if (tuple.get(0).equals(id)){
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
        Date date = new Date();
        Integer d = date.getDate();
        Integer m = date.getMonth();
        Integer y = date.getYear() + 1900;
        Integer hh = date.getHours();
        Integer mm = date.getMinutes();
        Integer ss = date.getSeconds();
        String time = d+"/"+m+"/"+y+"/"+hh+":"+mm+":"+ss;
        transactionTime = time;
    }

    public void recalculateTotalPrice(){
        Double res = 0.0;
        for ( List<Object> tuple: receipt) {
            res += (Double) tuple.get(2);
        }
        this.totalPrice = res;
    }

}
