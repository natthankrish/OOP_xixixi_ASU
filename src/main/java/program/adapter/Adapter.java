package program.adapter;

import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;

public interface Adapter {

    public void readDataClient(ClientContainer cc);
    public void writeDataClient(ClientContainer cc);

    public void readDataInventory(InventoryContainer ic);
    public void writeDataInventory(InventoryContainer ic);

    public void readDataTransaction(TransactionContainer tc);
    public void writeDataTransaction(TransactionContainer tc);
}
