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
        }
    }

    public void updateBillItem(Integer id, Integer quantity, Double subtotal){
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
                }
                if (!subtotal.equals(null)){
                    temp.set(2, subtotal);
                    updateTransactionTime();
                }
            }
        }
    }

    public void removeBillItem(Integer id){

        if (!isFixedBill){
            int idx = 0;
            for (List<Object> l: receipt) {
                if (l.get(0).equals(id)){
                    receipt.remove(idx);
                    updateTransactionTime();
                    break;
                }
                idx++;
            }

        }
    }

    public void setFixed() {
        this.isFixedBill = true;
    }

    public void updateTransactionTime(){
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

    public void display() {
        System.out.println("ID: "+idBill+", IDClient: "+ idClient);
        for ( List<Object> l : receipt) {
            System.out.println("[ "+ l.get(0)+", "+l.get(1)+", "+l.get(2)+" ]");
        }
        System.out.println("Total price: "+ totalPrice);
        System.out.println("Fix status: "+ isFixedBill +", Time: "+ transactionTime);
    }

}
