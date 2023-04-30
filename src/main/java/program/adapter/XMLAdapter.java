package program.adapter;

import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;
import program.entities.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XMLAdapter implements Adapter{

    private static final String clientDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\xml\\Client.xml";
    private static final String inventoryDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\xml\\Inventory.xml";
    private static final String transactionDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\xml\\Transaction.xml";

    public void readDataClient(ClientContainer cc){



    }
    public void writeDataClient(ClientContainer cc){

        try {
            JAXBContext context = JAXBContext.newInstance(ClientContainer.class, Customer.class, Member.class, VIP.class);
            Marshaller marshaller = context.createMarshaller();

            //Required formatting
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Create file
            File xmlFile = new File(clientDatabasePath);
            //Write XML to StringWriter
            marshaller.marshal(cc, xmlFile);
        }
        catch ( JAXBException e) {
            e.printStackTrace();
        }
    }

    public void readDataInventory(InventoryContainer ic){
        
    }
    public void writeDataInventory(InventoryContainer ic){
        try {
            JAXBContext context = JAXBContext.newInstance(InventoryContainer.class);
            Marshaller marshaller = context.createMarshaller();
            //Required formatting
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Create file
            File xmlFile = new File(inventoryDatabasePath);
            //Write XML to StringWriter
            marshaller.marshal(ic, xmlFile);
        }
        catch ( JAXBException e) {
            e.printStackTrace();
        }
    }

    public void readDataTransaction(TransactionContainer tc){
        
    }
    public void writeDataTransaction(TransactionContainer tc){
        try {
            JAXBContext context = JAXBContext.newInstance(TransactionContainer.class, Bill.class, ReceiptInfo.class, Time.class);
            Marshaller marshaller = context.createMarshaller();
            //Required formatting
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Create file
            File xmlFile = new File(transactionDatabasePath);
            //Write XML to StringWriter
            marshaller.marshal(tc, xmlFile);
        }
        catch ( JAXBException e) {
            e.printStackTrace();
        }
    }
}

