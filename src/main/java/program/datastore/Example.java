package program.datastore;

// Java program to read JSON from a file

import program.entities.*;

public class Example
{
    public static void main(String[] args) throws Exception {
        ClientData cd = new ClientData();
        InventoryData id = new InventoryData();
        TransactionData td = new TransactionData();

        System.out.println("Loading client data...");
        cd.readDataJSON();
        System.out.println("Loading inventory data...");
        id.readDataJSON();
        System.out.println("Loading transaction data...");
        td.readDataJSON();

//        Test Entities Creation
//        Bill b = new Bill();
//        Customer c = new Customer(1);
//        Member m = new Member(2, "", "", 0.0, true);
//        VIP v = new VIP(3, "", "", 0.0, true);
//        Product p = new Product();

//        Testing Class Input

        cd.getBuffer().forEach(o -> {
                if (o instanceof VIP){
                    ((VIP) o).display();
                } else if (o instanceof  Member){
                    ((Member) o).display();
                } else {
                    ((Customer) o).display();
                }
        });

        for ( Product p : id.getBuffer()) {
            p.display();
        }
        for (Bill b : td.getBuffer()){
            b.display();
        }


    }
}