package program.entities;

import lombok.*;
@Getter
@Setter

abstract public class RegisteredCustomer extends Client{
    private String name;
    private String phoneNumber;
    private Double point;
    private Boolean active;

    // Constructor
    public RegisteredCustomer(Integer newID, String name, String phoneNumber, Double point, Boolean active){
        super(newID);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.active = active;
    }

    @Override
    abstract public Double payment(Double n, Double point);

    public void addPoint(Double p){
        this.point += p;
    }

    public void subtractPoint(Double p){
        this.point -= p;
    }
}
