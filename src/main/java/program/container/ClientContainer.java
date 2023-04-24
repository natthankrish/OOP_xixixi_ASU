package program.container;


import program.entities.*;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientContainer{
    private List<? super Client> buffer;
    private int amount;

    public void reset() {
        buffer = new ArrayList<>();
        amount = 0;
    }
    public void increaseAmount(){
        amount++;
    }

    public void decreaseAmount(){
        amount--;
    }
    public Customer getCustomerById(Integer id){
        for (Object obj : buffer) {
            if (obj instanceof Customer){
                Customer cr = (Customer) obj;
                Integer tempID = cr.getId();
                if (tempID.equals(id)){
                    return cr;
                }
            }
        }
        return null;
    }

    public Member getMemberById(Integer id){
        for (Object obj : buffer) {
            if (obj instanceof Member){
                Member mr = (Member) obj;
                Integer tempID = mr.getId();
                if (tempID.equals(id)){
                    return mr;
                }
            }
        }
        return null;
    }

    public VIP getVIPById(Integer id){
        for (Object obj : buffer) {
            if (obj instanceof VIP){
                VIP vp = (VIP) obj;
                Integer tempID = vp.getId();
                if (tempID.equals(id)){
                    return vp;
                }
            }
        }
        return null;
    }

    public void addCustomer(Customer c){
        buffer.add(c);
        amount++;
    }

    public void addMember(Member m){
        buffer.add(m);
        amount++;
    }

    public void addVIP(VIP v) {
        buffer.add(v);
        amount++;
    }

    public void removeClient(Integer id){
        int idx = 0;
        for (Object obj : buffer){
            Integer tempID = 0;
            if (obj instanceof Customer){
                tempID = ((Customer)obj).getId();
            } else if ( obj instanceof Member){
                tempID = ((Member)obj).getId();
            } else if ( obj instanceof VIP){
                tempID = ((VIP)obj).getId();
            }
            if (tempID.equals(id)){
                buffer.remove(idx);
                amount--;
                break;
            }
            idx++;
        }
    }

    public Integer getMaxID() {
        Integer max = 0;
        Integer tempID = 0;
        boolean first = true;
        for (Object obj: buffer){
            if (obj instanceof Customer){
                tempID = ((Customer)obj).getId();
            }
            else if (obj instanceof Member){
                tempID = ((Member)obj).getId();
            }
            else if (obj instanceof VIP){
                tempID = ((VIP)obj).getId();
            }

            if (first) {
                max = tempID;
                first = false;
            } else {
                if (tempID.intValue() > max.intValue()){
                    max = tempID;
                }
            }
        }
        return max;
    }


}
