package program.adapter;

// Java program to read JSON from a file

import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;
import program.entities.*;

public class Example
{
    public static void main(String[] args) throws Exception {
        ClientContainer cc = new ClientContainer();
        InventoryContainer ic = new InventoryContainer();
        TransactionContainer tc = new TransactionContainer();
        ClientAdapter cd = new ClientAdapter();
        InventoryAdapter id = new InventoryAdapter();
        TransactionAdapter td = new TransactionAdapter();

//        System.out.println("Loading client data...");
//        cd.readDataJSON(cc);
        System.out.println("Loading inventory data...");
        id.readDataJSON(ic);
//        System.out.println("Loading transaction data...");
//        td.readDataJSON(tc);

//        Test Entities Creation
//        Bill b = new Bill();
//        Customer c = new Customer(1);
//        Member m = new Member(2, "", "", 0.0, true);
//        VIP v = new VIP(3, "", "", 0.0, true);
//        Product p = new Product();

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

        for ( Product p : ic.getBuffer()) {
            p.display();
        }
//        for (Bill b : tc.getBuffer()){
//            b.display();
//        }

//        Add item
        Product pr = new Product(16, 10, "Vanilla Milkshake", 24000.0, 18000.0, "Milk", "");
        ic.addProduct(pr);
//        Remove Item
//        ic.removeProduct(16);
//        Get Item
//        Product l = ic.getProductById(15);
//        l.setStock(20);

        for (Product p : ic.getBuffer()){
            p.display();
        }

//        Write Data
//        cd.writeDataJSON(cc);
        id.writeDataJSON(ic);
//        td.writeDataJSON(tc);

    }
}