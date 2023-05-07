package org.example.program.adapter;

import org.example.program.containers.ClientContainer;
import org.example.program.containers.InventoryContainer;
import org.example.program.containers.TransactionContainer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class OBJAdapter implements Adapter{

    private static final String clientDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\obj\\Client.obj";
    private static final String inventoryDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\obj\\Inventory.obj";
    private static final String transactionDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\obj\\Transaction.obj";

    public void readDataClient(ClientContainer cc){
        try {
            FileInputStream fileIn = new FileInputStream(clientDatabasePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();
            ClientContainer temp = (ClientContainer) obj;
            cc.setBuffer(temp.getBuffer());
            cc.setAmount(temp.getAmount());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void writeDataClient(ClientContainer cc){
        try {
            FileOutputStream fileOut = new FileOutputStream(clientDatabasePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(cc);
            objectOut.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readDataInventory(InventoryContainer ic){
        try {

            FileInputStream fileIn = new FileInputStream(inventoryDatabasePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();
            InventoryContainer temp = (InventoryContainer) obj;
            ic.setBuffer(temp.getBuffer());
            ic.setAmount(temp.getAmount());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void writeDataInventory(InventoryContainer ic){
        try {
            FileOutputStream fileOut = new FileOutputStream(inventoryDatabasePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(ic);
            objectOut.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readDataTransaction(TransactionContainer tc){
        try {
            FileInputStream fileIn = new FileInputStream(transactionDatabasePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();
            TransactionContainer temp = (TransactionContainer) obj;
            tc.setBuffer(temp.getBuffer());
            tc.setAmount(temp.getAmount());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void writeDataTransaction(TransactionContainer tc){
        try {
            FileOutputStream fileOut = new FileOutputStream(transactionDatabasePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(tc);
            objectOut.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
