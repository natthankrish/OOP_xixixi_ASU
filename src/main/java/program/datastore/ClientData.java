package program.datastore;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClientData {
    private int amount;

    public void readDataJSON() {
        String filePath = new java.io.File("").getAbsolutePath();
        var clientDatabasePath = filePath + "\\src\\main\\database\\json\\Client.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(clientDatabasePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray clientList = (JSONArray) obj;

            //Iterate over client array
            clientList.forEach( client -> parseClientObject( (JSONObject) client ) );

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

    public void parseClientObject(JSONObject c) {
        // Get every element
        Long id = (Long) c.get("id");
        String status = (String) c.get("status");
        String name = (String) c.get("name");
        String phoneNumber = (String) c.get("phoneNumber");
        Long point = (Long) c.get("point");
        Boolean active = (Boolean) c.get("active");

        // Get Transaction List
        JSONArray arr = (JSONArray) c.get("transactionHistory");
        List<Long> arrTransaction = new ArrayList<>();
        arr.forEach( i -> arrTransaction.add( (Long) i));

//        System.out.println(id + status + name + phoneNumber + point + active);
//        System.out.println(arr);
        if (status.equals("customer")){
            System.out.println("An unnamed customer with id: "+ id+" has beed added.");
        } else {
            System.out.println("A "+ status +" with id: "+ id +", named "+name+", has been added.");
        }
    }

}
