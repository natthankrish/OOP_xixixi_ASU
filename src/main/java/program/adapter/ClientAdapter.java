package program.adapter;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import program.container.ClientContainer;
import program.entities.*;



public class ClientAdapter {

    public void readDataJSON(ClientContainer cc) {
        String filePath = new java.io.File("").getAbsolutePath();
        var clientDatabasePath = filePath + "\\src\\main\\database\\json\\Client.json";

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(clientDatabasePath))
        {
            cc.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray clientList = (JSONArray) obj;

            //Iterate over client array
            clientList.forEach( client -> parseClientObjectJSON( cc, (JSONObject) client ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void writeDataJSON(ClientContainer cc) {
        String filePath = new java.io.File("").getAbsolutePath();
        var clientDatabasePath = filePath + "\\src\\main\\database\\json\\Client.json";

        JSONArray clientsArr = new JSONArray();
        for (Object o : cc.getBuffer()) {
            JSONObject clientObj = new JSONObject();
            if (o instanceof Customer){
                Customer cr = (Customer) o;
                clientObj.put("id", cr.getId());
                clientObj.put("status", "customer");
                clientObj.put("name", "");
                clientObj.put("phoneNumber","");
                clientObj.put("point", 0);
                clientObj.put("active", false);
                clientObj.put("transactionHistory", cr.getTransactionHistory());
                System.out.println("customer");
            } else if (o instanceof Member){
                Member mr = (Member) o;
                clientObj.put("id", mr.getId());
                clientObj.put("status", "member");
                clientObj.put("name", mr.getName());
                clientObj.put("phoneNumber",mr.getPhoneNumber());
                clientObj.put("point", mr.getPoint());
                clientObj.put("active", mr.getActive());
                clientObj.put("transactionHistory", mr.getTransactionHistory());
                System.out.println("member");
            } else if (o instanceof VIP){
                VIP vp = (VIP) o;
                clientObj.put("id", vp.getId());
                clientObj.put("status", "vip");
                clientObj.put("name", vp.getName());
                clientObj.put("phoneNumber",vp.getPhoneNumber());
                clientObj.put("point", vp.getPoint());
                clientObj.put("active", vp.getActive());
                clientObj.put("transactionHistory", vp.getTransactionHistory());
                System.out.println("vip");
            }
            clientsArr.add(clientObj);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(clientDatabasePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(clientsArr));
            file.write(gson.toJson(clientsArr));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseClientObjectJSON(ClientContainer cc, JSONObject c) {
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
            cc.getBuffer().add(cr);

        }
        else if (status.equals("member")){
            Integer id = ((Long) c.get("id")).intValue();
            String name = (String) c.get("name");
            String phoneNumber = (String) c.get("phoneNumber");
            Double point = ((Double) c.get("point"));
            Boolean active = ((Boolean) c.get("active")).booleanValue();

            // Get Transaction List
            JSONArray arr = (JSONArray) c.get("transactionHistory");
            List<Integer> arrTransaction = new ArrayList<>();
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

            Member m = new Member(id, name, phoneNumber, point, active);
            m.setTransactionHistory(arrTransaction);
            cc.getBuffer().add(m);

        }
        else if (status.equals("vip")){
            Integer id = ((Long) c.get("id")).intValue();
            String name = (String) c.get("name");
            String phoneNumber = (String) c.get("phoneNumber");
            Double point = ((Double) c.get("point"));
            Boolean active = ((Boolean) c.get("active")).booleanValue();

            // Get Transaction List
            JSONArray arr = (JSONArray) c.get("transactionHistory");
            List<Integer> arrTransaction = new ArrayList<>();
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));

            VIP v = new VIP(id, name, phoneNumber, point, active);
            v.setTransactionHistory(arrTransaction);
            cc.getBuffer().add(v);
        }
        cc.increaseAmount();

    }



}
