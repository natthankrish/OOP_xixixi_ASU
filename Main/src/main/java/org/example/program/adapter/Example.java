package org.example.program.adapter;

// Java program to read JSON from a file

import org.example.program.containers.Manager;
import org.example.program.entities.clients.Client;
import org.example.program.entities.Product;
import org.example.program.entities.Bill;

public class Example
{
    public static void main(String[] args) throws Exception {

        Manager manager = Manager.getInstance();

        // ADAPTER
        JSONAdapter ja = new JSONAdapter();
        XMLAdapter xa = new XMLAdapter();
        OBJAdapter oa = new OBJAdapter();

        // READ DATA
        System.out.println("Loading client data...");
//        ja.readDataClient(manager.getClientContainer());
//        xa.readDataClient(manager.getClientContainer());
        oa.readDataClient(manager.getClientContainer());
        System.out.println("Loading inventory data...");
//        ja.readDataInventory(manager.getInventoryContainer());
//        xa.readDataInventory(manager.getInventoryContainer());
        oa.readDataInventory(manager.getInventoryContainer());
        System.out.println("Loading transaction data...");
//        ja.readDataTransaction(manager.getTransactionContainer());
//        xa.readDataTransaction(manager.getTransactionContainer());
        oa.readDataTransaction(manager.getTransactionContainer());

        // DISPLAY TO TEST DATA
        for (Client c : manager.getClientContainer().getBuffer()){
            c.display();
        }
        for (Bill b : manager.getTransactionContainer().getBuffer()){
            b.display();
        }
        for ( Product p : manager.getInventoryContainer().getBuffer()) {
            p.display();
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

        // WRITE DATA
        System.out.println("Writing client data...");
        ja.writeDataClient(manager.getClientContainer());
        xa.writeDataClient(manager.getClientContainer());
        oa.writeDataClient(manager.getClientContainer());
        System.out.println("Writing inventory data...");
        ja.writeDataInventory(manager.getInventoryContainer());
        xa.writeDataInventory(manager.getInventoryContainer());
        oa.writeDataInventory(manager.getInventoryContainer());
        System.out.println("Writing transaction data...");
        ja.writeDataTransaction(manager.getTransactionContainer());
        xa.writeDataTransaction(manager.getTransactionContainer());
        oa.writeDataTransaction(manager.getTransactionContainer());

    }
}