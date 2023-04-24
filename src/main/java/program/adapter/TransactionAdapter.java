package program.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import program.container.TransactionContainer;
import program.entities.Bill;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class TransactionAdapter {

    // JSON ADAPTER
    public void readDataJSON(TransactionContainer tc) {
        String filePath = new java.io.File("").getAbsolutePath();
        var transactionDatabasePath = filePath + "\\src\\main\\database\\json\\Transaction.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(transactionDatabasePath))
        {
            tc.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray transactionList = (JSONArray) obj;

            //Iterate over client array
            transactionList.forEach( bill -> parseBillObjectJSON( tc, (JSONObject) bill ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseBillObjectJSON(TransactionContainer tc, JSONObject b) {
        // Get every element
        Integer idBill = ((Long) b.get("idBill")).intValue();
        Integer idClient = ((Long) b.get("idClient")).intValue();

        List<List<Object>> receipt = new ArrayList<>();
        JSONArray arr = (JSONArray) b.get("receipt");
        for (Object o: arr) {
            List<Object> tuple = getParseReceiptArrJSON((JSONObject) o);
            receipt.add(tuple);
        }

        Double totalPrice = (Double) b.get("totalPrice");
        Boolean isFixed = (Boolean) b.get("isFixed");
        String transactionTime = (String) b.get("transactionTime");

        Bill bi = new Bill(idBill, idClient, receipt, totalPrice, isFixed, transactionTime);
        tc.getBuffer().add(bi);
        tc.increaseAmount();
    }

    public List<Object> getParseReceiptArrJSON(JSONObject o) {
        List<Object> tuple = new ArrayList<>();
        Integer idP = ((Long) o.get("idProduct")).intValue();
        Integer quantity = ((Long) o.get("quantity")).intValue();
        Double subtotal = (Double) o.get("subtotal");
        tuple.add(idP);
        tuple.add(quantity);
        tuple.add(subtotal);
        return tuple;
    }


    public void writeDataJSON(TransactionContainer tc) {
        String filePath = new java.io.File("").getAbsolutePath();
        var transactionDatabasePath = filePath + "\\src\\main\\database\\json\\Transaction.json";

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
