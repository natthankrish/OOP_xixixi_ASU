package program.adapter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import program.container.InventoryContainer;
import program.entities.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class InventoryAdapter {

    public void readDataJSON(InventoryContainer ic) {
        String filePath = new java.io.File("").getAbsolutePath();
        var inventoryDatabasePath = filePath + "\\src\\main\\database\\json\\Inventory.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(inventoryDatabasePath))
        {
            ic.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray inventoryList = (JSONArray) obj;

            //Iterate over client array
            inventoryList.forEach( product -> parseProductObjectJSON( ic, (JSONObject) product ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseProductObjectJSON(InventoryContainer ic, JSONObject p) {
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

    public void writeDataJSON(InventoryContainer ic) {
        String filePath = new java.io.File("").getAbsolutePath();
        var inventoryDatabasePath = filePath + "\\src\\main\\database\\json\\Inventory.json";
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


}
