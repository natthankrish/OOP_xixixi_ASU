package program.adapter;

import lombok.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import program.entities.Bill;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
