package program.datastore;

// Java program to read JSON from a file

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
    }
}