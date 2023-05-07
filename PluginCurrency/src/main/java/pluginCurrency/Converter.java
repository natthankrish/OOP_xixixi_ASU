package pluginCurrency;

import org.example.program.containers.ClientContainer;
import org.example.program.containers.InventoryContainer;
import org.example.program.containers.Manager;
import org.example.program.containers.TransactionContainer;

import java.util.HashMap;
import java.util.Map;

public class Converter {
    private static Converter instance = null;

    private final Map<String, Double> convertTable;

    private Converter(){
        convertTable = new HashMap<String,Double>() {{
            put("idr", 1.0);
            put("usd", 14674.8);
            put("gbp", 18547.48);
            put("eur", 16173.00);
            put("jpy", 108.84);
            put("cny", 2123.52);
            put("krw", 11.13);
        }};
    }

    public static Converter getInstance(){
        if (instance == null){
            instance = new Converter();
        }
        return instance;
    }

    public Map<String,Double> getTable(){
        return convertTable;
    }

    public Double getMultiplier(String k){
        return convertTable.get(k);
    }
}
