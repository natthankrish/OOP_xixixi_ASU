package program.entities;

import lombok.*;
@Getter
@Setter

public class Customer extends Client{

    // Constructor
    public Customer(Integer newID) {
        super(newID);
    }

    // Methods
    @Override
    public Double payment(Double n, Double point) {
        return n;
    }

    public void display(){
        System.out.println("Customer");
        System.out.println("ID: "+id);
        System.out.println(transactionHistory);
    }

}
