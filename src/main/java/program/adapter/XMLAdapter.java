package program.adapter;

import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;

public class XMLAdapter implements Adapter{

    private static final String clientDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\xml\\Client.xml";
    private static final String inventoryDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\xml\\Inventory.xml";
    private static final String transactionDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\xml\\Transaction.xml";

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
