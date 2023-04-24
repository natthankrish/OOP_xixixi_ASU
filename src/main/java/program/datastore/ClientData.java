package program.datastore;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import lombok.*;
import program.entities.Product;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClientData {
    private List<Product> buffer;
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
        Integer id = ((Long) c.get("id")).intValue();
        String status = (String) c.get("status");
        String name = (String) c.get("name");
        String phoneNumber = (String) c.get("phoneNumber");
        Double point = ((Long) c.get("point")).doubleValue();
        Boolean active = ((Boolean) c.get("active")).booleanValue();

        // Get Transaction List
        JSONArray arr = (JSONArray) c.get("transactionHistory");
        List<Integer> arrTransaction = new ArrayList<>();
        arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

//        System.out.println(id + status + name + phoneNumber + point + active);
//        System.out.println(arr);
        if (status.equals("customer")){
            System.out.println("An unnamed customer with id: "+ id+" has beed added.");
        } else {
            System.out.println("A "+ status +" with id: "+ id +", named "+name+", has been added.");
        }
    }

    public Product getProductById(Integer id){
        Integer idx = 0;
        for (Product p: buffer ) {
            Integer tempID = p.getId();
            if (tempID.equals(id)){
                return p;
            }
        }
        return null;
    }

    public void addProduct(Integer id, Integer stock, String name, Double price, Double purchasePrice, String category, String image){
        Product p = new Product(id, stock, name, price, purchasePrice, category, image);
        buffer.add(p);
    }

    public void removeProduct(Integer id) {
        Integer idx = 0;
        for ( Product p : buffer) {
            Integer tempID = p.getId();
            if (tempID.equals(id)){
                buffer.remove(idx);
                break;
            }
            idx++;
        }
    }


}
