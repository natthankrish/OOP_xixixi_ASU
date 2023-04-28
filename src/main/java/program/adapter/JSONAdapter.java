package program.adapter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import program.container.ClientContainer;
import program.container.InventoryContainer;
import program.container.TransactionContainer;
import program.entities.*;


public class JSONAdapter implements Adapter{
    private static final String clientDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\json\\Client.json";
    private static final String inventoryDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\json\\Inventory.json";
    private static final String transactionDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\database\\json\\Transaction.json";

    // CLIENT DATA
    public void readDataClient(ClientContainer cc) {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(clientDatabasePath))
        {
            cc.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray clientList = (JSONArray) obj;

            //Iterate over client array
            clientList.forEach( client -> parseClientObject( cc, (JSONObject) client ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void writeDataClient(ClientContainer cc) {

        JSONArray clientsArr = new JSONArray();
        for (Object o : cc.getBuffer()) {
            JSONObject clientObj = new JSONObject();
            if (o instanceof Customer){
                Customer cr = (Customer) o;
                clientObj.put("id", cr.getId());
                clientObj.put("status", "customer");
                clientObj.put("name", "");
                clientObj.put("phoneNumber","");
                clientObj.put("point", 0);
                clientObj.put("active", false);
                clientObj.put("transactionHistory", cr.getTransactionHistory());
                System.out.println("customer");
            } else if (o instanceof Member){
                Member mr = (Member) o;
                clientObj.put("id", mr.getId());
                clientObj.put("status", "member");
                clientObj.put("name", mr.getName());
                clientObj.put("phoneNumber",mr.getPhoneNumber());
                clientObj.put("point", mr.getPoint());
                clientObj.put("active", mr.getActive());
                clientObj.put("transactionHistory", mr.getTransactionHistory());
                System.out.println("member");
            } else if (o instanceof VIP){
                VIP vp = (VIP) o;
                clientObj.put("id", vp.getId());
                clientObj.put("status", "vip");
                clientObj.put("name", vp.getName());
                clientObj.put("phoneNumber",vp.getPhoneNumber());
                clientObj.put("point", vp.getPoint());
                clientObj.put("active", vp.getActive());
                clientObj.put("transactionHistory", vp.getTransactionHistory());
                System.out.println("vip");
            }
            clientsArr.add(clientObj);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(clientDatabasePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(clientsArr));
            file.write(gson.toJson(clientsArr));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseClientObject(ClientContainer cc, JSONObject c) {
        // Get every element
        String status = (String) c.get("status");
        if (status.equals("customer")){

            Integer id = ((Long) c.get("id")).intValue();

            // Get Transaction List
            JSONArray arr = (JSONArray) c.get("transactionHistory");
            List<Integer> arrTransaction = new ArrayList<>();
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

            Customer cr = new Customer(id);
            cr.setTransactionHistory(arrTransaction);
            cc.getBuffer().add(cr);

        }
        else if (status.equals("member")){
            Integer id = ((Long) c.get("id")).intValue();
            String name = (String) c.get("name");
            String phoneNumber = (String) c.get("phoneNumber");
            Double point = ((Double) c.get("point"));
            Boolean active = ((Boolean) c.get("active")).booleanValue();

            // Get Transaction List
            JSONArray arr = (JSONArray) c.get("transactionHistory");
            List<Integer> arrTransaction = new ArrayList<>();
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

            Member m = new Member(id, name, phoneNumber, point, active);
            m.setTransactionHistory(arrTransaction);
            cc.getBuffer().add(m);

        }
        else if (status.equals("vip")){
            Integer id = ((Long) c.get("id")).intValue();
            String name = (String) c.get("name");
            String phoneNumber = (String) c.get("phoneNumber");
            Double point = ((Double) c.get("point"));
            Boolean active = ((Boolean) c.get("active")).booleanValue();

            // Get Transaction List
            JSONArray arr = (JSONArray) c.get("transactionHistory");
            List<Integer> arrTransaction = new ArrayList<>();
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

            VIP v = new VIP(id, name, phoneNumber, point, active);
            v.setTransactionHistory(arrTransaction);
            cc.getBuffer().add(v);
        }
        cc.increaseAmount();

    }

    // INVENTORY DATA
    public void readDataInventory(InventoryContainer ic) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(inventoryDatabasePath))
        {
            ic.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray inventoryList = (JSONArray) obj;

            //Iterate over client array
            inventoryList.forEach( product -> parseProductObject( ic, (JSONObject) product ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseProductObject(InventoryContainer ic, JSONObject p) {
        // Get every element
        Integer id = ((Long) p.get("id")).intValue();
        Integer stock = ((Long) p.get("stock")).intValue();
        String name = (String) p.get("name");
        Double price = (Double) p.get("price");
        Double purchasePrice = (Double) p.get("purchasePrice");
        String category = (String) p.get("category");
        String image = (String) p.get("image");

        Product pr = new Product(id, stock, name, price, purchasePrice, category, image);
        ic.getBuffer().add(pr);
        ic.increaseAmount();

    }

    public void writeDataInventory(InventoryContainer ic) {
        // Make array
        JSONArray productsArr = new JSONArray();
        for (Product p : ic.getBuffer()) {
            JSONObject productObj = new JSONObject();
            productObj.put("id", p.getId());
            productObj.put("stock", p.getStock());
            productObj.put("name", p.getName());
            productObj.put("price", p.getPrice());
            productObj.put("purchasePrice", p.getPurchasePrice());
            productObj.put("category", p.getCategory());
            productObj.put("image", p.getImage());

            productsArr.add(productObj);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(inventoryDatabasePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(productsArr));
            file.write(gson.toJson(productsArr));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TRANSACTION DATA
    public void readDataTransaction(TransactionContainer tc) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(transactionDatabasePath))
        {
            tc.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray transactionList = (JSONArray) obj;

            //Iterate over client array
            transactionList.forEach( bill -> parseBillObject( tc, (JSONObject) bill ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseBillObject(TransactionContainer tc, JSONObject b) {
        // Get every element
        Integer idBill = ((Long) b.get("idBill")).intValue();
        Integer idClient = ((Long) b.get("idClient")).intValue();

        List<List<Object>> receipt = new ArrayList<>();
        JSONArray arr = (JSONArray) b.get("receipt");
        for (Object o: arr) {
            List<Object> tuple = getParseReceiptArr((JSONObject) o);
            receipt.add(tuple);
        }

        Double totalPrice = (Double) b.get("totalPrice");
        Boolean isFixed = (Boolean) b.get("isFixed");
        String transactionTime = (String) b.get("transactionTime");

        Bill bi = new Bill(idBill, idClient, receipt, totalPrice, isFixed, transactionTime);
        tc.getBuffer().add(bi);
        tc.increaseAmount();
    }

    public List<Object> getParseReceiptArr(JSONObject o) {
        List<Object> tuple = new ArrayList<>();
        Integer idP = ((Long) o.get("idProduct")).intValue();
        Integer quantity = ((Long) o.get("quantity")).intValue();
        Double subtotal = (Double) o.get("subtotal");
        tuple.add(idP);
        tuple.add(quantity);
        tuple.add(subtotal);
        return tuple;
    }


    public void writeDataTransaction(TransactionContainer tc) {
        JSONArray transactionsArr = new JSONArray();
        for (Bill b : tc.getBuffer()) {
            JSONObject billObj = new JSONObject();
            billObj.put("idBill", b.getIdBill());
            billObj.put("idClient", b.getIdClient());

            JSONArray receiptArr = new JSONArray();
            for (List<Object> r : b.getReceipt()){
                JSONObject rec = new JSONObject();
                rec.put("idProduct", r.get(0));
                rec.put("quantity", r.get(1));
                rec.put("subtotal", r.get(2));
                receiptArr.add(rec);
            }
            billObj.put("receipt", receiptArr);
            billObj.put("totalPrice", b.getTotalPrice());
            billObj.put("isFixed", b.getIsFixedBill());
            billObj.put("transactionTime", b.getTransactionTime());
            transactionsArr.add(billObj);

        }

        //Write JSON file
        try (FileWriter file = new FileWriter(transactionDatabasePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(transactionsArr));
            file.write(gson.toJson(transactionsArr));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
