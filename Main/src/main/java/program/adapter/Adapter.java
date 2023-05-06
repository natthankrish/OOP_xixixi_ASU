package program.adapter;

import program.containers.ClientContainer;
import program.containers.InventoryContainer;
import program.containers.TransactionContainer;

public interface Adapter {

    public void readDataClient(ClientContainer cc);
    public void writeDataClient(ClientContainer cc);

    public void readDataInventory(InventoryContainer ic);
    public void writeDataInventory(InventoryContainer ic);

    public void readDataTransaction(TransactionContainer tc);
    public void writeDataTransaction(TransactionContainer tc);
}
