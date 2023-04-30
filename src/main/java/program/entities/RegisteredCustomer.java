package program.entities;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "RegisteredCustomer")
@XmlAccessorType(XmlAccessType.FIELD)
abstract public class RegisteredCustomer extends Client{
    protected String name;
    protected String phoneNumber;
    protected Double point;
    protected Boolean active;

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

    public void changeActiveStatus() {
        if (this.active){
            this.active = false;
        } else {
            this.active = true;
        }
    }
}
