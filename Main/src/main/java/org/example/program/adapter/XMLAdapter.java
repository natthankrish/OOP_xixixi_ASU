package org.example.program.adapter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.example.program.containers.ClientContainer;
import org.example.program.containers.InventoryContainer;
import org.example.program.containers.TransactionContainer;
import org.example.program.entities.Bill;
import org.example.program.entities.Product;
import org.example.program.entities.clients.Client;

import java.io.File;

public class XMLAdapter implements Adapter{

    private static final String clientDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\xml\\Client.xml";
    private static final String inventoryDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\xml\\Inventory.xml";
    private static final String transactionDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\xml\\Transaction.xml";

    public void readDataClient(ClientContainer cc){


        try
        {
            File xmlFile = new File(clientDatabasePath);
            JAXBContext context = JAXBContext.newInstance(ClientContainer.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();

            ClientContainer temp = (ClientContainer) jaxbUnmarshaller.unmarshal(xmlFile);

            cc.setAmount(temp.getAmount());
            cc.setBuffer(temp.getBuffer());
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

    }
    public void writeDataClient(ClientContainer cc){

        try {
            for (Client c: cc.getBuffer()) {
                c.display();
            }
            JAXBContext context = JAXBContext.newInstance(ClientContainer.class, Client.class);
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
        try
        {
            File xmlFile = new File(inventoryDatabasePath);
            JAXBContext context = JAXBContext.newInstance(InventoryContainer.class, Product.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();

            InventoryContainer temp = (InventoryContainer) jaxbUnmarshaller.unmarshal(xmlFile);

            ic.setAmount(temp.getAmount());
            ic.setBuffer(temp.getBuffer());
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
    public void writeDataInventory(InventoryContainer ic){
        try {
            JAXBContext context = JAXBContext.newInstance(InventoryContainer.class, Product.class);
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
        try
        {
            File xmlFile = new File(transactionDatabasePath);
            JAXBContext context = JAXBContext.newInstance(TransactionContainer.class, Bill.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();

            TransactionContainer temp = (TransactionContainer) jaxbUnmarshaller.unmarshal(xmlFile);

            tc.setAmount(temp.getAmount());
            tc.setBuffer(temp.getBuffer());
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
    public void writeDataTransaction(TransactionContainer tc){
        try {
            JAXBContext context = JAXBContext.newInstance(TransactionContainer.class, Bill.class);
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

