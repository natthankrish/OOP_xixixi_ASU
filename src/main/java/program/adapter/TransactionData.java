package program.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import program.entities.Bill;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TransactionData {
    private List<Bill> buffer;
    private int amount;

    public void reset(){
        buffer = new ArrayList<>();
        amount = 0;
    }

    public void readDataJSON() {
        String filePath = new java.io.File("").getAbsolutePath();
        var transactionDatabasePath = filePath + "\\src\\main\\database\\json\\Transaction.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(transactionDatabasePath))
        {
            reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray transactionList = (JSONArray) obj;

            //Iterate over client array
            transactionList.forEach( bill -> parseBillObject( (JSONObject) bill ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void writeDataJSON() {
        String filePath = new java.io.File("").getAbsolutePath();
        var transactionDatabasePath = filePath + "\\src\\main\\database\\json\\Transaction.json";

        JSONArray transactionsArr = new JSONArray();
        for (Bill b : buffer) {
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

    public void parseBillObject(JSONObject b) {
        // Get every element
        int idBill = ((Long) b.get("idBill")).intValue();
        int idClient = ((Long) b.get("idClient")).intValue();

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
        buffer.add(bi);
        amount++;
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


}
