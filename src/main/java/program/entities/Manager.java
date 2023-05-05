package program.entities;

import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;

import lombok.*;
@Setter
@Getter
public class Manager {
    private static Manager instance = null;
    private ClientContainer clientContainer;
    private InventoryContainer inventoryContainer;
    private TransactionContainer transactionContainer;

    private Manager(){
        clientContainer = new ClientContainer();
        inventoryContainer = new InventoryContainer();
        transactionContainer = new TransactionContainer();
    }

    public static Manager getInstance(){
        if (instance == null){
            instance = new Manager();
        }
        return instance;
    }


}
