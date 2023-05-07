package org.example.program.adapter;

import org.example.program.containers.Manager;
import org.example.program.entities.Bill;
import org.example.program.entities.Product;
import org.example.program.entities.clients.Client;
import org.json.simple.JSONArray;

public class DataDriver {


    public static void main (String[] args){
        Manager m = Manager.getInstance();

        JSONAdapter ja = new JSONAdapter();
        XMLAdapter xa = new XMLAdapter();
        OBJAdapter oa = new OBJAdapter();

//        ja.readDataClient(m.getClientContainer());
//        ja.readDataInventory(m.getInventoryContainer());
//        ja.readDataTransaction(m.getTransactionContainer());

//        xa.readDataClient(m.getClientContainer());
//        xa.readDataInventory(m.getInventoryContainer());
//        xa.readDataTransaction(m.getTransactionContainer());

        oa.readDataClient(m.getClientContainer());
        oa.readDataInventory(m.getInventoryContainer());
        oa.readDataTransaction(m.getTransactionContainer());

        for (Client c : m.getClientContainer().getBuffer()){
            c.display();
        }

        for (Product p : m.getInventoryContainer().getBuffer()){
            p.display();
        }

        for (Bill b : m.getTransactionContainer().getBuffer()){
            b.display();
        }


//        ja.writeDataClient(m.getClientContainer());
//        ja.writeDataInventory(m.getInventoryContainer());
//        ja.writeDataTransaction(m.getTransactionContainer());

//        xa.writeDataClient(m.getClientContainer());
//        xa.writeDataInventory(m.getInventoryContainer());
//        xa.writeDataTransaction(m.getTransactionContainer());
//
        oa.writeDataClient(m.getClientContainer());
        oa.writeDataInventory(m.getInventoryContainer());
        oa.writeDataTransaction(m.getTransactionContainer());
//

    }
}
