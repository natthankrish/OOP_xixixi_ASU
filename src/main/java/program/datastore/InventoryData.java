package program.datastore;

import lombok.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class InventoryData {
    private int amount;

    public void readDataJSON() {
        String filePath = new java.io.File("").getAbsolutePath();
        var inventoryDatabasePath = filePath + "\\src\\main\\database\\json\\Inventory.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(inventoryDatabasePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray inventoryList = (JSONArray) obj;

            //Iterate over client array
            inventoryList.forEach( product -> parseClientObject( (JSONObject) product ) );

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

    public void parseClientObject(JSONObject p) {
        // Get every element
        Long id = (Long) p.get("id");
        Long stock = (Long) p.get("stock");
        String name = (String) p.get("name");
        Long price = (Long) p.get("price");
        Long purchasePrice = (Long) p.get("purchasePrice");
        String category = (String) p.get("category");
        String image = (String) p.get("image");



//        System.out.println(id + stock + name + price + purchasePrice + category + image);

        System.out.println("A product with id: "+ id +", called "+ name +", has been added to "+ category +" category.");
    }
}
