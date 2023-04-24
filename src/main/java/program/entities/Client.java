package program.entities;

import java.util.*;
import lombok.*;
@Getter
@Setter

abstract public class Client {
    private Integer id;
    private List<Integer> transactionHistory;

    // Constructor
    public Client(Integer newID) {
        this.id = newID;
        this.transactionHistory = new ArrayList<>();
    }

    // Methods
    abstract public Double payment(Double n, Double point);
    public void addTransaction(Integer id){
        transactionHistory.add(id);
    }
    public void removeTransaction(Integer id){
        Integer idx = 0;
        for ( Integer tempID: transactionHistory ) {
            if (tempID.equals(id)){
                transactionHistory.remove(idx);
                break;
            }
            idx++;
        }
    }
}
