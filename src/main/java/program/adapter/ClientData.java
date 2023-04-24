package program.adapter;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import lombok.*;
import program.entities.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClientData {
    private List<? super Client> buffer;
    private int amount;

    public void reset(){
        buffer = new ArrayList <>();
        amount = 0;
    }

    public void readDataJSON() {
        String filePath = new java.io.File("").getAbsolutePath();
        var clientDatabasePath = filePath + "\\src\\main\\database\\json\\Client.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(clientDatabasePath))
        {
            reset();
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
        String status = (String) c.get("status");
        if (status.equals("customer")){

            Integer id = ((Long) c.get("id")).intValue();

            // Get Transaction List
            JSONArray arr = (JSONArray) c.get("transactionHistory");
            List<Integer> arrTransaction = new ArrayList<>();
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

            Customer cr = new Customer(id);
            cr.setTransactionHistory(arrTransaction);
            buffer.add(cr);

        }
        else if (status.equals("member")){
            Integer id = ((Long) c.get("id")).intValue();
            String name = (String) c.get("name");
            String phoneNumber = (String) c.get("phoneNumber");
            Double point = ((Long) c.get("point")).doubleValue();
            Boolean active = ((Boolean) c.get("active")).booleanValue();

            // Get Transaction List
            JSONArray arr = (JSONArray) c.get("transactionHistory");
            List<Integer> arrTransaction = new ArrayList<>();
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

            Member m = new Member(id, name, phoneNumber, point, active);
            m.setTransactionHistory(arrTransaction);
            buffer.add(m);


        }
        else if (status.equals("vip")){
            Integer id = ((Long) c.get("id")).intValue();
            String name = (String) c.get("name");
            String phoneNumber = (String) c.get("phoneNumber");
            Double point = ((Long) c.get("point")).doubleValue();
            Boolean active = ((Boolean) c.get("active")).booleanValue();

            // Get Transaction List
            JSONArray arr = (JSONArray) c.get("transactionHistory");
            List<Integer> arrTransaction = new ArrayList<>();
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

            VIP v = new VIP(id, name, phoneNumber, point, active);
            v.setTransactionHistory(arrTransaction);
            buffer.add(v);
        }
        amount++;

    }



}
