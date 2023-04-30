package program.adapter;

// Java program to read JSON from a file

import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;
import program.entities.*;

public class Example
{
    public static void main(String[] args) throws Exception {

        // CONTAINER
        ClientContainer cc = new ClientContainer();
        InventoryContainer ic = new InventoryContainer();
        TransactionContainer tc = new TransactionContainer();

        // ADAPTER
        JSONAdapter ja = new JSONAdapter();
        XMLAdapter xa = new XMLAdapter();

        // READ DATA
        System.out.println("Loading client data...");
//        ja.readDataClient(cc);
        xa.readDataClient(cc);
        System.out.println("Loading inventory data...");
//        ja.readDataInventory(ic);
        xa.readDataInventory(ic);
        System.out.println("Loading transaction data...");
//        ja.readDataTransaction(tc);
        xa.readDataTransaction(tc);

        // DISPLAY TO TEST DATA
//        cc.getBuffer().forEach(o -> {
//                if (o instanceof VIP){
//                    ((VIP) o).display();
//                } else if (o instanceof  Member){
//                    ((Member) o).display();
//                } else {
//                    ((Customer) o).display();
//                }
//        });
//        for (Bill b : tc.getBuffer()){
//            b.display();
//        }
//        for ( Product p : ic.getBuffer()) {
//            p.display();
//        }


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

        // WRITE DATA
        System.out.println("Writing client data...");
//        ja.writeDataClient(cc);
        xa.writeDataClient(cc);
        System.out.println("Writing inventory data...");
        ja.writeDataInventory(ic);
        xa.writeDataInventory(ic);
        System.out.println("Writing transaction data...");
//        ja.writeDataTransaction(tc);
        xa.writeDataTransaction(tc);

    }
}