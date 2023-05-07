package org.example.program.entities.clients;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.*;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Client")
@XmlAccessorType (XmlAccessType.FIELD)
public class Client implements Serializable {
    private Integer id;
    private List<Integer> transactionHistory;
    @XmlJavaTypeAdapter(ClientXMLAdapter.class)
    private ClientType type;

//    public Client(){
//        id = 0;
//        transactionHistory = new ArrayList<>();
//        type = new Customer();
//    }

    // Methods
    public Double pay(Double n, Double point){
        return type.payment(n, point);
    }

    public void display(){
        System.out.println("ID: "+id);
        System.out.println(transactionHistory);
        type.display();
    }
    public void addTransaction(Integer id){
        if (transactionHistory == null){
            // Handle case if equal null, because some XML data reading can result null value
            transactionHistory = new ArrayList<>();
        }
        transactionHistory.add(id);
    }
    public void removeTransaction(Integer id){
        int idx = 0;
        for ( Integer tempID: transactionHistory ) {
            if (tempID.equals(id)){
                transactionHistory.remove(idx);
                break;
            }
            idx++;
        }
    }

    public boolean isTransactionIDInTransaction(Integer id){
        for (Integer tempID: transactionHistory) {
            if (tempID.equals(id)){
                return true;
            }
        }
        return false;
    }

    public void makeClientACustomer(){
        setType(new Customer());
    }

    public void makeClientAMember(String name, String phoneNumber, Double point, Boolean active){
        setType(new Member(name, phoneNumber, point, active));
    }

    public void makeClientAVIP(String name, String phoneNumber, Double point, Boolean active){
        setType(new VIP(name, phoneNumber, point, active));
    }

    // Manual Setter and Getter
    public void setName(String name){
        if (this.type instanceof Member mr){ mr.setName(name);}
        else if (this.type instanceof VIP vp){ vp.setName(name);}
    }
    public void setPhoneNumber(String num){
        if (this.type instanceof Member mr){ mr.setPhoneNumber(num);}
        else if (this.type instanceof VIP vp){ vp.setPhoneNumber(num);}
    }
    public void setPoint(Double point){
        if (this.type instanceof Member mr){ mr.setPoint(point);}
        else if (this.type instanceof VIP vp){ vp.setPoint(point);}
    }
    public void setInactive(){
        this.type.deactivate();
    }
    public void setActive(){
        this.type.activate();
    }
    public String getName(){
        if (this.type instanceof Member mr){ return mr.getName();}
        else if (this.type instanceof VIP vp){ return vp.getName();}
        return null;
    }
    public String getPhoneNumber(){
        if (this.type instanceof Member mr){ return mr.getPhoneNumber();}
        else if (this.type instanceof VIP vp){ return vp.getPhoneNumber();}
        return null;
    }

    public Double getPoint(){
        if (this.type instanceof Member mr){ return mr.getPoint();}
        else if (this.type instanceof VIP vp){ return vp.getPoint();}
        return null;
    }

    public Boolean getActiveStatus(){
        if (this.type instanceof Member mr){ return mr.getActive();}
        else if (this.type instanceof VIP vp){ return vp.getActive();}
        return null;
    }

}


