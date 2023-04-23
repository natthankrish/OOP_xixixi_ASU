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
        Long idBill = (Long) b.get("idBill");
        Long idClient = (Long) b.get("idClient");

        List<List<Long>> receipt = new ArrayList<>();
        JSONArray arr = (JSONArray) b.get("receipt");

        Long totalPrice = (Long) b.get("totalPrice");
        Long discount = (Long) b.get("discount");
        Boolean isFixed = (Boolean) b.get("isFixed");
        String transactionTime = (String) b.get("transactionTime");



//        System.out.println(id + stock + name + price + purchasePrice + category + image);

//        System.out.println("A product with id: "+ id +", called "+ name +", has been added to "+ category +" category.");
    }


}
