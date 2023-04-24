package program.datastore;

import lombok.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import program.entities.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class InventoryData {
    private List<Product> buffer;
    private int amount;

    public void reset() {
        buffer = new ArrayList<>();
        amount = 0;
    }

    public void readDataJSON() {
        String filePath = new java.io.File("").getAbsolutePath();
        var inventoryDatabasePath = filePath + "\\src\\main\\database\\json\\Inventory.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(inventoryDatabasePath))
        {
            reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray inventoryList = (JSONArray) obj;

            //Iterate over client array
            inventoryList.forEach( product -> parseProductObject( (JSONObject) product ) );

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

    public void parseProductObject(JSONObject p) {
        // Get every element
        Integer id = ((Long) p.get("id")).intValue();
        Integer stock = ((Long) p.get("stock")).intValue();
        String name = (String) p.get("name");
        Double price = (Double) p.get("price");
        Double purchasePrice = (Double) p.get("purchasePrice");
        String category = (String) p.get("category");
        String image = (String) p.get("image");

        Product pr = new Product(id, stock, name, price, purchasePrice, category, image);
        buffer.add(pr);
        amount++;

    }

    // Getter
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
        amount++;
    }

    public void removeProduct(Integer id) {
        Integer idx = 0;
        for ( Product p : buffer) {
            Integer tempID = p.getId();
            if (tempID.equals(id)){
                buffer.remove(idx);
                amount--;
                break;
            }
            idx++;
        }
    }

}
