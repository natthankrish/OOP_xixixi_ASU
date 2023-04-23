package program.datastore;

// Java program to read JSON from a file

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReadExample
{
    public static void main(String[] args) throws Exception {
        ClientData cd = new ClientData();
        cd.readDataJSON();
    }
}