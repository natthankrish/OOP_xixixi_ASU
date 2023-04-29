package program.adapter;

// Java program to read JSON from a file

import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;
import program.entities.*;

public class Example
{
    public static void main(String[] args) throws Exception {
//        ClientContainer cc = new ClientContainer();
//        InventoryContainer ic = new InventoryContainer();
        TransactionContainer tc = new TransactionContainer();
        JSONAdapter ja = new JSONAdapter();

//        System.out.println("Loading client data...");
//        ja.readDataClient(cc);
//        System.out.println("Loading inventory data...");
//        ja.readDataInventory(ic);
        System.out.println("Loading transaction data...");
        ja.readDataTransaction(tc);


//        Testing Class Input

//        cc.getBuffer().forEach(o -> {
//                if (o instanceof VIP){
//                    ((VIP) o).display();
//                } else if (o instanceof  Member){
//                    ((Member) o).display();
//                } else {
//                    ((Customer) o).display();
//                }
//        });

//        for ( Product p : ic.getBuffer()) {
//            p.display();
//        }
        for (Bill b : tc.getBuffer()){
            b.display();
        }

//        Add item
//        Product pr = new Product(ic.getMaxID()+1, 10, "Strawberry Milkshake", 24000.0, 18000.0, "Milk", "", true);
//        ic.addProduct(pr);
//        Remove Item
//        ic.removeProduct(16);
//        Get Item
//        Product l = ic.getProductById(15);
//        l.setStock(20);

//        for (Product p : ic.getBuffer()){
//            p.display();
//        }

//        Write Data
//        ja.writeDataClient(cc);
//        ja.writeDataInventory(ic);
        ja.writeDataTransaction(tc);

    }
}