package program.datastore;

import lombok.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TransactionData {
    private int amount;

    public void readDataJSON() {
        String filePath = new java.io.File("").getAbsolutePath();
        var transactionDatabasePath = filePath + "\\src\\main\\database\\json\\Transaction.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(transactionDatabasePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray transactionList = (JSONArray) obj;

            //Iterate over client array
            transactionList.forEach( bill -> parseClientObject( (JSONObject) bill ));

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

    public void parseClientObject(JSONObject b) {
        // Get every element
        int idBill = ((Long) b.get("idBill")).intValue();
        int idClient = ((Long) b.get("idClient")).intValue();

        List<List<Object>> receipt = new ArrayList<>();
        JSONArray arr = (JSONArray) b.get("receipt");
        arr.forEach( o -> receipt.add(getParseReceiptArr((JSONObject) o)));

        Double totalPrice = (Double) b.get("totalPrice");
        Double discount = (Double) b.get("discount");
        Boolean isFixed = (Boolean) b.get("isFixed");
        String transactionTime = (String) b.get("transactionTime");

//        System.out.println(" "+ idBill + idClient + totalPrice + discount + isFixed + transactionTime);
//        System.out.println(receipt);

        System.out.println("A transaction with id: "+ idBill +", of client "+ idClient +", has been added with total price of "+totalPrice+".");
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
