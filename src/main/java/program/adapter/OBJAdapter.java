package program.adapter;

import program.containers.ClientContainer;
import program.containers.InventoryContainer;
import program.containers.TransactionContainer;

public class OBJAdapter implements Adapter{

    private static final String clientDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\obj\\Client.obj";
    private static final String inventoryDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\obj\\Inventory.obj";
    private static final String transactionDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\obj\\Transaction.obj";

    public void readDataClient(ClientContainer cc){

    }
    public void writeDataClient(ClientContainer cc){

    }

    public void readDataInventory(InventoryContainer ic){

    }
    public void writeDataInventory(InventoryContainer ic){

    }

    public void readDataTransaction(TransactionContainer tc){

    }
    public void writeDataTransaction(TransactionContainer tc){

    }


}
