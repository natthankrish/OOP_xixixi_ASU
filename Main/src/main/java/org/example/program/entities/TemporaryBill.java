//package program.entities;
//
//import java.util.*;
//import lombok.*;
//
//import jakarta.xml.bind.annotation.XmlAccessType;
//import jakarta.xml.bind.annotation.XmlAccessorType;
//import jakarta.xml.bind.annotation.XmlRootElement;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@XmlRootElement(name = "TemporayBill")
//@XmlAccessorType(XmlAccessType.FIELD)
//
//public class TemporaryBill extends Bill{
//
//    public boolean isProductIDInReceipt(int id){
//        // Check whether a product with certain ID is already in the receipt
//        for (ReceiptInfo i: receipt) {
//            if (i.getProductID().equals(id)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void updateTransactionTime(){
//        // updating transaction time everytime the receipt is changed
//        transactionTime.updateCurrentTime();
//    }
//
//    public void recalculateTotalPrice(){
//        Double res = 0.0;
//        for ( ReceiptInfo i: receipt) {
//            res += (Double) i.getSubtotal();
//        }
//        this.totalPrice = res;
//    }
//
//}
